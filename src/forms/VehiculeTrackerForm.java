/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import connexion.Connexion;
import entities.Position;
import entities.Tracker;
import entities.Vehicule;
import entities.VehiculeGpsTrqcker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.TrackerService;
import service.VehiculeGpsTrackerService;
import service.vehiculeService;


/**
 *
 * @author zidah
 */
public class VehiculeTrackerForm extends javax.swing.JInternalFrame {
private DefaultTableModel model;
private DefaultTableModel model1;
private DefaultTableModel model2;
    private static int id4;
     vehiculeService vs = new vehiculeService();
        TrackerService ts = new TrackerService();
     VehiculeGpsTrackerService vgs = new VehiculeGpsTrackerService();
     private static final VehiculeTrackerForm instane =new VehiculeTrackerForm();
    /**
     * Creates new form VehiculeTrackerForm
     */
    public VehiculeTrackerForm() {
        initComponents();
         choix.removeAllItems();
        chox.removeAllItems();
        loadAvailabletracker();
        loadAvailableVehicule();
        model = (DefaultTableModel) listevehiculetracker.getModel();
        model1 = (DefaultTableModel) listeTracker2.getModel();
         model2 = (DefaultTableModel) listevehicule2.getModel();
        load();
        loadt();
        loadv();
        
    }
     public static final VehiculeTrackerForm getInstance() {
  		return instane ;
  	}
    
     private void loadt(){
         List<Tracker> trackers = new ArrayList<Tracker>();
        try {
           String req = "SELECT tracker.id,tracker.simNumber FROM tracker WHERE tracker.id not in (SELECT vehiculegpstracker.id_tracker FROM vehiculegpstracker) group by id  \n" +
"UNION \n" +
"SELECT  tracker.id,tracker.simNumber FROM tracker,vehiculegpstracker WHERE(vehiculegpstracker.datef is NOT null and vehiculegpstracker.id_tracker=tracker.id) GROUP by tracker.id ";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                trackers.add(new Tracker(rs.getInt("id"), rs.getString("simNumber")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
         model1.setRowCount(0);
        
        for (Tracker t : trackers) {
          
              model1.addRow(new Object[]{
                t.getId(),
                
                t.getSimNumber()
                
            });
            
        }
         
     }
     private void loadv(){
          List<Vehicule> vehicules = new ArrayList<Vehicule>();
              try {
            String req = "SELECT vehicule.id,vehicule.matricule,vehicule.marque,vehicule.type FROM vehicule WHERE (vehicule.id not in (SELECT vehiculegpstracker.id_vehicule FROM vehiculegpstracker)) GROUP by vehicule.id UNION SELECT vehicule.id,vehicule.matricule,vehicule.marque,vehicule.type FROM vehiculegpstracker,vehicule WHERE(vehiculegpstracker.datef is NOT null and vehiculegpstracker.id_vehicule=vehicule.id) GROUP by vehicule.id ";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
           
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                vehicules.add(new Vehicule(rs.getInt("id"), rs.getString("matricule"),rs.getString("marque"),rs.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
         model2.setRowCount(0);
        for (Vehicule v : vehicules) {
          
              model2.addRow(new Object[]{
                v.getId(),
                v.getMatricule(),
                v.getMarque(),
                v.getType()
                
                
                
            });
            
        }
         
     }
     
    
    
     private void load() {
        model.setRowCount(0);
        
        for (VehiculeGpsTrqcker t : vgs.findAll()){
            System.out.println("-----------------------------------------");
             System.out.println(t.getId());
              System.out.println(ts.findById(t.getId_ts()));
        System.out.println(vs.findById(t.getId_vs()));
      
        int id2= t.getId_vs();
            model.addRow(new Object[]{
                t.getId(),
                vs.findById(id2).getMatricule(),
                ts.findById(t.getId_ts()).getSimNumber(),
                t.getDateDebut()
                
            });
     
    }}
    private void loadAvailabletracker() {
  chox.removeAllItems();
    
                List<Tracker> trackers = new ArrayList<Tracker>();
        try {
            String req = "SELECT tracker.id,tracker.simNumber FROM tracker WHERE tracker.id not in (SELECT vehiculegpstracker.id_tracker FROM vehiculegpstracker) group by id  \n" +
"UNION \n" +
"SELECT  tracker.id,tracker.simNumber FROM tracker,vehiculegpstracker WHERE(vehiculegpstracker.datef is NOT null and vehiculegpstracker.id_tracker=tracker.id) GROUP by tracker.id ";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                trackers.add(new Tracker(rs.getInt("id"), rs.getString("simNumber")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
        for (Tracker t : trackers) {
            System.out.println(t.getSimNumber());
            
            chox.addItem(t.getSimNumber());
        }
    }
private void loadAvailableVehicule() {
    choix.removeAllItems();
    
                List<Vehicule> vehicules = new ArrayList<Vehicule>();
        try {
            String req = "SELECT vehicule.id,vehicule.matricule,vehicule.marque,vehicule.type FROM vehicule WHERE (vehicule.id not in (SELECT vehiculegpstracker.id_vehicule FROM vehiculegpstracker)) GROUP by vehicule.id UNION SELECT vehicule.id,vehicule.matricule,vehicule.marque,vehicule.type FROM vehiculegpstracker,vehicule WHERE(vehiculegpstracker.datef is NOT null and vehiculegpstracker.id_vehicule=vehicule.id) GROUP by vehicule.id ";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = pr.executeQuery();
           
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                vehicules.add(new Vehicule(rs.getInt("id"), rs.getString("matricule"),rs.getString("marque"),rs.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
        for (Vehicule v : vehicules) {
            System.out.println(v.getMatricule());
            
            choix.addItem(v.getMatricule());
        }
    }
private int findbyMatricule(String mat) {
    
                List<Vehicule> vehicules = new ArrayList<Vehicule>();
        try {
            String req = "SELECT vehicule.id FROM vehicule WHERE vehicule.matricule=? ";
           PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
            pr.setString(1, mat);
            
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


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listevehiculetracker = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listevehicule2 = new javax.swing.JTable();
        affecter = new javax.swing.JButton();
        choix = new javax.swing.JComboBox<>();
        chox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        listeTracker2 = new javax.swing.JTable();
        desaffecter = new javax.swing.JButton();
        datedTxt = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 255, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-car-repair-128.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel8.setText("Affectation et désaffectation des trackers");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setText("Vehicule");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel6.setText("Tracker");

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel7.setText("Liste des véhicules disponibles");

        listevehiculetracker.setForeground(new java.awt.Color(51, 255, 255));
        listevehiculetracker.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "vehicule", "tracker", "Date d'affectation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        listevehiculetracker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listevehiculetrackerMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listevehiculetracker);

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel9.setText("Liste des trackers disponibles");

        listevehicule2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Matricule", "Marque", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(listevehicule2);

        affecter.setText("Affectation");
        affecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                affecterActionPerformed(evt);
            }
        });

        choix.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixActionPerformed(evt);
            }
        });

        chox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        listeTracker2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Sim Number"
            }
        ));
        jScrollPane1.setViewportView(listeTracker2);

        desaffecter.setText("desaffectation");
        desaffecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desaffecterActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Entrer la date de début : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(desaffecter, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addComponent(affecter, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                        .addComponent(jScrollPane5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel7)))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(303, 303, 303))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choix, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chox, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(datedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(149, 149, 149))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(choix, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(desaffecter, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(affecter, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void affecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_affecterActionPerformed
 
        Date dated = datedTxt.getDate();
        //Date datef =  new Date("0/0/0");
        int idv =  findbyMatricule((String) choix.getSelectedItem());
        int idt =  findbysimtracker((String) chox.getSelectedItem());
        if (vgs.create(new VehiculeGpsTrqcker(idv,idt,dated))) {
            JOptionPane.showMessageDialog(this, "tracker est bien affecté au vehicule");
             loadAvailabletracker();
        loadAvailableVehicule();
            load();
            loadt();
            loadv();
        }
    }//GEN-LAST:event_affecterActionPerformed

    private void choixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choixActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choixActionPerformed

    private void desaffecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desaffecterActionPerformed
        // TODO add your handling code here:
        System.out.println("////////////////////////////////////////////////////////////////////////////////////");
           System.out.println(id4);
            System.out.println(vgs.findById(id4));
        int reponse = JOptionPane.showConfirmDialog(this, "Voulez vous vraiment supprimer cette association ! ");
     
        if (reponse == 0 && id4 != 0) {
              
            try {
               
                    
                    if (vgs.update(vgs.findById(id4)));
                     String req = "insert into VehiculeGpsTrackerh values (?,?,?,?,?) ";
                PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
                pr.setInt(1, vgs.findById(id4).getId());
                pr.setInt(2, vgs.findById(id4).getId_vs());
                pr.setDate(3, new java.sql.Date(vgs.findById(id4).getDateDebut().getTime()));
                pr.setDate(4, new java.sql.Date(vgs.findById(id4).getDateFin().getTime()));
                pr.setInt(5, vgs.findById(id4).getId_ts());
                if (pr.executeUpdate() != 0){
                        if (vgs.delete(vgs.findById(id4))) {
                        JOptionPane.showMessageDialog(this, "desafectation reussite !!");
                        loadAvailabletracker();
                        loadAvailableVehicule();
                        load();
                        loadt();
                        loadv(); 
                    }
                }
                    
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
        
        
        
    }//GEN-LAST:event_desaffecterActionPerformed
    }
    private void listevehiculetrackerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listevehiculetrackerMouseClicked
id4 = Integer.parseInt(model.getValueAt(listevehiculetracker.getSelectedRow(), 0).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_listevehiculetrackerMouseClicked
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton affecter;
    private javax.swing.JComboBox<String> choix;
    private javax.swing.JComboBox<String> chox;
    private com.toedter.calendar.JDateChooser datedTxt;
    private javax.swing.JButton desaffecter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable listeTracker2;
    private javax.swing.JTable listevehicule2;
    private javax.swing.JTable listevehiculetracker;
    // End of variables declaration//GEN-END:variables
}
