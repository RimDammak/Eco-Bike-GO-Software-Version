/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;


/**
 *
 * @author pc
 * @param <user>
 */
public interface IService <user> {
    public void insertPst(user p);
    void delete(int id);
    
    void update(int id, String nom, String prenom, java.util.Date dateNaiss, String num, String email, String adresse, String image, String role);
    List<user> readAll();
    user readById(int id);
    int calculerNombreUsers();
    void showNotification(String title, String message);
    boolean isEmailExists(String email);
}
