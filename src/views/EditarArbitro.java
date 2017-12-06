package views;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sources.GestionarArbitros;
import sources.MostrarDatos;

/**
 *
 * @author rob_a
 */
public class EditarArbitro extends javax.swing.JDialog {

    private final MostrarDatos md = new MostrarDatos();
    private final GestionarArbitros ga = new GestionarArbitros();


    /**
     * Creates new form EditarCampo
     */
    public EditarArbitro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new java.awt.Color(232, 245, 228));
        this.cmbArbitros.addItem("");
        md.llenarComboEditarArbitros(cmbArbitros);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogoAgregarEquipo = new javax.swing.JLabel();
        txtNombreArbitro = new javax.swing.JTextField();
        btnEditarArbitro = new javax.swing.JButton();
        btnCancelarArbitro = new javax.swing.JButton();
        lblAgreagarEquipo = new javax.swing.JLabel();
        chkEspecial = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtApellidoArbitro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbArbitros = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar equipo");
        setBackground(new java.awt.Color(0, 0, 0));

        lblLogoAgregarEquipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/equipos_menu.png"))); // NOI18N

        btnEditarArbitro.setBackground(new java.awt.Color(31, 87, 12));
        btnEditarArbitro.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarArbitro.setText("Aceptar");
        btnEditarArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarArbitroActionPerformed(evt);
            }
        });

        btnCancelarArbitro.setBackground(new java.awt.Color(31, 87, 12));
        btnCancelarArbitro.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarArbitro.setText("Cancelar");
        btnCancelarArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarArbitroActionPerformed(evt);
            }
        });

        lblAgreagarEquipo.setBackground(new java.awt.Color(31, 87, 12));
        lblAgreagarEquipo.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        lblAgreagarEquipo.setForeground(new java.awt.Color(31, 87, 12));
        lblAgreagarEquipo.setText("Editar Arbitro");

        chkEspecial.setText("Especiales");
        chkEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEspecialActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel2.setText("Apellidos");

        jLabel3.setText("Seleciona");

        cmbArbitros.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbArbitrosItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 151, Short.MAX_VALUE)
                        .addComponent(btnEditarArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombreArbitro)
                    .addComponent(txtApellidoArbitro)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblLogoAgregarEquipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAgreagarEquipo))
                            .addComponent(chkEspecial)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmbArbitros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLogoAgregarEquipo)
                    .addComponent(lblAgreagarEquipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbArbitros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellidoArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkEspecial)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarArbitro)
                    .addComponent(btnCancelarArbitro))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarArbitroActionPerformed
        String arbitro = cmbArbitros.getSelectedItem().toString();
        String nombre = txtNombreArbitro.getText();
        String apellido = txtApellidoArbitro.getText();
        int especial = 0;
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
        if (txtNombreArbitro.getText().equals("")){              
            JOptionPane.showMessageDialog(rootPane, "Debe escribir el nombre del árbitro","Nombre del árbitro", JOptionPane.QUESTION_MESSAGE, icon);
        }else{     
            System.out.println(arbitro);
            System.out.println(nombre);
            System.out.println(apellido);
            if(chkEspecial.isSelected()){
                System.out.println("Especial");
                especial = 1;
            }
            try {
                ga.guardarArbitro(nombre, apellido, especial);
            } catch (SQLException ex) {
                Logger.getLogger(EditarArbitro.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }
    }//GEN-LAST:event_btnEditarArbitroActionPerformed

    private void btnCancelarArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarArbitroActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarArbitroActionPerformed

    private void chkEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEspecialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEspecialActionPerformed

    private void cmbArbitrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbArbitrosItemStateChanged
        String completo = cmbArbitros.getSelectedItem().toString();
        String[] nombre = completo.split(" ");
        if(nombre.length > 1){
            this.txtNombreArbitro.setText(nombre[0]);
            this.txtApellidoArbitro.setText(nombre[1]);
        }
    }//GEN-LAST:event_cmbArbitrosItemStateChanged

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
            java.util.logging.Logger.getLogger(EditarArbitro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarArbitro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarArbitro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarArbitro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarArbitro dialog = new EditarArbitro(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarArbitro;
    private javax.swing.JButton btnEditarArbitro;
    private javax.swing.JCheckBox chkEspecial;
    private javax.swing.JComboBox<String> cmbArbitros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblAgreagarEquipo;
    private javax.swing.JLabel lblLogoAgregarEquipo;
    private javax.swing.JTextField txtApellidoArbitro;
    public javax.swing.JTextField txtNombreArbitro;
    // End of variables declaration//GEN-END:variables
}
