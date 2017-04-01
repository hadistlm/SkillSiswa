/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package skillsiswa;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.TextField;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author acer
 */
public class MainForm extends javax.swing.JFrame {
     private Connection konek;

    /**
     * Creates new form MainForm
     */
    public MainForm() throws ClassNotFoundException, SQLException {
        initComponents();
        openKoneksi();
        
        TextAutoCompleter complete=new TextAutoCompleter(txt_nama);
        DBConn conn=new DBConn();
        conn.connection();
        conn.retrieve();
        while(conn.rs.next()){
            complete.addItem(conn.rs.getString("nama"));
        }
    }

    private void openKoneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
                    
            String host = "localhost";
            String port = "3306";
            String db = "db_skill_siswa";
            String username = "root";
            String password = "";
                    
            konek = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, username, password);
            System.out.println("Koneksi Berhasil");
        }
            catch (ClassNotFoundException ex){
                    System.out.println("Class Driver tidak ditemukan");
                    }
            catch (SQLException sql){
                    System.out.println("Koneksi ke database gagal");
                }
    }
    
    private List getData(){
        List dataSiswa = new ArrayList();
        String tampilData = "SELECT t_siswa.nis, t_siswa.nama, t_kelas.kelas, t_nilai.n_agama,t_nilai.n_bind,t_nilai.n_bing, t_nilai.n_fisika, t_nilai.n_kesenian, t_nilai.n_kewirausahaan, t_nilai.n_kimia, t_nilai.n_mtk, t_nilai.n_olahraga, t_nilai.n_pkn, t_nilai.n_produktif, t_nilai.n_sosial\n" +
                            "FROM t_siswa, t_kelas, t_nilai\n" +
                            "WHERE t_siswa.nis=t_nilai.nis AND t_siswa.id_kelas=t_kelas.id_kelas AND t_siswa.nama='"+txt_nama.getText()+"';";
        try{
            Statement stmt = konek.createStatement();
            ResultSet res = stmt.executeQuery(tampilData);
            
            if (res != null){
                while (res.next()){
                    String nis = res.getString("nis");
                    String nama = res.getString("nama");
                    String kelas = res.getString("kelas");
                    float n_agama = res.getFloat("n_agama");
                    float n_bind = res.getFloat("n_bind");
                    float n_bing = res.getFloat("n_bing");
                    float n_fisika = res.getFloat("n_fisika");
                    float n_kesenian = res.getFloat("n_kesenian");
                    float n_kewirausahaan = res.getFloat("n_kewirausahaan");
                    float n_kimia = res.getFloat("n_kimia");
                    float n_mtk = res.getFloat("n_mtk");
                    float n_olahraga = res.getFloat("n_olahraga");
                    float n_pkn = res.getFloat("n_pkn");
                    float n_produktif = res.getFloat("n_produktif");
                    float n_sosial = res.getFloat("n_sosial");
                    
                    ObjekSkillSiswa objSiswa = new ObjekSkillSiswa();//setting data ke objek
                    
                    objSiswa.setNIS(nis);
                    objSiswa.setNAMA(nama);
                    objSiswa.setKELAS(kelas);
                    objSiswa.setNILAI_AGAMA(n_agama);
                    objSiswa.setNILAI_BIND(n_bind);
                    objSiswa.setNILAI_BING(n_bing);
                    objSiswa.setNILAI_FISIKA(n_fisika);
                    objSiswa.setNILAI_KESENIAN(n_kesenian);
                    objSiswa.setNILAI_KEWIRAUSAHAAN(n_kewirausahaan);
                    objSiswa.setNILAI_KIMIA(n_kimia);
                    objSiswa.setNILAI_MTK(n_mtk);
                    objSiswa.setNILAI_OLAHRAGA(n_olahraga);
                    objSiswa.setNILAI_PKN(n_pkn);
                    objSiswa.setNILAI_PRODUKTIF(n_produktif);
                    objSiswa.setNILAI_SOSIAL(n_sosial);
                    
                    dataSiswa.add(objSiswa);//memasukkan objek ke list
                }
            }else{
                JOptionPane.showMessageDialog(null, "Data Kosong");
            }
            
        }catch(SQLException ex){
                    ex.printStackTrace();
                    }
        return dataSiswa;   
    }
    
    private void callReport(){
        try{
            //Masukan List data Ke Koneksi JavaBean
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getData(), false);
            
            HashMap param = new HashMap();
            //Tambahkan jika ada parameter
            
            String reportName = "reportSkillSiswa.jasper";
            
            //Mengecek path laporan
            ClassLoader cl = getClass().getClassLoader();
            InputStream inReportFile = cl.getResourceAsStream(reportName);
            
            //Proses pengisian data ke laporan
            JasperPrint print = JasperFillManager.fillReport(inReportFile, param, dataSource);
            //Menampilkan laporan
            JasperViewer.viewReport(print, false);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 51));

        jButton1.setText("LIHAT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Exo 2.0 Extra Light", 1, 24)); // NOI18N
        jLabel2.setText("LAPORAN KEMAMPUAN SISWA");

        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });

        jLabel1.setText("Masukkan nama yang dicari :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(184, 184, 184))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(202, 202, 202))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton1)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        callReport();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])throws ClassNotFoundException,
    SQLException {
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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainForm().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txt_nama;
    // End of variables declaration//GEN-END:variables
}
