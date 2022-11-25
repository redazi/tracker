package entities;

import java.util.Date;

public class VehiculeGpsTrqcker {
    private int id;
    private int id_vs;
    private int id_ts;
    private Date dateDebut;
    private Date dateFin;
    private static int count;

    public VehiculeGpsTrqcker(int id_vs, int id_ts, Date dateDebut, Date dateFin) {
        super();
        this.id_vs = id_vs;
        this.id_ts = id_ts;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
         this.id=count++;
    }
    public VehiculeGpsTrqcker(int id,int id_vs, int id_ts, Date dateDebut, Date dateFin) {
        super();
        
        this.id_vs = id_vs;
        this.id_ts = id_ts;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
         this.id=id;
    }
     public VehiculeGpsTrqcker(int id_vs, int id_ts, Date dateDebut) {
        super();
        this.id_vs = id_vs;
        this.id_ts = id_ts;
        this.dateDebut = dateDebut;
       
         this.id=count++;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public int getId_vs() {
        return id_vs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_vs(int id_vs) {
        this.id_vs = id_vs;
    }

    public int getId_ts() {
        return id_ts;
    }

    public void setId_ts(int id_ts) {
        this.id_ts = id_ts;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "VehiculeGpsTrqcker{" + "id=" + id + ", id_vs=" + id_vs + ", id_ts=" + id_ts + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

   
}
