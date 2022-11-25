package service;

import dao.IDao;
import entities.Vehicule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class vehiculeService implements IDao<Vehicule> {


    public boolean create(Vehicule V) {
        try {
            String req = "insert into vehicule values (null, ?,?,?)";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setString(1, V.getMatricule());
            pr.setString(2, V.getMarque());
            pr.setString(3, V.getType());
            if (pr.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Vehicule v) {
        try {
            String req = "update VEHICULE set marque = ? , matricule = ? , type = ?  where id = ?";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setString(1,v.getMarque());
            pr.setString(2,v.getMatricule());
            pr.setString(3,v.getType());
            pr.setInt(4, v.getId());
            if (pr.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Vehicule v) {
        System.out.println(v.getId());
        try {
            String req = "delete from vehicule where id = ?";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setInt(1, v.getId());
            if (pr.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Vehicule findById(int id) {
        try {
            String req = "select * from vehicule where id = ?";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next())
                return new Vehicule(rs.getInt("id"), rs.getString("matricule"),rs.getString("marque"),rs.getString("type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Vehicule("","","");
    }

    @Override
    public List<Vehicule> findAll() {
        List<Vehicule> vehicules = new ArrayList<Vehicule>();
        try {
            String req = "select * from vehicule";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
            while (rs.next())
                vehicules.add(new Vehicule( rs.getInt("id"),rs.getString("matricule"),rs.getString("marque"),rs.getString("type")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicules;
    }

}
