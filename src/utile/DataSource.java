/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author houss
 */
public class DataSource {
    private String url="jdbc:mysql://localhost:3306/wedev";
    private String login="root";
    private String pwd="";
    private java.sql.Connection cnx;
    private static DataSource instance;

    private DataSource() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("marhbee bik houssem");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }//ou java.util.logging.Level
    }
    
    public static DataSource getInstance(){
        if(instance==null)
            instance=new DataSource();
        return instance;
    }

    public Connection getCnx() {
        //ou  java.sql.Connection
        return cnx;
    } 
}
