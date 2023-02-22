/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecobikego;

import Entite.Event;
import Service.ServiceEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utile.DataSource;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.applet.Main;

/**
 *
 * @author damak
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        DataSource ds1 = DataSource.getInstance();
        System.out.println(ds1);
     /*   
          String dateString = "11-12-2012";
SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
try {
         java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2023");
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
Event e = new Event("WEGO123", sqlDate, "GAMMARTHAZERTY", "", 100);
    ServiceEvent se = new ServiceEvent();
    se.insert(e);
} catch (ParseException ex) {
    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        
    }*/
    
}
}