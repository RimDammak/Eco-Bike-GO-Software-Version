/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;
import Entite.Event;


/**
 *
 * @author choua
 */
public class Reserv {
    
    
    private int id_res;
    private Event ev;
    private user u;

    public Reserv() {
    }

    public Reserv(int id_res, Event ev, user u) {
        this.id_res = id_res;
        this.ev = ev;
        this.u = u;
    }

   

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public Event getEv() {
        return ev;
    }

    public void setEv(Event ev) {
        this.ev = ev;
    }

    public user getU() {
        return u;
    }

    @Override
    public String toString() {
        return "Reserv{" + "id_res=" + id_res + ", ev=" + ev + ", u=" + u + '}';
    }

    public void setU(user u) {
        this.u = u;
    }
public void setEvent(Event event) {
    this.ev = event;
}

  public void setUser(user user){
      this.u=user;
  }

   
    
    
    
}
