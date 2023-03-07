/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Blob;
import java.sql.Date;
import static java.sql.JDBCType.BLOB;
import java.sql.SQLException;
import static java.sql.Types.BLOB;
import javafx.scene.image.Image;

/**
 *
 * @author choua
 */
public class Event {

    private int  id_event ;
    private String nom_event ;
    private Date date_event ;
    private String locate_event ;
    private Blob photo_event ;
    private int dispoplace_event ;

    public Event() {
    }

    public Event(int id_event) {
        this.id_event = id_event;
    }

    public Event(String nom_event) {
        this.nom_event = nom_event;
    }

    public Event(int id_event, String nom_event, Date date_event, String locate_event, Blob photo_event, int dispoplace_event) {
        this.id_event = id_event;
        this.nom_event = nom_event;
        this.date_event = date_event;
        this.locate_event = locate_event;
        this.photo_event = photo_event;
        this.dispoplace_event = dispoplace_event;
    }

    public Event(String nom_event, Date date_event, String locate_event, Blob photo_event, int dispoplace_event) {
        this.nom_event = nom_event;
        this.date_event = date_event;
        this.locate_event = locate_event;
        this.photo_event = photo_event;
        this.dispoplace_event = dispoplace_event;
    }



 
    

 

   
    

   




    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public String getLocate_event() {
        return locate_event;
    }

    public void setLocate_event(String locate_event) {
        this.locate_event = locate_event;
    }

    public Blob getPhoto_event() {
        return photo_event;
    }

    public void setPhoto_event(Blob photo_event) {
        this.photo_event = photo_event;
    }

 
  
  

    public int getDispoplace_event() {
        return dispoplace_event;
    }

    public void setDispoplace_event(int dispoplace_event) {
        this.dispoplace_event = dispoplace_event;
    }

    @Override

    
    public String toString() {
        return "Event{" + "id_event=" + id_event + ", nom_event=" + nom_event + ", date_event=" + date_event + ", locate_event=" + locate_event + ", photo_event=" + photo_event + ", dispoplace_event=" + dispoplace_event + '}';
    }

    public void setPhoto_event(byte[] imageBytes) {
        Blob blob = null;
    try {
        blob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
    } catch (SQLException ex) {
        // Handle the exception
    }
    this.photo_event = blob;
    }
   

    

    
}
