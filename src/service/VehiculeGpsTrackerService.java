package service;

import dao.IDao;
import entities.VehiculeGpsTrqcker;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculeGpsTrackerService implements IDao<VehiculeGpsTrqcker> {
    private List<VehiculeGpsTrqcker> vehiculeGPSTrackers;

    public VehiculeGpsTrackerService() {
        vehiculeGPSTrackers = new ArrayList<VehiculeGpsTrqcker>();
    }


   /* public List <Tracker> findGPSTrackersBetweenDates (Vehicule vehicule, Date dateDebut, Date dateFin){
        List<Tracker> gpsTrackers = new ArrayList<>();
        for(VehiculeGpsTrqcker vg : vehiculeGPSTrackers ){
            if(vg.getDateDebut().after(dateDebut) && vg.getDateDebut().before(dateFin) && vehicule.getId()==vg.getVs().getId() ){
                gpsTrackers.add(vg.getTs());

                return gpsTrackers;


            }
            if(vg.getDateFin().after(dateFin) && vg.getDateFin().before(dateFin) && vehicule.getId()==vg.getVs().getId() ){
                gpsTrackers.add(vg.getTs());

                return gpsTrackers;


            }
        }

        return null;
    }
*/

    public boolean check_ids_exist(int id_v,int id_t){
        boolean v = false;
        boolean t =false;
        try {
            String req = "select * from vehicule";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
            while (rs.next())
                if(rs.getInt("id") == id_v){
                        v=true ;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String req = "select * from tracker";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
            while (rs.next())
                if(rs.getInt("id") == id_t){
                    t = true;
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(v && t){
            return true;
        }else{
            return  false;
        }


    }


    @Override
    public boolean create(VehiculeGpsTrqcker o) {

            if(check_ids_exist(o.getId_vs(),o.getId_ts())==false){
                System.out.println("le tracker ou le vehicule que vous avez choisi n'exist pas dans la base");


            }else{
                try {
                    String req = "insert into VehiculeGpsTracker values (null,?,?,null,?) ";
                    PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
                    pr.setInt(1, o.getId_vs());
                    pr.setDate(2, new Date(o.getDateDebut().getTime()));
                   // pr.setDate(3, new Date(o.getDateFin().getTime()));
                    pr.setInt(3, o.getId_ts());
                    if (pr.executeUpdate() != 0)
                        return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        return false;
    }

    @Override
    public boolean delete(VehiculeGpsTrqcker o) {
        try {
            String req = "delete from vehiculegpstracker where id = ?";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setInt(1, o.getId());
            if (pr.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public VehiculeGpsTrqcker findById(int id) {
       try {
            String req = "select * from  vehiculegpstracker where id = ?";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next())
                return new VehiculeGpsTrqcker(rs.getInt("id"),rs.getInt("id_vehicule"), rs.getInt("id_tracker"),rs.getDate("dated"),rs.getDate("datef"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new VehiculeGpsTrqcker(0,0,new java.util.Date(),new java.util.Date());
    }

    @Override
    public boolean update(VehiculeGpsTrqcker o) {
        long millis=System.currentTimeMillis();  
java.sql.Date date=new java.sql.Date(millis); 
          try {
            String req = "update vehiculegpstracker set datef = ? where id = ?";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setDate(1, date);
            pr.setInt(2,o.getId());
          
            if (pr.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public List<VehiculeGpsTrqcker> findAll() {
        List<VehiculeGpsTrqcker> vehiculeGpsTrqckerList = new ArrayList<VehiculeGpsTrqcker>();
        try {
            String req = "select *  from VehiculeGpsTracker ";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
            while (rs.next())
                vehiculeGpsTrqckerList.add(new VehiculeGpsTrqcker(rs.getInt("id"),rs.getInt("id_vehicule"), rs.getInt("id_tracker"),
                        rs.getDate("dated"), rs.getDate("datef")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculeGpsTrqckerList;
    }
       public List<VehiculeGpsTrqcker> findAllh() {
        List<VehiculeGpsTrqcker> vehiculeGpsTrqckerList = new ArrayList<VehiculeGpsTrqcker>();
        try {
            String req = "select *  from VehiculeGpsTrackerh ";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
            while (rs.next())
                vehiculeGpsTrqckerList.add(new VehiculeGpsTrqcker(rs.getInt("id"),rs.getInt("id_vehicule"), rs.getInt("id_tracker"),
                        rs.getDate("dated"), rs.getDate("datef")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculeGpsTrqckerList;
    }
    }



