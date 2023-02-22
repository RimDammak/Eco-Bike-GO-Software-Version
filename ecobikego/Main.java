/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecobikego;

import java.sql.Date;
import service.ServiceReclamation;
import utils.DataSource;
import entite.Reclamation;
import entite.Type_Rec;
import entite.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class Main {

    public static void main(String[] args) throws ParseException {

        // Create a new instance of DataSource
        DataSource ds1 = DataSource.getInstance();
        System.out.println(ds1);

//    ServiceVelo sv = new ServiceVelo();
//   Velo v = new Velo();
//   v.setId_velo(3);
//    v.setSta(new Station(1 ,"test"));
//    v.setTitre("iheb");
//    v.setPrix(333.23f);
//    v.setImage("");
//    v.setQte(999);
//    v.setCat(new Categorie(1,"Categorie ","zzzzz"));
//    sv.insert(v);
//        System.err.println(v);
    
      //  Create a Reclamation object with required parameters
Reclamation r = new Reclamation();
     //   Reclamation setDate_rec = r.setDate_rec(new Date().getTime());
     java.util.Date utilDate=new SimpleDateFormat("dd-MM-yyyy").parse("1-1-2023");
     java.sql.Date SQL_date = new java.sql.Date(utilDate.getTime());
 r.setDate_rec(SQL_date);    
r.setDescription_rec("Choiuaibi test");
r.setT(new Type_Rec(1,"nametype"));
r.setEtat_rec(1);
r.setReponse("hhh");
r.setU(new User(6));


// Create a ServiceReclamation object and call the insert method
ServiceReclamation sr = new ServiceReclamation();
sr.insert(r);


//TEST READ ALL
//ServiceReclamation sr=new ServiceReclamation();
//List <Reclamation> r=sr.readAll();
//for (Reclamation rec : r){
//    System.out.println("VOICI LID " +rec.getId_rec());
//  
//}
//fin test read all
//Reclamation r = new Reclamation();
//r.setId_rec(2);
//Date specificDate = new Date(95, 5, 12);
//r.setDate_rec(specificDate);


//
//int id_rec = 7;
//Reclamation rec= sr.readById(id_rec);
//  
//   System.out.println("Voici la reclamation avec l'ID 7: (AVANT MODIFICATION) " + rec);
//   System.out.println("-------------");
   Reclamation recl= new Reclamation();
  // r.setDate_rec(new java.sql.Date(new Date().getTime()));
r.setDescription_rec("NHB NRECLAMI");
r.setT(new Type_Rec(1,"NOMTYPE"));
r.setU(new User(2));
r.setEtat_rec(2);
r.setReponse("wathah");
r.setU(new User(7));
sr.insert(r);
System.out.println("INSERSTION AVEC SUCESS");
      

////      int id_re=50;
////     int etat_rec=20;
////     Date specificDate  = new Date(95, 5, 12);
////    
////     User Usr= new User(5);
////     String description_rec="BBB";
////     String reponse="hhh";
////     Type_Rec Ty= new Type_Rec(2,"nom") ;
//
//
//    // Creating a new Type_Rec object
//    Type_Rec type = new Type_Rec();
//    type.setId_type(1);
//    type.setNom_type("Type 1");
//    
//    // Creating a new User object
//    User user = new User();
//    user.setIdUser(1);
//
    
    // Updating the record with id_rec = 100
  
//}

    }
    }


        
        
       
    

