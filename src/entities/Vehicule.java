package entities;

public class Vehicule {

    private int id;
    private String matricule;
    private String marque;
    private String type;


    public Vehicule( String matricule, String marque, String type) {
        super();
       
        this.matricule = matricule;
        this.marque = marque;
        this.type = type;
    }
    public Vehicule(int id , String matricule, String marque, String type) {
        super();
        this.id=id;
        this.matricule = matricule;
        this.marque = marque;
        this.type = type;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", matricule='" + matricule + '\'' +
                ", marque='" + marque + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
