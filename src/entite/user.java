/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class user {
     private int IdUser ;
    private String NomUser ;
    private String  PrenomUser;
    private Date DateNaiss;
    private String NumTel;
    private String Email;
    private String Adresse;
    private String Mdp;
    private String ImgUser;
    private String Role;

    public user() {
    }

    public user(int IdUser) {
        this.IdUser = IdUser;
    }

    public user(int IdUser, String NomUser, String PrenomUser, Date DateNaiss, String NumTel, String Email, String Adresse, String Mdp, String ImgUser, String Role) {
        this.IdUser = IdUser;
        this.NomUser = NomUser;
        this.PrenomUser = PrenomUser;
        this.DateNaiss = DateNaiss;
        this.NumTel = NumTel;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Mdp = Mdp;
        this.ImgUser = ImgUser;
        this.Role = Role;
    }

    public user(String NomUser, String PrenomUser, Date DateNaiss, String NumTel, String Email, String Adresse, String Mdp, String ImgUser, String Role) {
        this.NomUser = NomUser;
        this.PrenomUser = PrenomUser;
        this.DateNaiss = DateNaiss;
        this.NumTel = NumTel;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Mdp = Mdp;
        this.ImgUser = ImgUser;
        this.Role = Role;
    }

    public user(String NomUser, String PrenomUser, String NumTel, String Email, String Adresse, String Mdp) {
        this.NomUser = NomUser;
        this.PrenomUser = PrenomUser;
        this.NumTel = NumTel;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Mdp = Mdp;
    }

    

    public int getIdUser() {
        return IdUser;
    }

    public String getNomUser() {
        return NomUser;
    }

    public String getPrenomUser() {
        return PrenomUser;
    }

    public Date getDateNaiss() {
        return DateNaiss;
    }

    public String getNumTel() {
        return NumTel;
    }

    public String getEmail() {
        return Email;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getMdp() {
        return Mdp;
    }

    public String getImgUser() {
        return ImgUser;
    }

    public String getRole() {
        return Role;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public void setNomUser(String NomUser) {
        this.NomUser = NomUser;
    }

    public void setPrenomUser(String PrenomUser) {
        this.PrenomUser = PrenomUser;
    }

    public void setDateNaiss(Date DateNaiss) {
        this.DateNaiss = DateNaiss;
    }

    public void setNumTel(String NumTel) {
        this.NumTel = NumTel;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }

    public void setImgUser(String ImgUser) {
        this.ImgUser = ImgUser;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "user{" + "IdUser=" + IdUser + ", NomUser=" + NomUser + ", PrenomUser=" + PrenomUser + ", DateNaiss=" + DateNaiss + ", NumTel=" + NumTel + ", Email=" + Email + ", Adresse=" + Adresse + ", Mdp=" + Mdp + ", ImgUser=" + ImgUser + ", Role=" + Role + '}';
    }

    public void setImgUser(byte[] imageBytes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
    
    
}
