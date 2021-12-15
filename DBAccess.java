/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBAccess {
    
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private int res;
    
    public DBAccess(String driver, String url, String usr, String pwd) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static DBAccess getConnection(){
            return new DBAccess(
                "org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/datos",
                "postgres",
                "masterkey"
            );
    }
    
    //Select simples para comprobaciones
    public ResultSet execute(String query){
            
            try {
                this.stmt = con.createStatement();
                this.rs = this.stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return this.rs; 
    }

    //Sentencias de modificaciones a la DB
    public int update(String query) {
        try {
            this.stmt = con.createStatement();
            this.res = this.stmt.executeUpdate(query);
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return this.res; 
    }

	
	//Cerrar conexiones
    public void close() {
        try {
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
