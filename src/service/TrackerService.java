package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.IDao;
import entities.Tracker;

public class TrackerService implements IDao<Tracker> {

    @Override
    public boolean create(Tracker o) {
        try {
            String req = "insert into tracker values (null, ?)";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
            pr.setString(1, o.getSimNumber());
            if (pr.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Tracker o) {
        try {
            String req = "update tracker set simNumber = ? where id = ?";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
            pr.setString(1, o.getSimNumber());
            pr.setInt(2, o.getId());
            if (pr.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Tracker o) {
        try {
            String req = "delete from tracker where id = ?";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
            pr.setInt(1, o.getId());
            if (pr.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            
        }
        return false;
    }

    @Override
    public Tracker findById(int id) {
        try {
            String req = "select * from tracker where id = ?";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return new Tracker(rs.getInt("id"), rs.getString("simNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tracker> findAll() {
        List<Tracker> trackers = new ArrayList<Tracker>();
        try {
            String req = "select * from tracker";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                trackers.add(new Tracker(rs.getInt("id"), rs.getString("simNumber")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trackers;
    }

}
