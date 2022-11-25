/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import connexion.Connexion;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lachgar
 */
public class Auth extends javax.swing.JFrame {
private static final Auth instane =new Auth();
    /**
     * Creates new form Auth
     */
    public Auth() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    public static final Auth getInstance() {
  		return instane ;
  	}
 //fonction de hashage
    
    public String passe(String password1)throws Exception
    {
     String password =password1 ;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

      /*  //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("En format hexa : " + sb.toString());*/

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
     System.out.println("En format hexa : " + hexString.toString());
     return hexString.toString();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        list1 = new java.awt.List();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        loginTxt = new javax.swing.JTextField();
        passwordTxt = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        connexionBn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Authetification");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 255, 204));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-compte-test-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel4)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(405, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 568));

        loginTxt.setBackground(new java.awt.Color(0, 204, 204));
        loginTxt.setForeground(new java.awt.Color(255, 255, 255));
        loginTxt.setText("Username");
        loginTxt.setBorder(null);
        jPanel1.add(loginTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 240, 40));

        passwordTxt.setBackground(new java.awt.Color(0, 204, 204));
        passwordTxt.setForeground(new java.awt.Color(255, 255, 255));
        passwordTxt.setText("Password");
        passwordTxt.setBorder(null);
        jPanel1.add(passwordTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, 230, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-verrouiller-2-50.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 60, 60));

        connexionBn.setBackground(new java.awt.Color(0, 204, 204));
        connexionBn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-connexion-50.png"))); // NOI18N
        connexionBn.setText("Connexion");
        connexionBn.setBorder(null);
        connexionBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionBnActionPerformed(evt);
            }
        });
        jPanel1.add(connexionBn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 170, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 280, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, -1, -1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 332, 280, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connexionBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connexionBnActionPerformed
        // TODO add your handling code here:

        try {

            String login = loginTxt.getText();
            String password = (String) passwordTxt.getText();
            
             

            System.out.println(login);
            System.out.println(password);
            System.out.println(passe(password));
            String req = "select * from auth where user='"+login+"' and password='"+passe(password)+"' ";
            PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
            ResultSet r = pr.executeQuery();
            if (r.next()) {

                JOptionPane.showConfirmDialog(this, "vous etes connecté");
                this.setVisible(false);
                (new Main()).setVisible(true);

            }else{
                JOptionPane.showConfirmDialog(this, "mot de passe ou login incorrect");   
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
        } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchProviderException ex) {
        Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
    }

    }//GEN-LAST:event_connexionBnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Auth().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connexionBn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private java.awt.List list1;
    private javax.swing.JTextField loginTxt;
    private javax.swing.JPasswordField passwordTxt;
    // End of variables declaration//GEN-END:variables
}