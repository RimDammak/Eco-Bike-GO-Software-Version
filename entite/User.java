package entite;

public class User {
    private int IdUser;

    public User(int IdUser) {
        this.IdUser = IdUser;
    }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
