/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sources.GestionarEquipos;
import sources.MostrarDatos;

/**
 *
 * @author rob_a
 */
public class EditarEquipo extends javax.swing.JDialog {
    private final MostrarDatos md = new MostrarDatos();
    private final GestionarEquipos ge = new GestionarEquipos();
    private String campoAnterior = null;
    /**
     * Creates new form EditarCampo
     */
    public EditarEquipo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new java.awt.Color(232, 245, 228));
        
        md.llenarComboEquipos(this.cbEditarEquipo);
        md.llenarComboCategorias(this.cbEditarEquipoCategoria);
        md.llenarComboDivisiones(this.cbEditarEquipoDivision);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEditarEquipo = new javax.swing.JLabel();
        btnEditarEquipo = new javax.swing.JButton();
        btnEliminarEquipo = new javax.swing.JButton();
        btnCancelaEquipo = new javax.swing.JButton();
        lblLogoEditarEquipo = new javax.swing.JLabel();
        cbEditarEquipo = new javax.swing.JComboBox<>();
        cbEditarEquipoDivision = new javax.swing.JComboBox<>();
        cbEditarEquipoCategoria = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar equipo");
        setBackground(new java.awt.Color(31, 87, 12));

        lblEditarEquipo.setBackground(new java.awt.Color(31, 87, 12));
        lblEditarEquipo.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        lblEditarEquipo.setForeground(new java.awt.Color(31, 87, 12));
        lblEditarEquipo.setText("Seleccione un equipo");

        btnEditarEquipo.setBackground(new java.awt.Color(31, 87, 12));
        btnEditarEquipo.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarEquipo.setText("Editar");
        btnEditarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEquipoActionPerformed(evt);
            }
        });

        btnEliminarEquipo.setBackground(new java.awt.Color(31, 87, 12));
        btnEliminarEquipo.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarEquipo.setText("Eliminar");
        btnEliminarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEquipoActionPerformed(evt);
            }
        });

        btnCancelaEquipo.setBackground(new java.awt.Color(31, 87, 12));
        btnCancelaEquipo.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelaEquipo.setText("Cancelar");
        btnCancelaEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelaEquipoActionPerformed(evt);
            }
        });

        lblLogoEditarEquipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/equipos_menu.png"))); // NOI18N

        cbEditarEquipo.setBackground(new java.awt.Color(31, 87, 12));
        cbEditarEquipo.setForeground(new java.awt.Color(255, 255, 255));
        cbEditarEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un equipo" }));

        cbEditarEquipoDivision.setBackground(new java.awt.Color(31, 87, 12));
        cbEditarEquipoDivision.setForeground(new java.awt.Color(255, 255, 255));
        cbEditarEquipoDivision.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una división" }));
        cbEditarEquipoDivision.setEnabled(false);

        cbEditarEquipoCategoria.setBackground(new java.awt.Color(31, 87, 12));
        cbEditarEquipoCategoria.setForeground(new java.awt.Color(255, 255, 255));
        cbEditarEquipoCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una categoría" }));
        cbEditarEquipoCategoria.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblLogoEditarEquipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEditarEquipo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 60, Short.MAX_VALUE)
                        .addComponent(btnEditarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbEditarEquipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbEditarEquipoCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbEditarEquipoDivision, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLogoEditarEquipo)
                    .addComponent(lblEditarEquipo))
                .addGap(18, 18, 18)
                .addComponent(cbEditarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbEditarEquipoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(cbEditarEquipoDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelaEquipo)
                    .addComponent(btnEliminarEquipo)
                    .addComponent(btnEditarEquipo))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEquipoActionPerformed
        if(cbEditarEquipo.getSelectedItem()=="Seleccione un equipo"){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un campo","Seleccione un equipo", JOptionPane.QUESTION_MESSAGE, icon);
            
        }else{           
            cbEditarEquipo.setEditable(true);
            cbEditarEquipoCategoria.setEnabled(true);
            cbEditarEquipoDivision.setEnabled(true);
            if(btnEditarEquipo.getText().equals("Editar")){
                campoAnterior = this.cbEditarEquipo.getSelectedItem().toString();
            }else{
                ge.editarEquipo(campoAnterior, this.cbEditarEquipo.getSelectedItem().toString(), this.cbEditarEquipoCategoria.getSelectedIndex(), this.cbEditarEquipoDivision.getSelectedIndex());
                dispose();
            }
            btnEditarEquipo.setText("Aceptar");
        }
    }//GEN-LAST:event_btnEditarEquipoActionPerformed

    private void btnEliminarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEquipoActionPerformed
         if(cbEditarEquipo.getSelectedItem()=="Seleccione un equipo"){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/resources/warning.png"));
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un campo","Seleccione un equipo", JOptionPane.QUESTION_MESSAGE, icon);
            
        }else{
            ge.eliminarEquipo(this.cbEditarEquipo.getSelectedItem().toString());
            dispose();
         }
    }//GEN-LAST:event_btnEliminarEquipoActionPerformed

    private void btnCancelaEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelaEquipoActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelaEquipoActionPerformed

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
            java.util.logging.Logger.getLogger(EditarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarEquipo dialog = new EditarEquipo(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelaEquipo;
    private javax.swing.JButton btnEditarEquipo;
    private javax.swing.JButton btnEliminarEquipo;
    private javax.swing.JComboBox<String> cbEditarEquipo;
    private javax.swing.JComboBox<String> cbEditarEquipoCategoria;
    private javax.swing.JComboBox<String> cbEditarEquipoDivision;
    private javax.swing.JLabel lblEditarEquipo;
    private javax.swing.JLabel lblLogoEditarEquipo;
    // End of variables declaration//GEN-END:variables
}
