/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

/**
 *
 * @author houss
 */
public class Station {
    private int id_station;
    private String nom_station;
    private String localisation_station;
    private int velo_station;

    public Station(int id_station, String nom_station, String localisation_station, int velo_station) {
        this.id_station = id_station;
        this.nom_station = nom_station;
        this.localisation_station = localisation_station;
        this.velo_station = velo_station;
    }

    public Station(int id_station) {
        this.id_station = id_station;
    }

    public Station() {
    }

    public int getId_station() {
        return id_station;
    }

    public void setId_station(int id_station) {
        this.id_station = id_station;
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

    public int getVelo_station() {
        return velo_station;
    }

    public void setVelo_station(int velo_station) {
        this.velo_station = velo_station;
    }

    public Station(String nom_station, String localisation_station, int velo_station) {
        this.nom_station = nom_station;
        this.localisation_station = localisation_station;
        this.velo_station = velo_station;
    }

    @Override
    public String toString() {
        return "Station{" + "id_station=" + id_station + ", nom_station=" + nom_station + ", localisation_station=" + localisation_station + ", velo_station=" + velo_station + '}';
    }

}
