/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author choua
 */
public interface IEventServie <E> {
    
    void insert(E e);
    void delete(String nom);
    void update(int  id,String nom,Date date,String locate,byte[] photo,int dispoplace_event);
    List<E> readAll();
    E readById(int Id);
    

    
    
    
}
