/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entite.Reservation;
import java.util.List;

/**
 *
 * @author houss
 * @param <Reservation>
 */
public interface IReservation<Reservation> {
    void insertPst(Reservation r) ;
    List<Reservation> readAll();
    Reservation read(int id);
    //Reservation read(int id);
}
