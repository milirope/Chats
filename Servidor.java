package com.mycompany.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.JOptionPane;


public class Servidor extends Thread{    

    private ServerSocket serverSocket;

    LinkedList<HiloCliente> clientes;
    LinkedList<String> usuariosRegistrados;
    

    private final VentanaS ventana;

    private final String puerto;

    static int correlativo;

    public Servidor(String puerto, VentanaS ventana) {
        
        correlativo=0;
        this.puerto=puerto;
        this.ventana=ventana;
        clientes=new LinkedList<>();
        usuariosRegistrados=new LinkedList<>();
        
        DBAccess db = DBAccess.getConnection();
        ResultSet rs = null;
        
        try{
            rs = db.execute("SELECT nombre, cedula FROM persona;");
            while(rs.next()){
                usuariosRegistrados.add(rs.getString(1)+" - "+rs.getInt(2));
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        
        this.start();
    }
 
    public void run() {
        try {
            serverSocket = new ServerSocket(Integer.valueOf(puerto));
            ventana.addServidorIniciado();
            while (true) {
                HiloCliente h;
                Socket socket;
                socket = serverSocket.accept();
                System.out.println("Nueva conexion entrante: "+socket);
                h = new HiloCliente(socket, this);               
                h.start();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "El servidor no se ha podido iniciar,\n"
                                                 + "puede que haya ingresado un puerto incorrecto.\n"
                                                 + "Esta aplicación se cerrará.");
            System.exit(0);
        }                
    }        

    LinkedList<String> getUsuariosConectados() {
        LinkedList<String>usuariosConectados = new LinkedList<>();
        clientes.stream().forEach(c -> usuariosConectados.add(c.getIdentificador()));
        return usuariosConectados;
    }

    LinkedList<String> getUsuariosRegistrados() {
        LinkedList<String> usuariosRegistrados = new LinkedList<>();
        DBAccess db = DBAccess.getConnection();
        ResultSet rs = null;
        try{
            rs = db.execute("SELECT nombre, cedula FROM persona;");
            while(rs.next()){
                usuariosRegistrados.add(rs.getString(1)+" - "+rs.getInt(2));
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return usuariosRegistrados;
    }

    void agregarLog(String texto) {
        ventana.agregarLog(texto);
    }
}
