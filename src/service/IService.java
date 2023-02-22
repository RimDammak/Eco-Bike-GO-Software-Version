/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entite.user;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pc
 */
public interface IService <T> {
    public void insertPst(user p);
    void delete(int id);
    
    void update(int id, String nom, String prenom, java.sql.Date date, String tel, String email, byte[] img, String adresse, String mdp, String role);
    List<T> readAll();
    user readById(int id);
    int calculerNombreUsers();
    
}
