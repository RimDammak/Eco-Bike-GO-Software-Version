/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entite.Categorie;
import entite.Station;
import java.sql.Blob;
import java.util.List;

/**
 *
 * @author macbook
 * @param <S>
 */
public interface IVeloService <S>{
    void insert(S s);
    void delete(int id_velo);
    void update(int id_velo, Station sta, String titre, float prix, Blob image, int qte, Categorie cat);
    List<S> readAll();

    /**
     *
     * @param id_velo
     * @return
     */
    S readByID(int id_velo);
}