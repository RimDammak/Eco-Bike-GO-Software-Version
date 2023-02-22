/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

/**
 *
 * @author macbook
 */
public class Station {
    private int id_station;
    private String nom_station;

    public Station() {
    }

    public Station(int id_station) {
        this.id_station = id_station;
        this.nom_station = nom_station;
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

    @Override
    public String toString() {
        return "Station{" + "id_station=" + id_station + ", nom_station=" + nom_station + '}';
    }
    
    
    
}
