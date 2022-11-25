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
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import service.TrackerService;
import service.vehiculeService;

/**
 *
 * @author zidah
 */
public class ChartsFormPositionV extends javax.swing.JInternalFrame {
private static final ChartsFormPositionV instane =new ChartsFormPositionV();
private TrackerService ts;
 private vehiculeService vs;
    /**
     * Creates new form ChartsForm
     */
    private ChartsFormPositionV() {
        initComponents();
        ts = new TrackerService();
         vs = new vehiculeService();
         loadVehicule();
       // loadCharts();
       countpPosition( );
    }
    
 public static final ChartsFormPositionV getInstance() {
  		return instane ;
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
DefaultCategoryDataset dod = new DefaultCategoryDataset();
 private void countpPosition( ){
      
     String year1 = year.getSelectedItem().toString();
     int ma = findbyMatricule(listeVehiculepositionbetwin2.getSelectedItem().toString());
     try {
            String req = "select COUNT(position.id), EXTRACT(month FROM date) as 'mois' ,EXTRACT(year FROM date), position.idTracker , vehiculegpstrackerh.id_vehicule , COUNT(vehiculegpstrackerh.id_vehicule) as 'nombre' from position inner join vehiculegpstrackerh on position.idTracker=vehiculegpstrackerh.id_tracker and position.date <= '2021-12-31' and position.date >= '"+year1+"-01-01' and EXTRACT(year FROM date)='"+year1+"-12-02' and vehiculegpstrackerh.id_vehicule="+ma+" group by EXTRACT(MONTH FROM date), vehiculegpstrackerh.id_vehicule";
           PreparedStatement pr = connexion.Connexion.getConnection().prepareStatement(req);
           // pr.setInt(1, tracker.getId());
            
            ResultSet rs = pr.executeQuery();
           
            while (rs.next()) {
                //System.out.println(rs.getInt("id"));
               
               // return rs.getInt(1);
                 dod.setValue(rs.getInt(1), "nombre de positions ",rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
      JFreeChart jchart1 = ChartFactory.createBarChart("rapport des positions", "Matricule du vehicule","nombre de position", dod, PlotOrientation.VERTICAL,true, true, false);
         CategoryPlot plot = jchart1.getCategoryPlot();
         plot.setRangeGridlinePaint(Color.BLACK);
         ChartFrame chartform = new ChartFrame("rapport des positions",jchart1,true);
         //chartform.setVisible(true);
         //chartform.setSize(500,400);
         ChartPanel chartPanel = new ChartPanel(jchart1);
         chartpanel.removeAll();
         chartpanel.add(chartPanel);
         chartpanel.updateUI();
     
 }
 private void loadVehicule() {
        for (Vehicule v : vs.findAll()) {
            listeVehiculepositionbetwin2.addItem(v.getMatricule());
        }
    }
    public void loadCharts(){
        String year1 = year.getSelectedItem().toString();
         
         
            
            // dod.setValue(countpPosition(), "nombre de positions ",year1);
             
        
        /* dod.setValue(78.80, "nombre de positions ","reda" );
         dod.setValue(80.12, "marks2","amiiine" );
         dod.setValue(50.80, "marks3","anass" );
         dod.setValue(98.80, "marks6","zikooo" );*/
         JFreeChart jchart1 = ChartFactory.createBarChart("rapport des positions", "Matricule du vehicule","nombre de position", dod, PlotOrientation.VERTICAL,true, true, false);
         CategoryPlot plot = jchart1.getCategoryPlot();
         plot.setRangeGridlinePaint(Color.BLACK);
         ChartFrame chartform = new ChartFrame("rapport des positions",jchart1,true);
         //chartform.setVisible(true);
         //chartform.setSize(500,400);
         ChartPanel chartPanel = new ChartPanel(jchart1);
         chartpanel.removeAll();
         chartpanel.add(chartPanel);
         chartpanel.updateUI();
         
         
        
    }

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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        listeVehiculepositionbetwin2 = new javax.swing.JComboBox();
        year = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        chartpanel = new javax.swing.JPanel();
        chartpanel1 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel3.setBackground(new java.awt.Color(51, 255, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-businessman-100.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1)
                .addContainerGap(621, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Année");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Vehicule");

        listeVehiculepositionbetwin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listeVehiculepositionbetwin2ActionPerformed(evt);
            }
        });

        year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2020", "2021" }));

        jButton1.setText("Afficher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listeVehiculepositionbetwin2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(172, 172, 172)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(455, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listeVehiculepositionbetwin2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        chartpanel.setBackground(new java.awt.Color(255, 255, 255));
        chartpanel.setLayout(new javax.swing.BoxLayout(chartpanel, javax.swing.BoxLayout.LINE_AXIS));

        chartpanel1.setBackground(new java.awt.Color(255, 255, 255));
        chartpanel1.setLayout(new javax.swing.BoxLayout(chartpanel1, javax.swing.BoxLayout.LINE_AXIS));
        chartpanel.add(chartpanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 237, Short.MAX_VALUE)
                    .addComponent(chartpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1349, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 110, Short.MAX_VALUE)
                    .addComponent(chartpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listeVehiculepositionbetwin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listeVehiculepositionbetwin2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listeVehiculepositionbetwin2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        countpPosition();
     /*  String year1 = year.getSelectedItem().toString();
         DefaultCategoryDataset dod = new DefaultCategoryDataset(); 
         
            
             dod.setValue(countpPosition(), "nombre de positions ",year1);
             */
        
        /* dod.setValue(78.80, "nombre de positions ","reda" );
         dod.setValue(80.12, "marks2","amiiine" );
         dod.setValue(50.80, "marks3","anass" );
         dod.setValue(98.80, "marks6","zikooo" );*/
        /* JFreeChart jchart1 = ChartFactory.createBarChart("rapport des positions", "Matricule du vehicule","nombre de position", dod, PlotOrientation.VERTICAL,true, true, false);
         CategoryPlot plot = jchart1.getCategoryPlot();
         plot.setRangeGridlinePaint(Color.BLACK);
         ChartFrame chartform = new ChartFrame("rapport des positions",jchart1,true);
         chartform.setVisible(true);
         chartform.setSize(500,400);
         ChartPanel chartPanel = new ChartPanel(jchart1);
         chartpanel.removeAll();
         chartpanel.add(chartPanel);
         chartpanel.updateUI();*/
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartpanel;
    private javax.swing.JPanel chartpanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox listeVehiculepositionbetwin2;
    private javax.swing.JComboBox<String> year;
    // End of variables declaration//GEN-END:variables
}
