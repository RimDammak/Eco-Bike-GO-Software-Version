/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import java.time.LocalDateTime;

/**
 *
 * @author houss
 */
public class Resielo {
    private int id_reservation;
    private LocalDateTime date_debut;
    private LocalDateTime date_fin;
    private String nom_station;
    private String localisation_station;
    private String NomUser;
    private String PrenomUser;
    private String NumTel;
    private String titre;
    private double prix;   
    private int nbr;        
    private double prixr;

    public Resielo(int id_reservation, LocalDateTime date_debut, LocalDateTime date_fin, String nom_station, String localisation_station, String NomUser, String PrenomUser, String NumTel, String titre, double prix, int nbr, double prixr) {
        this.id_reservation = id_reservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nom_station = nom_station;
        this.localisation_station = localisation_station;
        this.NomUser = NomUser;
        this.PrenomUser = PrenomUser;
        this.NumTel = NumTel;
        this.titre = titre;
        this.prix = prix;
        this.nbr = nbr;
        this.prixr = prixr;
    }

    public Resielo(LocalDateTime date_debut, LocalDateTime date_fin, String nom_station, String localisation_station, String NomUser, String PrenomUser, String NumTel, String titre, double prix, int nbr, double prixr) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nom_station = nom_station;
        this.localisation_station = localisation_station;
        this.NomUser = NomUser;
        this.PrenomUser = PrenomUser;
        this.NumTel = NumTel;
        this.titre = titre;
        this.prix = prix;
        this.nbr = nbr;
        this.prixr = prixr;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public LocalDateTime getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }

    public String getNom_station() {
        return nom_station;
    }

    public void setNom_station(String nom_station) {
        this.nom_station = nom_station;
    }

    public String getLocalisation_station() {
        return localisation_station;
    }

    public void setLocalisation_station(String localisation_station) {
        this.localisation_station = localisation_station;
    }

    public String getNomUser() {
        return NomUser;
    }

    public void setNomUser(String NomUser) {
        this.NomUser = NomUser;
    }

    public String getPrenomUser() {
        return PrenomUser;
    }

    public void setPrenomUser(String PrenomUser) {
        this.PrenomUser = PrenomUser;
    }

    public String getNumTel() {
        return NumTel;
    }

    public void setNumTel(String NumTel) {
        this.NumTel = NumTel;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public double getPrixr() {
        return prixr;
    }

    public void setPrixr(double prixr) {
        this.prixr = prixr;
    }

    @Override
    public String toString() {
        return "Resielo{" + "id_reservation=" + id_reservation + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", nom_station=" + nom_station + ", localisation_station=" + localisation_station + ", NomUser=" + NomUser + ", PrenomUser=" + PrenomUser + ", NumTel=" + NumTel + ", titre=" + titre + ", prix=" + prix + ", nbr=" + nbr + ", prixr=" + prixr + '}';
    }
    
}
