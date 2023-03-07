/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;

/**
 *
 * @author houss
 * @param <Station>
 */
public interface IStation<Station> {
    void insertPst(Station p) ;
    boolean supprimerStation(Station s);
    List<Station> readAll();
    void modifierStation(int id, String nom, String localisation, int velodisponible);
    
    
}
