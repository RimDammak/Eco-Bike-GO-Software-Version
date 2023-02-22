/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.*;
import javafx.scene.control.TextArea;

/**
 *
 * @author damak
 */
public class Reclamation {

    private int id_rec;
    private int etat_rec;
    private Date date_rec;
    private User U;
    private String description_rec;
    private String reponse;
    private Type_Rec T;

    
    
    
    public Reclamation(Date date_rec) {
      
    }

    public Reclamation(int id_rec, int etat_rec, Date date_rec, User U, String description_rec, String reponse, Type_Rec T) {
        this.id_rec = id_rec;
        this.etat_rec = etat_rec;
        this.date_rec = date_rec;
        this.U = U;
        this.description_rec = description_rec;
        this.reponse = reponse;
        this.T = T;
    }

    public Reclamation(int etat_rec, Date date_rec, User U, String description_rec, String reponse, Type_Rec T) {
        this.etat_rec = etat_rec;
        this.date_rec = date_rec;
        this.U = U;
        this.description_rec = description_rec;
        this.reponse = reponse;
        this.T = T;
    }

    public Reclamation() {
          }

    public User getU() {
        return U;
    }

    public void setU(User U) {
        this.U = U;
    }

    public Type_Rec getT() {
        return T;
    }

    public void setT(Type_Rec T) {
        this.T = T;
    }


    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public int getEtat_rec() {
        return etat_rec;
    }

    public void setEtat_rec(int etat_rec) {
        this.etat_rec = etat_rec;
    }

    public Date getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(Date date_rec) {
        this.date_rec = date_rec;
    }

    public String getDescription_rec() {
        return description_rec;
    }

    public void setDescription_rec(String description_rec) {
        this.description_rec = description_rec;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", etat_rec=" + etat_rec + ", date_rec=" + date_rec + ", U=" + U + ", description_rec=" + description_rec + ", reponse=" + reponse + ", T=" + T + '}';
    }

    public void setDescription_rec(TextArea text_rec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   


}
