/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

/**
 *
 * @author macbook
 */
public class Categorie {
     private int id_categorie;
     private String nom_categorie;
     private String desc_categorie;

    public Categorie() {
    }

   public Categorie(int id_categorie) {
    this.id_categorie = id_categorie;
}

    
        
   public Categorie(int id_categorie, String nom_categorie, String desc_categorie) {
    this.id_categorie = id_categorie;
    this.nom_categorie = nom_categorie;
    this.desc_categorie = desc_categorie;
}


    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getDesc_categorie() {
        return desc_categorie;
    }

    public void setDesc_categorie(String desc_categorie) {
        this.desc_categorie = desc_categorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", nom_categorie=" + nom_categorie + ", desc_categorie=" + desc_categorie + '}';
    }
     
     
}