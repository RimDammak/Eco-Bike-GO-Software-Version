/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

/**
 *
 * @author macbook
 */
public class Velo {
    
    private int id_velo;
   private  Station sta;
    private String titre;
    private float prix;
    private String image;
    private int qte;
    private Categorie cat;
private String pathImage;
private double rating;

    public Velo() {
    }
  public Velo(String image) {
        this.image = image;
    }
    public Velo(int id_velo, Station sta, String titre, float prix, String image, int qte, Categorie cat,double rating) {
        this.id_velo = id_velo;
        this.sta = sta;
        this.titre = titre;
        this.prix = prix;
        this.image = image;
        this.qte = qte;
        this.cat = cat;
        this.rating = rating;
    }

    public Velo(int id_velo) {
        this.id_velo = id_velo;
    }

    public int getId_velo() {
        return id_velo;
    }

    public Station getSta() {
        return sta;
    }

    public String getTitre() {
        return titre;
    }

    public float getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

    public int getQte() {
        return qte;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setId_velo(int id_velo) {
        this.id_velo = id_velo;
    }

    public void setSta(Station sta) {
        this.sta = sta;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }
public double getRating() {
        return rating;
    }
     public void setRating(double rating) {
        this.rating = rating;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }
public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    @Override
    public String toString() {
        return "Velo{" + "id_velo=" + id_velo + ", sta=" + sta + ", titre=" + titre + ", prix=" + prix + ", image=" + image + ", qte=" + qte + ", cat=" + cat + ", pathImage=" + pathImage + ", rating=" + rating + '}';
    }

   


   
   

}
