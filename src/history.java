import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane; 
/**
 *
 * @author DELL
 */

public class history extends javax.swing.JFrame {
 
   private Connection koneksi;
 
    /**
     * Creates new form history
     */
     private void initializeDatabaseConnection() {
        // Inisialisasi koneksi ke database
        koneksi = Koneksi.getKoneksi();
        if (koneksi == null) {
            JOptionPane.showMessageDialog(this, "Gagal terhubung ke database.", "Kesalahan Koneksi", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    public history() {
        initComponents();
        initializeDatabaseConnection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_check = new javax.swing.JToggleButton();
        history = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_check.setOpaque(false);
        btn_check.setContentAreaFilled(false);
        btn_check.setBorderPainted(false);
        btn_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_checkActionPerformed(evt);
            }
        });
        getContentPane().add(btn_check, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 225, 190, 40));

        history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui componen/historyy.png"))); // NOI18N
        getContentPane().add(history, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_checkActionPerformed
  showHistoryData();
        // Kembali ke frame Menu
        Menu menuFrame = new Menu();
        menuFrame.setVisible(true);

        // Tutup frame saat ini (opsional, tergantung pada kebutuhan)
        this.dispose();
    }//GEN-LAST:event_btn_checkActionPerformed
private void showHistoryData() {
        // Ambil dan tampilkan data histori dari database di JTextArea
        try {
            if (koneksi == null) {
                initializeDatabaseConnection();
                if (koneksi == null) {
                    JOptionPane.showMessageDialog(this, "Gagal terhubung ke database.", "Kesalahan Koneksi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Query untuk mengambil data histori dari database
            String query = "SELECT * FROM history";
            try (PreparedStatement preparedStatement = koneksi.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Tampilkan data histori di JTextArea
                    StringBuilder historyData = new StringBuilder();
                    while (resultSet.next()) {
                        String idPasien = resultSet.getString("id_pasien");
                        String hasilDiagnosis = resultSet.getString("hasil_diagnosis");
                        historyData.append("ID Pasien: ").append(idPasien).append(", Hasil Diagnosis: ").append(hasilDiagnosis).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, historyData.toString(), "History", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal mengambil data histori dari database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
            java.util.logging.Logger.getLogger(history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new history().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_check;
    private javax.swing.JLabel history;
    // End of variables declaration//GEN-END:variables
}
