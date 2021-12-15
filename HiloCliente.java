package com.mycompany.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.LinkedList;


public class HiloCliente extends Thread{

    private final Socket socket;    
 
    private ObjectOutputStream objectOutputStream;

    private ObjectInputStream objectInputStream;            
       
    private final Servidor server;

    private String identificador;

    private boolean escuchando;

    public HiloCliente(Socket socket,Servidor server) {
        this.server = server;
        this.socket = socket;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.err.println("Error en la inicialización del ObjectOutputStream y el ObjectInputStream");
        }
    }
  
    public void desconnectar() {
        try {
            socket.close();
            escuchando=false;
        } catch (IOException ex) {
            System.err.println("Error al cerrar el socket de comunicación con el cliente.");
        }
    }
       
    public void run() {
        try{
            escuchar();
        } catch (Exception ex) {
            System.err.println("Error al llamar al método readLine del hilo del cliente.");
        }
        desconnectar();
    }
        
    public void escuchar(){        
        escuchando=true;
        while(escuchando){
            try {
                Object aux=objectInputStream.readObject();
                if(aux instanceof LinkedList){
                    ejecutar((LinkedList<String>)aux);
                }
            } catch (Exception e) {                    
                //System.err.println("Error al leer lo enviado por el cliente.");
            }
        }
    }
  
    public void ejecutar(LinkedList<String> lista){
        // 0 - El primer elemento de la lista es siempre el tipo
        String tipo=lista.get(0);
        switch (tipo) {
            case "SOLICITUD_CONEXION":
                // 1 - Identificador propio del nuevo usuario
                confirmarConexion(lista.get(1));
                break;
            case "SOLICITUD_DESCONEXION":
                // 1 - Identificador propio del nuevo usuario
                confirmarDesConexion();
                break;                
            case "MENSAJE":
                // 1      - Cliente emisor
                // 2      - Cliente receptor
                // 3      - Mensaje
                String emisor = lista.get(1);
                String destinatario=lista.get(2);
                String mensaje = lista.get(3);
                
                String cedulaE = emisor.split(" - ")[1];
                String cedulaD = destinatario.split(" - ")[1];
                  
                server.clientes
                        .stream()
                        .filter(h -> (destinatario.equals(h.getIdentificador())))
                        .forEach((h) -> h.enviarMensaje(lista));
                
                DBAccess db = DBAccess.getConnection();

                try{
                    db.update("INSERT INTO mensaje (contenido, de_persona_cedula, para_persona_cedula) "
                            + "VALUES ('"+mensaje+"',"+cedulaE+","+cedulaD+");");
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    db.close();
                }
                
                break;
            default:
                break;
        }
    }
    
    private void enviarMensaje(LinkedList<String> lista){
        try {
            objectOutputStream.writeObject(lista);            
        } catch (Exception e) {
            System.err.println("Error al enviar el objeto al cliente.");
        }
    }    

    private void confirmarConexion(String identificador) {
        //Servidor.correlativo++;
        //this.identificador=Servidor.correlativo+" - "+identificador;
        this.identificador=identificador;
        LinkedList<String> lista=new LinkedList<>();
        lista.add("CONEXION_ACEPTADA");
        lista.add(this.identificador);
        lista.addAll(server.getUsuariosRegistrados());
        enviarMensaje(lista);
        server.agregarLog("\nNuevo cliente: "+this.identificador);
        //enviar a todos los clientes el nombre del nuevo usuario conectado excepto a él mismo
        LinkedList<String> auxLista=new LinkedList<>();
        auxLista.add("NUEVO_USUARIO_CONECTADO");
        auxLista.add(this.identificador);
        server.clientes
                .stream()
                .forEach(cliente -> cliente.enviarMensaje(auxLista));
        server.clientes.add(this);
    }

    public String getIdentificador() {
        return identificador;
    }

    private void confirmarDesConexion() {
        LinkedList<String> auxLista=new LinkedList<>();
        auxLista.add("USUARIO_DESCONECTADO");
        auxLista.add(this.identificador);
        server.agregarLog("\nEl cliente \""+this.identificador+"\" se ha desconectado.");
        this.desconnectar();
        for(int i=0;i<server.clientes.size();i++){
            if(server.clientes.get(i).equals(this)){
                server.clientes.remove(i);
                break;
            }
        }
        server.clientes
                .stream()
                .forEach(h -> h.enviarMensaje(auxLista));        
    }
}
