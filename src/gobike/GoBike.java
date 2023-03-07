/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gobike;

import java.time.LocalDateTime;
import java.util.Date;
import utile.DataSource;
import entite.Reservation;
import service.ServiceReservation;

/**
 *
 * @author houss
 */
public class GoBike {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("houssem main");
        // TODO code application logic here
        DataSource ds1 =DataSource.getInstance();
        System.out.println(ds1 );
        /*station st=new station("Nom_Station", "Localisation_Station", 2, 3);
       ServiceStation station = new ServiceStation();
        station.insertPst(st);
       */
        Date date_heure = new Date();
    System.out.println(date_heure);
    
        //ServiceReservation sr=new ServiceReservation();
        //sr.readAll().forEach(System.out::println);
        Reservation a =new Reservation();
        
        

    }
    
}
