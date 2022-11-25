/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package forms;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.sun.prism.paint.Color;
import connexion.Connexion;
import entities.Position;
import entities.Tracker;
import entities.Vehicule;
import static java.awt.Frame.NORMAL;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import service.PositionService;
import service.TrackerService;
import service.vehiculeService;

/**
 *
 * @author zidah
 */
public class ReportForm extends javax.swing.JInternalFrame {
private static final ReportForm instane =new ReportForm();

 private PositionService ps;
 private TrackerService ts;
 private vehiculeService vs;
  private vehiculeService vs1;
    private DefaultTableModel model;
    private static int id;
    /** Creates new form ReportForm */
    public ReportForm() {
        initComponents();
        vs = new vehiculeService();
        vs1=new vehiculeService();
        ts = new TrackerService();
         ps = new PositionService();
        model = (DefaultTableModel) listeVehiculesPositionsbetwinpdf.getModel();
        model.setRowCount(0);
        loadVehicule();
    }
public static final ReportForm getInstance() {
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
   private List<Position> findpositionBetwindates(Tracker tracker, Date dated, Date datef){
        //model.setRowCount(0);
    System.out.println(new java.sql.Date(dated.getTime()));
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
    private List<Position> findpositionBetwindatesv(Vehicule v, Date dated, Date datef){
   
    List <Position> positions1 = new ArrayList<Position>();
    List <Vehicule> vehicules= new ArrayList<Vehicule>();
    List <Tracker> trackers= new ArrayList<Tracker>();
        //for(Vehicule v1 : vs.findAll()){
            
            try {
      
            String req = "select DISTINCT vehiculegpstrackerh.id_tracker FROM vehiculegpstrackerh WHERE vehiculegpstrackerh.id_vehicule=? and vehiculegpstrackerh.dated BETWEEN '"+new java.sql.Date(dated.getTime())+"' and '"+new java.sql.Date(datef.getTime())+"' and vehiculegpstrackerh.datef BETWEEN '"+new java.sql.Date(dated.getTime())+"' and '"+new java.sql.Date(datef.getTime())+"'";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
           
            // pr.setInt(1,tracker.getId() );
             pr.setInt(1,v.getId() );
            // pr.setDate(3,new java.sql.Date(dated.getDate()));
            // pr.setDate(4, new java.sql.Date(datef.getDate()));
            ResultSet rs = pr.executeQuery();
           
            while (rs.next()) {
                //System.out.println(rs.getInt("id"));
                trackers.add(ts.findById(rs.getInt("id_tracker")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
                try {
      
            String req = "select DISTINCT vehiculegpstracker.id_tracker FROM vehiculegpstracker WHERE vehiculegpstracker.id_vehicule=? and vehiculegpstracker.dated BETWEEN '"+new java.sql.Date(dated.getTime())+"' and '"+new java.sql.Date(datef.getTime())+"'";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
           
            // pr.setInt(1,tracker.getId() );
             pr.setInt(1,v.getId() );
            // pr.setDate(3,new java.sql.Date(dated.getDate()));
            // pr.setDate(4, new java.sql.Date(datef.getDate()));
            ResultSet rs = pr.executeQuery();
           
            while (rs.next()) {
                //System.out.println(rs.getInt("id"));
                trackers.add(ts.findById(rs.getInt("id_tracker")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            
        //}
            for(Tracker t : trackers){
             //findpositionBetwindates(t,dated,datef);
             
             for(Position p : findpositionBetwindates(t,dated,datef) ){
                 
                 positions1.add(p);
             }
            }
             //}
             
             
             
            //}
            
            return positions1;
            
         
       
    
    
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
private void loadVehicule() {
        for (Vehicule v : vs.findAll()) {
            listeVehiculepositionbetwin.addItem(v.getMatricule());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listeVehiculesPositionsbetwinpdf = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        listeVehiculepositionbetwin = new javax.swing.JComboBox();
        datedTxt = new com.toedter.calendar.JDateChooser();
        datefTxt = new com.toedter.calendar.JDateChooser();

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel4.setBackground(new java.awt.Color(51, 255, 204));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-positioning-100.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(685, 685, 685))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel2)
                .addContainerGap(562, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        listeVehiculesPositionsbetwinpdf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Latitude", "Longitude", "Date", "Tracker"
            }
        ));
        listeVehiculesPositionsbetwinpdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listeVehiculesPositionsbetwinpdfMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listeVehiculesPositionsbetwinpdf);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-pdf-30.png"))); // NOI18N
        jButton1.setText("Generer le pdf");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Vehicule");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Entrer la date de début : ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Entrer la date de fin : ");

        listeVehiculepositionbetwin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listeVehiculepositionbetwinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(432, 432, 432))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(listeVehiculepositionbetwin, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(datedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(datefTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(108, 108, 108)
                                .addComponent(jLabel8))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(163, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(listeVehiculepositionbetwin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datefTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listeVehiculesPositionsbetwinpdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listeVehiculesPositionsbetwinpdfMouseClicked
        // TODO add your handling code here:
        /*    id = Integer.parseInt(model.getValueAt(listePositionsbetwin.getSelectedRow(), 0).toString());
        latitudeTxt.setText(model.getValueAt(listePositionsbetwin.getSelectedRow(), 1).toString());
        longitudeTxt.setText(model.getValueAt(listePositionsbetwin.getSelectedRow(), 2).toString());
        datedTxt.setDate((Date) model.getValueAt(listePositionsbetwin.getSelectedRow(), 3));
        listeTrackersbetwin.setSelectedItem((Tracker)model.getValueAt(listePositionsbetwin.getSelectedRow(), 4));*/
    }//GEN-LAST:event_listeVehiculesPositionsbetwinpdfMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        System.out.println( listeVehiculepositionbetwin.getSelectedItem()); 
    String vt = listeVehiculepositionbetwin.getSelectedItem().toString();
  //     System.out.println(st); 
        java.util.Date dated =  datedTxt.getDate();
        java.util.Date datef =  datefTxt.getDate();
        int idt1 = findbyMatricule(vt) ;
        System.out.println("le matricule du vehicule est:");
       System.out.println(idt1); 
          System.out.println(vs.findById(idt1));
            model.setRowCount(0);
       findpositionBetwindatesv(vs.findById(idt1),dated,datef);
       
        String path="";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x=j.showSaveDialog(this);
        if(x==JFileChooser.APPROVE_OPTION)
        {
            path=j.getSelectedFile().getPath();
        }
        Document doc = new Document();
        try{
            PdfWriter.getInstance(doc,new FileOutputStream(path+".pdf"));
            doc.open();
            PdfPTable table = new PdfPTable(4);
            //doc.addTitle("nombre des positiondu vehicule "+idt1);
            //adding headers
            table.addCell("identité de position");
            table.addCell("latitude");
            table.addCell("longitude");
            table.addCell("date");
            
            for(int i =0;i<listeVehiculesPositionsbetwinpdf.getRowCount();i++){
                String id = listeVehiculesPositionsbetwinpdf.getValueAt(i,0).toString();
                String latitude = listeVehiculesPositionsbetwinpdf.getValueAt(i,1).toString();
                String longitude = listeVehiculesPositionsbetwinpdf.getValueAt(i,2).toString();
                String date = listeVehiculesPositionsbetwinpdf.getValueAt(i,3).toString();
                      
                 
                table.addCell(id);
                table.addCell(latitude);
                table.addCell(longitude);
                table.addCell(date);
                       }    
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(50);
            font.setColor(BaseColor.BLACK);
            Paragraph p = new Paragraph("nombre des positiondu vehicule "+idt1);
            Paragraph p1 = new Paragraph("");
            p.setAlignment(p.ALIGN_CENTER);
            //doc.addTitle("nombre des positiondu vehicule "+idt1);
            doc.add(p);
            doc.add(p1);
                        doc.add(table);
            
            
        }catch(FileNotFoundException ex){
            //Logger.getLogger(ReportForm.class.getName().log(Level.SEVERE),null,ex);
            ex.printStackTrace();
        }catch(DocumentException ex){
            //Logger.getLogger(ReportForm.class.getName().log(Level.SEVERE),null,ex);
            ex.printStackTrace();
        }
        doc.close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listeVehiculepositionbetwinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listeVehiculepositionbetwinActionPerformed

        // TODO add your handling code here:

    }//GEN-LAST:event_listeVehiculepositionbetwinActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser datedTxt;
    private com.toedter.calendar.JDateChooser datefTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox listeVehiculepositionbetwin;
    private javax.swing.JTable listeVehiculesPositionsbetwinpdf;
    // End of variables declaration//GEN-END:variables

}
