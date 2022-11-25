/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entities.User;
import entities.Vehicule;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zidah
 */
public class UserService implements IDao<User> {

    @Override
    public boolean create(User o) {
         try {
            String req = "insert into auth values (null,?,?)";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setString(1, o.getUser());
            pr.setString(2, o.getPassword());
            
            if (pr.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(User o) {
         try {
            String req = "update auth set password = ?  where id = ?";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setString(1,o.getPassword());
            pr.setInt(2,o.getId());
           
            if (pr.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User o) {
           System.out.println(o.getId());
        try {
            String req = "delete from auth where id = ?";
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
    public User findById(int id) {
         try {
            String req = "select * from auth where id = ?";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next())
                return new User(rs.getInt("id"),rs.getString("user"),rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User("","");
    }

    @Override
    public List<User> findAll() {
 List<User> users = new ArrayList<User>();
        try {
            String req = "select * from auth";
            PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
            while (rs.next())
                users.add(new User(rs.getInt("id"),rs.getString("user"),rs.getString("password")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    
    }
}
