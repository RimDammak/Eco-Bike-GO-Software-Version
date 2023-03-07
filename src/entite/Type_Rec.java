package entite;

/**
 * A class representing different types of reclamation.
 */
public class Type_Rec {

    public static Type_Rec[] values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int id_type;
    private String nom_type;

    public Type_Rec(int id_type, String nom_type) {
        this.id_type = id_type;
        this.nom_type = nom_type;

    }

    public Type_Rec(String nom_type) {
        this.nom_type = nom_type;
    }

    public Type_Rec() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNom_type() {
        return nom_type;
    }

    public void setNom_type(String nom_type) {
        this.nom_type = nom_type;
    }

    public Type_Rec(int id_type) {
        this.id_type = id_type;

    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    @Override
    public String toString() {
        return "Type_Rec{" + "id_type=" + id_type + ", nom_type=" + nom_type + '}';
    }

}
