/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import service.HelloWorld;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import connexion.Connexion;
import entities.Position;
import entities.Tracker;
import entities.Vehicule;
import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.sql.*;
import java.util.*;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import service.PositionService;
import service.TrackerService;

/**
 *
 * @author zidah
 */
public class AfficherPositionForm extends javax.swing.JInternalFrame {
      private static final AfficherPositionForm instane =new AfficherPositionForm();
 private PositionService ps;
 private TrackerService ts;
    private DefaultTableModel model;
    private static int id;
    /**
     * Creates new form AfficherPositionForm
     */
    public AfficherPositionForm() {
        initComponents();
           ts = new TrackerService();
         ps = new PositionService();
        model = (DefaultTableModel) listePositionsbetwin.getModel();
        model.setRowCount(0);
        loadTrackers();
    }
    public static final AfficherPositionForm getInstance() {
  		return instane ;
  	}
     private void loadTrackers() {
        for (Tracker t : ts.findAll()) {
            listeTrackersbetwin.addItem(t);
        }
    }
private List<Position> findpositionBetwindates(Tracker tracker, Date dated, Date datef){
    //System.out.println(new java.sql.Date(dated.getTime()));
    List <Position> positions = new ArrayList<Position>();
  try {
      
            String req = "SELECT * FROM `position`,tracker WHERE position.idTracker = tracker.id and  tracker.simNumber=? AND date BETWEEN '"+new java.sql.Date(dated.getTime())+"' and '"+new java.sql.Date(datef.getTime())+"' ";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
           
            // pr.setInt(1,tracker.getId() );
             pr.setString(1,tracker.getSimNumber() );
            // pr.setDate(3,new java.sql.Date(dated.getDate()));
            // pr.setDate(4, new java.sql.Date(datef.getDate()));
            ResultSet rs = pr.executeQuery();
           
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                positions.add(new Position(rs.getInt("id"),rs.getDouble("latitude"), rs.getDouble("longitude"),rs.getDate("Date"),ts.findById(rs.getInt("idTracker"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        for (Position p : positions ) {
            System.out.println(p+"iciiii");
            model.addRow(new Object[]{
                p.getId(),
                p.getLatitude(),
                p.getLongitude(),
                p.getDate(),
                p.getTracker().getSimNumber()
            });
        }
    return  positions;
    
}
private int findbysimtracker(String sim) {
    
                List<Tracker> trackers = new ArrayList<Tracker>();
        try {
            String req = "SELECT tracker.id FROM tracker WHERE tracker.simNumber=? ";
           PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setString(1, sim);
            
            ResultSet rs = pr.executeQuery();
           
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
               
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
     return 0;   
        
      
    }

 /*  private void loadPositins() {
        model.setRowCount(0);
        for (Position p : findpositionBetwindates() ) {
            model.addRow(new Object[]{
                p.getId(),
                p.getLatitude(),
                p.getLongitude(),
                p.getDate(),
                p.getTracker()
            });
        }
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        listeTrackersbetwin = new javax.swing.JComboBox();
        datedTxt = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        datefTxt = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listePositionsbetwin = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel3.setBackground(new java.awt.Color(51, 255, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-positioning-100.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addContainerGap(574, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Entrer la date de début : ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tracker : ");

        listeTrackersbetwin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listeTrackersbetwinActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Entrer la date de fin : ");

        jButton1.setText("Afficher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listePositionsbetwin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Latitude", "Longitude", "Date", "Tracker"
            }
        ));
        listePositionsbetwin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listePositionsbetwinMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listePositionsbetwin);

        jButton3.setText("Afficher dans la map");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(listeTrackersbetwin, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(120, 120, 120)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(datedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(121, 121, 121)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(datefTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listeTrackersbetwin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datefTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listePositionsbetwinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listePositionsbetwinMouseClicked
        // TODO add your handling code here:
    /*    id = Integer.parseInt(model.getValueAt(listePositionsbetwin.getSelectedRow(), 0).toString());
        latitudeTxt.setText(model.getValueAt(listePositionsbetwin.getSelectedRow(), 1).toString());
        longitudeTxt.setText(model.getValueAt(listePositionsbetwin.getSelectedRow(), 2).toString());
        datedTxt.setDate((Date) model.getValueAt(listePositionsbetwin.getSelectedRow(), 3));
        listeTrackersbetwin.setSelectedItem((Tracker)model.getValueAt(listePositionsbetwin.getSelectedRow(), 4));*/
    }//GEN-LAST:event_listePositionsbetwinMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         model.setRowCount(0);
        System.out.println( listeTrackersbetwin.getSelectedItem()); 
    String st =  listeTrackersbetwin.getSelectedItem().toString();
  //     System.out.println(st); 
        java.util.Date dated =  datedTxt.getDate();
        java.util.Date datef =  datefTxt.getDate();
        int idt1 =  findbysimtracker( st);
       System.out.println(idt1); 
          System.out.println(ts.findById(idt1));
       findpositionBetwindates(ts.findById(idt1),dated,datef);
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listeTrackersbetwinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listeTrackersbetwinActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_listeTrackersbetwinActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        model.setRowCount(0);
         String st =  listeTrackersbetwin.getSelectedItem().toString();
      System.out.println(st); 
        /* List <Position> p = new ArrayList<>();
         p.add(new Position(75,32.074378, -9.327248,new Date(),ts.findById(1)));
          
          p.add(new Position(79,33.124473,-8.616826,new Date(),ts.findById(1)));
          p.add(new Position(77,32.78791, -8.959736,new Date(),ts.findById(1)));
          p.add(new Position(179,33.244152, -8.484570,new Date(),ts.findById(1)));
          p.add(new Position(279,33.576974, -7.694706,new Date(),ts.findById(1)));*/
        java.util.Date dated =  datedTxt.getDate();
        java.util.Date datef =  datefTxt.getDate();
        int idt1 =  findbysimtracker( st);
       System.out.println(idt1); 
          System.out.println(ts.findById(idt1));
     //findpositionBetwindates(ts.findById(idt1),dated,datef);
        double a = 31.665696;
        double b = -8.029273;
           MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        options.setApiKey("AIzaSyDWvcYmuSVPuUfQ9YVWwbqBHjgBAjQp6zM");
              
        final HelloWorld mapView = new HelloWorld(options,findpositionBetwindates(ts.findById(idt1),dated,datef));
   
                   mapView.getMap();
                   //map.setCenter(new LatLng((com.teamdev.jxmaps.Map) map, 31.665696,-8.029273));
                   
                  // map.setZoom(8);
                 //GeocoderRequest request = new GeocoderRequest(map);
                 //request.setLocation(new LatLng(31.665696,-8.029273));
        JFrame frame = new JFrame("JxMaps - Hello, World!");
     //jPanel4.add(mapView);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(mapView, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
           try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
    /* MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        options.setApiKey("AIzaSyDWvcYmuSVPuUfQ9YVWwbqBHjgBAjQp6zM");
     final MarkersExample sample = new MarkersExample();

        JFrame frame = new JFrame("Markers");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(sample, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      */  
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser datedTxt;
    private com.toedter.calendar.JDateChooser datefTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listePositionsbetwin;
    private javax.swing.JComboBox listeTrackersbetwin;
    // End of variables declaration//GEN-END:variables
}
