/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author damak
 */
public class DataSource {
    
    private String url = "jdbc:mysql://localhost:3306/wedev";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static DataSource instance;
   
    public DataSource(){
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
public static DataSource getInstance(){
    if (instance==null)
        instance=new DataSource();
    return instance;
}
public Connection getCnx()  {
    return cnx;
}

    public void close() {
    
    }

    public Connection getConnection() {
        return null;
 
    }

    
}