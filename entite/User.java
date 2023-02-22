/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author choua
 */
public class User {
    private int IdUser;
    
    
 public User() {
    }
 
    public User(int IdUser) {
        this.IdUser = IdUser;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    @Override
    public String toString() {
        return "User{" + "IdUser=" + IdUser + '}';
    }

   
    
    
}
