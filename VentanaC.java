
package com.mycompany.tarea3;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class VentanaC extends javax.swing.JFrame {
    
    String datosCliente;
    
   
    public VentanaC(String name) {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        String ip = DEFAULT_IP;
        String puerto = DEFAULT_PORT;
        String nombre = name; //Cambia dependiendo del inicio de sesion 
        this.datosCliente = name;
        cliente = new Cliente(this, ip, Integer.valueOf(puerto), nombre);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtHistorial = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();
        cmbContactos = new javax.swing.JComboBox();
        btnEnviar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtHistorial.setEditable(false);
        txtHistorial.setColumns(20);
        txtHistorial.setRows(5);
        jScrollPane1.setViewportView(txtHistorial);

        cmbContactos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbContactosActionPerformed(evt);
            }
        });

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        jLabel1.setText("Destinatario:");

        jButton1.setText("Administrar Usuarios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cerrar sesion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMensaje)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                        .addComponent(cmbContactos, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbContactos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        //Si no hay más clientes del chat con quien comunicarse.
        if(cmbContactos.getSelectedItem()==null){
            JOptionPane.showMessageDialog(this, "Debe escoger un destinatario válido, si no \n"
                                                 + "hay uno, espere a que otro usuario se conecte\n"
                                                 + "para poder chatear con él.");        
            return;
        }
        String cliente_receptor = cmbContactos.getSelectedItem().toString();
        String mensaje = txtMensaje.getText();
        cliente.enviarMensaje(cliente_receptor, mensaje);
        //se agrega en el historial de la conversación lo que el cliente ha dicho
        txtHistorial.append("## Yo -> "+cliente_receptor+ " ## : \n\n" + mensaje+"\n\n");
        txtMensaje.setText("");
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed
   
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cliente.confirmarDesconexion();
    }//GEN-LAST:event_formWindowClosing

    private void cmbContactosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbContactosActionPerformed
        // TODO add your handling code here:
        
        System.out.println("Se cambió!");
        System.out.println(cmbContactos.getSelectedItem().toString());
        txtHistorial.selectAll();
        txtHistorial.replaceSelection("");
        txtMensaje.setText("");
        
        String yo = datosCliente.split(" - ")[1];
        String otro = cmbContactos.getSelectedItem().toString().split(" - ")[1];
        
        DBAccess db = DBAccess.getConnection();
        ResultSet rs = null;
        
        
        try {
            rs = db.execute(
                    "SELECT de_persona_cedula, para_persona_cedula, contenido FROM mensaje WHERE"+
                    "(de_persona_cedula = "+yo+" AND para_persona_cedula = "+otro+" ) OR"+
                    "(de_persona_cedula = "+otro+" AND para_persona_cedula = "+yo+" )"
            );
            
            otro = cmbContactos.getSelectedItem().toString();
            
            while(rs.next()){
                if(rs.getString(1).equals(yo)){
                    txtHistorial.append("## Yo -> "+otro+ " ## : \n\n" + rs.getString(3) +"\n\n");
                }else{
                    txtHistorial.append("##### "+otro+ " ##### : \n\n" + rs.getString(3) +"\n\n");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentanaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_cmbContactosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            new Vista(datosCliente).setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new VistaInicio().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaC("admin - 123456").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JComboBox cmbContactos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtHistorial;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
    
    private final String DEFAULT_PORT="10101";
      
    private final String DEFAULT_IP="127.0.0.1";
    
    private final Cliente cliente;
    
    void addContacto(String contacto) {
        cmbContactos.addItem(contacto);
    }
    
    void addMensaje(String emisor, String mensaje) {
        //Mostrar en pantalla solo si el chat del emisor esta abierto
        System.out.println(emisor +"/"+ cmbContactos.getSelectedItem().toString());
        if(cmbContactos.getSelectedItem().toString().equals(emisor)){ 
            txtHistorial.append("##### "+emisor + " ##### : \n\n" + mensaje+"\n\n");
        }
    }
    
    void sesionIniciada(String identificador) {
        this.setTitle(" --- "+identificador+" --- ");
    }

}
