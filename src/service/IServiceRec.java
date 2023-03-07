/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package service;

import entite.Reclamation;
import entite.Type_Rec;
import entite.user;
import java.sql.Date;
import java.util.List;


public interface IServiceRec<R> {
    
    void insert(Reclamation r);
    void delete(int id_rec);
    void  update(int id_rec, Date date_rec, String description_rec, Type_Rec T, int etat_rec, String reponse, user U) ;
    List<R> readAll();
    R readById(int id_rec);
    
}

