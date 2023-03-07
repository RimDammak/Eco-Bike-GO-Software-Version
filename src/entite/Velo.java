/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import java.sql.Blob;
import java.util.logging.Logger;

/**
 *
 * @author macbook
 */
public class Velo {
    
    private int id_velo;
   private  Station sta;
    private String titre;
    private float prix;
    private Blob image;
    private int qte;
    private Categorie cat;

    public Velo() {
    }

    public Velo(int id_velo, Station sta, String titre, float prix, Blob image, int qte, Categorie cat) {
        this.id_velo = id_velo;
        this.sta = sta;
        this.titre = titre;
        this.prix = prix;
        this.image = image;
        this.qte = qte;
        this.cat = cat;
    }

    public int getId_velo() {
        return id_velo;
    }

    public void setId_velo(int id_velo) {
        this.id_velo = id_velo;
    }

    public Station getSta() {
        return sta;
    }

    public void setSta(Station sta) {
        this.sta = sta;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }
    
   
   
}
