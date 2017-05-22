/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import connection.Conn;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sources.MostrarDatos;

/**
 *
 * @author A644308
 */
public class Restricciones extends javax.swing.JFrame {
        private FondoVentana fondo;
        private final Conn conn = new Conn();
        private final MostrarDatos md = new MostrarDatos();
        Vector v=new Vector();
        

    public Restricciones() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/logo1.png"));
        setIconImage(icon);
        fondo = new FondoVentana();
        this.add(fondo);
        
        md.llenarComboEquipos(this.cbEquipos);
        md.llenarComboCampos(this.cbCampos);
        
        
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
   
        });
        
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                  int x, y;
                  x = Restricciones.this.getWidth();
                  y = Restricciones.this.getHeight();
                  fondo.fondoZize(x, y);
            }
        });  
    }
    
    public void restriccionesDeEquipo(){
        
        String equipoSeleccionado = (String) cbEquipos.getSelectedItem();
        
        if(!"Selecciona un equipo".equals(equipoSeleccionado)){
            System.out.println("Mandar " + equipoSeleccionado + " a la BBDD.");
        }
        
        
        this.comprobarCamposExcluidos();
        this.comprobarHorasExcluidas();
        this.comprobarEquiposDiasExcluidos();
        String equipoNoCoincidir = (String) cbNoCoincidir.getSelectedItem();
                if ("".equals(equipoNoCoincidir)){
                    
                }else{
                    System.out.println("Mandar " + equipoNoCoincidir + " a la BBDD.");
                }
    }
    
   
    public void comprobarEquiposDiasExcluidos(){

            if(ckLunesEquipos.isSelected()){
                System.out.println("Mandar Lunes a BBDD");
            }
            if(ckMartesEquipos.isSelected()){
                System.out.println("Mandar Martes a BBDD");
            }
            if(ckMiercolesEquipos.isSelected()){
                System.out.println("Mandar Miercoles a BBDD");
            }
            if(ckJuevesEquipos.isSelected()){
                System.out.println("Mandar Jueves a BBDD");
            }
            if(ckViernesEquipos.isSelected()){
                System.out.println("Mandar Viernes a BBDD");
            }
            if(ckSabadoEquipos.isSelected()){
                System.out.println("Mandar Sabado a BBDD");
            }
            if(ckDomingoEquipos.isSelected()){
                System.out.println("Mandar Domingo a BBDD");
            }
    }
    
    public void comprobarHorasExcluidas(){
        if(ckPrimeraEquipos.isSelected()){
                System.out.println("Mandar Primera Hora a BBDD");
            }
        if(ckSegundaEquipos.isSelected()){
                System.out.println("Mandar Segunda Hora a BBDD");
            }
    }
    
    public void comprobarCamposExcluidos(){
        
        if(ckMP3.isSelected()){
                System.out.println("Mandar MP3 a BBDD");
            }
        if(ckMP4.isSelected()){
                System.out.println("Mandar MP4 a BBDD");
            }
        if(ckSTM.isSelected()){
                System.out.println("Mandar STM a BBDD");
            }
        if(ckTNC.isSelected()){
                System.out.println("Mandar TNC a BBDD");
            }
        if(ckCHU.isSelected()){
                System.out.println("Mandar CHU a BBDD");
            }
        if(ckDEL.isSelected()){
                System.out.println("Mandar DEL a BBDD");
            }
        if(ckCHO.isSelected()){
                System.out.println("Mandar CHO a BBDD");
            }
        if(ckDRA.isSelected()){
                System.out.println("Mandar DRA a BBDD");
            }
    }

    public void enableEquipos(){
                ckLunesEquipos.setEnabled(true);
                ckMartesEquipos.setEnabled(true);
                ckMiercolesEquipos.setEnabled(true);
                ckJuevesEquipos.setEnabled(true);
                ckViernesEquipos.setEnabled(true);
                ckSabadoEquipos.setEnabled(true);
                ckDomingoEquipos.setEnabled(true);
                ckPrimeraEquipos.setEnabled(true);
                ckSegundaEquipos.setEnabled(true);
                ckMP3.setEnabled(true);
                ckMP4.setEnabled(true);
                ckSTM.setEnabled(true);
                ckTNC.setEnabled(true);
                ckCHU.setEnabled(true);
                ckDEL.setEnabled(true);
                ckCHO.setEnabled(true);
                ckDRA.setEnabled(true);
                cbNoCoincidir.setEnabled(true);
                ckCongelarEquipo.setEnabled(true);
    }
    
    public void disableEquipos(){
                ckLunesEquipos.setEnabled(false);
                ckMartesEquipos.setEnabled(false);
                ckMiercolesEquipos.setEnabled(false);
                ckJuevesEquipos.setEnabled(false);
                ckViernesEquipos.setEnabled(false);
                ckSabadoEquipos.setEnabled(false);
                ckDomingoEquipos.setEnabled(false);
                ckPrimeraEquipos.setEnabled(false);
                ckSegundaEquipos.setEnabled(false);
                ckMP3.setEnabled(false);
                ckMP4.setEnabled(false);
                ckSTM.setEnabled(false);
                ckTNC.setEnabled(false);
                ckCHU.setEnabled(false);
                ckDEL.setEnabled(false);
                ckCHO.setEnabled(false);
                ckDRA.setEnabled(false);
                cbNoCoincidir.setEnabled(false);
                ckCongelarEquipo.setEnabled(false);
    }
    
     public void restriccionesDeCampo(){
         
        String campoSeleccionado = (String) cbCampos.getSelectedItem();
        
        if(!"Selecciona un campo".equals(campoSeleccionado)){
            System.out.println("Mandar " + campoSeleccionado + " a la BBDD.");
        }
        
        this.comprobarCampoTexto();
        this.comprobarCampoHoraExcluida();
        this.comprobarCampoDiaExcluido();

    }
     
     public void comprobarCampoDiaExcluido(){
            if(ckLunesCampos.isSelected()){
                System.out.println("Mandar Lunes a BBDD");
            }
            if(ckMartesCampos.isSelected()){
                System.out.println("Mandar Martes a BBDD");
            }
            if(ckMiercolesCampos.isSelected()){
                System.out.println("Mandar Miercoles a BBDD");
            }
            if(ckJuevesCampos.isSelected()){
                System.out.println("Mandar Jueves a BBDD");
            }
            if(ckViernesCampos.isSelected()){
                System.out.println("Mandar Viernes a BBDD");
            }
            if(ckSabadoCampos.isSelected()){
                System.out.println("Mandar Sabado a BBDD");
            }
            if(ckDomingoCampos.isSelected()){
                System.out.println("Mandar Domingo a BBDD");
            }
     }
     
     public void comprobarCampoHoraExcluida(){
            if(ckLunesPrimera.isSelected()){
                System.out.println("Mandar Lunes Primera a BBDD");
            }
            if(ckLunesSegunda.isSelected()){
                System.out.println("Mandar Lunes Segunda a BBDD");
            }
            if(ckMartesPrimera.isSelected()){
                System.out.println("Mandar Martes Primera a BBDD");
            }
            if(ckMartesSegunda.isSelected()){
                System.out.println("Mandar Martes Segunda a BBDD");
            }
            if(ckMiercolesPrimera.isSelected()){
                System.out.println("Mandar Miercoles Primera a BBDD");
            }
            if(ckMiercolesSegunda.isSelected()){
                System.out.println("Mandar Miercoles Segunda a BBDD");
            }
            if(ckJuevesPrimera.isSelected()){
                System.out.println("Mandar Jueves Primera a BBDD");
            }
            if(ckJuevesSegunda.isSelected()){
                System.out.println("Mandar Jueves Segunda a BBDD");
            }
            if(ckViernesPrimera.isSelected()){
                System.out.println("Mandar Viernes Primera a BBDD");
            }
            if(ckViernesSegunda.isSelected()){
                System.out.println("Mandar Viernes Segunda a BBDD");
            }
            if(ckSabadoPrimera.isSelected()){
                System.out.println("Mandar Sabado Primera a BBDD");
            }
            if(ckSabadoSegunda.isSelected()){
                System.out.println("Mandar Sabado Segunda a BBDD");
            }
            if(ckDomingoPrimera.isSelected()){
                System.out.println("Mandar Domingo Primera a BBDD");
            }
            if(ckDomingoSegunda.isSelected()){
                System.out.println("Mandar Domingo Segunda a BBDD");
            }
     }
     
     public void comprobarCampoTexto(){
         if(txtLunesPrimera.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtLunesPrimera.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtLunesSegunda.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtLunesSegunda.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtMartesPrimera.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtMartesPrimera.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtMartesSegunda.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtMartesSegunda.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtMiercolesPrimera.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtMiercolesPrimera.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtMiercolesSegunda.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtMiercolesSegunda.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtJuevesPrimera.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtJuevesPrimera.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtJuevesSegunda.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtJuevesSegunda.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtViernesPrimera.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtViernesPrimera.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtViernesSegunda.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtViernesSegunda.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtSabadoPrimera.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtSabadoPrimera.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtSabadoSegunda.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtSabadoSegunda.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtDomingoPrimera.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtDomingoPrimera.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
         if(txtDomingoSegunda.getText().isEmpty()){
             //System.out.println("No hacer nada");
         }else{
             String hora = txtDomingoSegunda.getText();
             System.out.println("Mandar " + hora + "a la BBDD.");
         }
     }
    
    public void enableCampos(){
                cbEquipos.setEnabled(true);
                ckLunesCampos.setEnabled(true);
                ckMartesCampos.setEnabled(true);
                ckMiercolesCampos.setEnabled(true);
                ckJuevesCampos.setEnabled(true);
                ckViernesCampos.setEnabled(true);
                ckSabadoCampos.setEnabled(true);
                ckDomingoCampos.setEnabled(true);                
                /*ckLunesPrimera.setEnabled(true);
                ckLunesSegunda.setEnabled(true);
                ckMartesPrimera.setEnabled(true);
                ckMartesSegunda.setEnabled(true);                
                ckMiercolesPrimera.setEnabled(true);
                ckMiercolesSegunda.setEnabled(true);
                ckJuevesPrimera.setEnabled(true);
                ckJuevesSegunda.setEnabled(true); 
                ckViernesPrimera.setEnabled(true);
                ckViernesSegunda.setEnabled(true);
                ckSabadoPrimera.setEnabled(true);
                ckSabadoSegunda.setEnabled(true);
                ckDomingoPrimera.setEnabled(true);
                ckDomingoSegunda.setEnabled(true);                
                txtLunesPrimera.setEnabled(true);
                txtLunesSegunda.setEnabled(true);
                txtMartesPrimera.setEnabled(true);
                txtMartesSegunda.setEnabled(true);
                txtMiercolesPrimera.setEnabled(true);
                txtMiercolesSegunda.setEnabled(true);
                txtJuevesPrimera.setEnabled(true);
                txtJuevesSegunda.setEnabled(true);
                txtViernesPrimera.setEnabled(true);
                txtViernesSegunda.setEnabled(true);
                txtSabadoPrimera.setEnabled(true);
                txtSabadoSegunda.setEnabled(true);
                txtDomingoPrimera.setEnabled(true);
                txtDomingoSegunda.setEnabled(true);*/
                ckCongelarCampo.setEnabled(true);
    }
    
    public void disableCampos(){
                cbEquipos.setEnabled(false);
                ckLunesCampos.setEnabled(false);
                ckMartesCampos.setEnabled(false);
                ckMiercolesCampos.setEnabled(false);
                ckJuevesCampos.setEnabled(false);
                ckViernesCampos.setEnabled(false);
                ckSabadoCampos.setEnabled(false);
                ckDomingoCampos.setEnabled(false);                
                ckLunesPrimera.setEnabled(false);
                ckLunesSegunda.setEnabled(false);
                ckMartesPrimera.setEnabled(false);
                ckMartesSegunda.setEnabled(false);                
                ckMiercolesPrimera.setEnabled(false);
                ckMiercolesSegunda.setEnabled(false);
                ckJuevesPrimera.setEnabled(false);
                ckJuevesSegunda.setEnabled(false); 
                ckViernesPrimera.setEnabled(false);
                ckViernesSegunda.setEnabled(false);
                ckSabadoPrimera.setEnabled(false);
                ckSabadoSegunda.setEnabled(false);
                ckDomingoPrimera.setEnabled(false);
                ckDomingoSegunda.setEnabled(false);                
                txtLunesPrimera.setEnabled(false);
                txtLunesSegunda.setEnabled(false);
                txtMartesPrimera.setEnabled(false);
                txtMartesSegunda.setEnabled(false);
                txtMiercolesPrimera.setEnabled(false);
                txtMiercolesSegunda.setEnabled(false);
                txtJuevesPrimera.setEnabled(false);
                txtJuevesSegunda.setEnabled(false);
                txtViernesPrimera.setEnabled(false);
                txtViernesSegunda.setEnabled(false);
                txtSabadoPrimera.setEnabled(false);
                txtSabadoSegunda.setEnabled(false);
                txtDomingoPrimera.setEnabled(false);
                txtDomingoSegunda.setEnabled(false);
                ckCongelarCampo.setEnabled(false);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pEquipos = new javax.swing.JPanel();
        lblEquipos = new javax.swing.JLabel();
        cbEquipos = new javax.swing.JComboBox<>();
        lblLogoEquipos = new javax.swing.JLabel();
        separadorEquipos1 = new javax.swing.JSeparator();
        lblDiasEquipos = new javax.swing.JLabel();
        ckLunesEquipos = new javax.swing.JCheckBox();
        ckMartesEquipos = new javax.swing.JCheckBox();
        ckMiercolesEquipos = new javax.swing.JCheckBox();
        ckJuevesEquipos = new javax.swing.JCheckBox();
        ckViernesEquipos = new javax.swing.JCheckBox();
        ckSabadoEquipos = new javax.swing.JCheckBox();
        ckDomingoEquipos = new javax.swing.JCheckBox();
        separadorEquipos2 = new javax.swing.JSeparator();
        lblHorasEquipos = new javax.swing.JLabel();
        ckPrimeraEquipos = new javax.swing.JCheckBox();
        ckSegundaEquipos = new javax.swing.JCheckBox();
        separadorEquipos3 = new javax.swing.JSeparator();
        lblCamposExcluidos = new javax.swing.JLabel();
        ckMP3 = new javax.swing.JCheckBox();
        ckMP4 = new javax.swing.JCheckBox();
        ckSTM = new javax.swing.JCheckBox();
        ckTNC = new javax.swing.JCheckBox();
        ckCHU = new javax.swing.JCheckBox();
        ckDEL = new javax.swing.JCheckBox();
        ckCHO = new javax.swing.JCheckBox();
        ckDRA = new javax.swing.JCheckBox();
        separadorEquipos4 = new javax.swing.JSeparator();
        lblNoCoincidir = new javax.swing.JLabel();
        cbNoCoincidir = new javax.swing.JComboBox<>();
        separadorEquipos5 = new javax.swing.JSeparator();
        ckCongelarEquipo = new javax.swing.JCheckBox();
        separadorEquipos6 = new javax.swing.JSeparator();
        btnEditarEquipos = new javax.swing.JButton();
        btnAceptarEquipos = new javax.swing.JButton();
        pCampos = new javax.swing.JPanel();
        lblLogoCampos = new javax.swing.JLabel();
        lblCampos = new javax.swing.JLabel();
        cbCampos = new javax.swing.JComboBox<>();
        separadorCampos1 = new javax.swing.JSeparator();
        ckLunesCampos = new javax.swing.JCheckBox();
        ckLunesPrimera = new javax.swing.JCheckBox();
        txtLunesPrimera = new javax.swing.JTextField();
        ckLunesSegunda = new javax.swing.JCheckBox();
        txtLunesSegunda = new javax.swing.JTextField();
        separadorCampos2 = new javax.swing.JSeparator();
        ckMartesCampos = new javax.swing.JCheckBox();
        ckMartesPrimera = new javax.swing.JCheckBox();
        txtMartesPrimera = new javax.swing.JTextField();
        ckMartesSegunda = new javax.swing.JCheckBox();
        separadorCampos3 = new javax.swing.JSeparator();
        txtMartesSegunda = new javax.swing.JTextField();
        ckMiercolesCampos = new javax.swing.JCheckBox();
        ckMiercolesPrimera = new javax.swing.JCheckBox();
        txtMiercolesPrimera = new javax.swing.JTextField();
        ckMiercolesSegunda = new javax.swing.JCheckBox();
        txtMiercolesSegunda = new javax.swing.JTextField();
        separadorCampos4 = new javax.swing.JSeparator();
        ckJuevesCampos = new javax.swing.JCheckBox();
        ckJuevesPrimera = new javax.swing.JCheckBox();
        txtJuevesPrimera = new javax.swing.JTextField();
        ckJuevesSegunda = new javax.swing.JCheckBox();
        txtJuevesSegunda = new javax.swing.JTextField();
        separadorCampos5 = new javax.swing.JSeparator();
        ckViernesCampos = new javax.swing.JCheckBox();
        ckViernesPrimera = new javax.swing.JCheckBox();
        txtViernesPrimera = new javax.swing.JTextField();
        ckViernesSegunda = new javax.swing.JCheckBox();
        txtViernesSegunda = new javax.swing.JTextField();
        separadorCampos6 = new javax.swing.JSeparator();
        ckSabadoCampos = new javax.swing.JCheckBox();
        ckSabadoPrimera = new javax.swing.JCheckBox();
        txtSabadoPrimera = new javax.swing.JTextField();
        ckSabadoSegunda = new javax.swing.JCheckBox();
        txtSabadoSegunda = new javax.swing.JTextField();
        separadorCampos7 = new javax.swing.JSeparator();
        ckDomingoCampos = new javax.swing.JCheckBox();
        ckDomingoPrimera = new javax.swing.JCheckBox();
        txtDomingoPrimera = new javax.swing.JTextField();
        ckDomingoSegunda = new javax.swing.JCheckBox();
        txtDomingoSegunda = new javax.swing.JTextField();
        separadorCampos8 = new javax.swing.JSeparator();
        ckCongelarCampo = new javax.swing.JCheckBox();
        separadorCampos9 = new javax.swing.JSeparator();
        btnEditarEquipos1 = new javax.swing.JButton();
        btnAceptarEquipos1 = new javax.swing.JButton();
        jExcluirFechas = new javax.swing.JPanel();
        lbExcFechaInicio = new javax.swing.JLabel();
        txtExcFechaInicio = new javax.swing.JTextField();
        lblExcFechaFin = new javax.swing.JLabel();
        txtExcFechaFin = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        cbDescripcion = new javax.swing.JComboBox<>();
        btnExcEditar = new javax.swing.JButton();
        btnExcExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Configuración");
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));

        pEquipos.setBackground(new java.awt.Color(255, 255, 255));
        pEquipos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 4, true), "  RESTRICCIONES POR EQUIPO  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(31, 87, 12))); // NOI18N
        pEquipos.setToolTipText("");
        pEquipos.setMinimumSize(new java.awt.Dimension(668, 700));
        pEquipos.setPreferredSize(new java.awt.Dimension(668, 700));

        lblEquipos.setBackground(new java.awt.Color(255, 255, 255));
        lblEquipos.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblEquipos.setForeground(new java.awt.Color(31, 87, 12));
        lblEquipos.setText("Equipo");

        cbEquipos.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        cbEquipos.setForeground(new java.awt.Color(31, 87, 12));
        cbEquipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un equipo" }));
        cbEquipos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEquiposItemStateChanged(evt);
            }
        });

        lblLogoEquipos.setBackground(new java.awt.Color(255, 255, 255));
        lblLogoEquipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/equipos.png"))); // NOI18N

        separadorEquipos1.setForeground(new java.awt.Color(31, 87, 12));

        lblDiasEquipos.setBackground(new java.awt.Color(255, 255, 255));
        lblDiasEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDiasEquipos.setText("Días excluídos:");
        lblDiasEquipos.setMaximumSize(new java.awt.Dimension(191, 22));
        lblDiasEquipos.setMinimumSize(new java.awt.Dimension(191, 22));
        lblDiasEquipos.setPreferredSize(new java.awt.Dimension(191, 22));

        ckLunesEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckLunesEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckLunesEquipos.setText("Lunes");
        ckLunesEquipos.setEnabled(false);
        ckLunesEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckLunesEquiposActionPerformed(evt);
            }
        });

        ckMartesEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckMartesEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckMartesEquipos.setText("Martes");
        ckMartesEquipos.setEnabled(false);

        ckMiercolesEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckMiercolesEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckMiercolesEquipos.setText("Miércoles");
        ckMiercolesEquipos.setEnabled(false);

        ckJuevesEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckJuevesEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckJuevesEquipos.setText("Jueves");
        ckJuevesEquipos.setEnabled(false);

        ckViernesEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckViernesEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckViernesEquipos.setText("Viernes");
        ckViernesEquipos.setEnabled(false);

        ckSabadoEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckSabadoEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckSabadoEquipos.setText("Sábado");
        ckSabadoEquipos.setEnabled(false);
        ckSabadoEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckSabadoEquiposActionPerformed(evt);
            }
        });

        ckDomingoEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckDomingoEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckDomingoEquipos.setText("Domingo");
        ckDomingoEquipos.setEnabled(false);

        separadorEquipos2.setForeground(new java.awt.Color(31, 87, 12));

        lblHorasEquipos.setBackground(new java.awt.Color(255, 255, 255));
        lblHorasEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHorasEquipos.setText("Horas excluídos:");
        lblHorasEquipos.setMaximumSize(new java.awt.Dimension(191, 22));
        lblHorasEquipos.setMinimumSize(new java.awt.Dimension(191, 22));
        lblHorasEquipos.setPreferredSize(new java.awt.Dimension(191, 22));

        ckPrimeraEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckPrimeraEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckPrimeraEquipos.setText("Primera");
        ckPrimeraEquipos.setEnabled(false);

        ckSegundaEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckSegundaEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckSegundaEquipos.setText("Segunda");
        ckSegundaEquipos.setEnabled(false);

        separadorEquipos3.setForeground(new java.awt.Color(31, 87, 12));

        lblCamposExcluidos.setBackground(new java.awt.Color(255, 255, 255));
        lblCamposExcluidos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCamposExcluidos.setText("Campos excluídos:");
        lblCamposExcluidos.setMaximumSize(new java.awt.Dimension(191, 22));
        lblCamposExcluidos.setMinimumSize(new java.awt.Dimension(191, 22));
        lblCamposExcluidos.setPreferredSize(new java.awt.Dimension(191, 22));

        ckMP3.setBackground(new java.awt.Color(255, 255, 255));
        ckMP3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckMP3.setText("MP3");
        ckMP3.setEnabled(false);
        ckMP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckMP3ActionPerformed(evt);
            }
        });

        ckMP4.setBackground(new java.awt.Color(255, 255, 255));
        ckMP4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckMP4.setText("MP4");
        ckMP4.setEnabled(false);

        ckSTM.setBackground(new java.awt.Color(255, 255, 255));
        ckSTM.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckSTM.setText("STM");
        ckSTM.setEnabled(false);

        ckTNC.setBackground(new java.awt.Color(255, 255, 255));
        ckTNC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckTNC.setText("TNC");
        ckTNC.setEnabled(false);

        ckCHU.setBackground(new java.awt.Color(255, 255, 255));
        ckCHU.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckCHU.setText("CHU");
        ckCHU.setEnabled(false);

        ckDEL.setBackground(new java.awt.Color(255, 255, 255));
        ckDEL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckDEL.setText("DEL");
        ckDEL.setEnabled(false);

        ckCHO.setBackground(new java.awt.Color(255, 255, 255));
        ckCHO.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckCHO.setText("CHO");
        ckCHO.setEnabled(false);

        ckDRA.setBackground(new java.awt.Color(255, 255, 255));
        ckDRA.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckDRA.setText("DRA");
        ckDRA.setEnabled(false);

        separadorEquipos4.setForeground(new java.awt.Color(31, 87, 12));

        lblNoCoincidir.setBackground(new java.awt.Color(255, 255, 255));
        lblNoCoincidir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNoCoincidir.setText("No coincidir con equipo:");

        cbNoCoincidir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbNoCoincidir.setEnabled(false);
        cbNoCoincidir.setPreferredSize(new java.awt.Dimension(300, 28));

        separadorEquipos5.setForeground(new java.awt.Color(31, 87, 12));

        ckCongelarEquipo.setBackground(new java.awt.Color(255, 255, 255));
        ckCongelarEquipo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckCongelarEquipo.setForeground(new java.awt.Color(255, 0, 0));
        ckCongelarEquipo.setText("Congelar equipo");
        ckCongelarEquipo.setEnabled(false);
        ckCongelarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckCongelarEquipoActionPerformed(evt);
            }
        });

        separadorEquipos6.setForeground(new java.awt.Color(31, 87, 12));

        btnEditarEquipos.setBackground(new java.awt.Color(31, 87, 12));
        btnEditarEquipos.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarEquipos.setText("Editar Equipo");
        btnEditarEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEquiposActionPerformed(evt);
            }
        });

        btnAceptarEquipos.setBackground(new java.awt.Color(31, 87, 12));
        btnAceptarEquipos.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptarEquipos.setText("Aceptar");
        btnAceptarEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarEquiposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pEquiposLayout = new javax.swing.GroupLayout(pEquipos);
        pEquipos.setLayout(pEquiposLayout);
        pEquiposLayout.setHorizontalGroup(
            pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEquiposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEquiposLayout.createSequentialGroup()
                        .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pEquiposLayout.createSequentialGroup()
                                .addComponent(lblHorasEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ckPrimeraEquipos)
                                .addGap(18, 18, 18)
                                .addComponent(ckSegundaEquipos))
                            .addGroup(pEquiposLayout.createSequentialGroup()
                                .addComponent(lblNoCoincidir, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbNoCoincidir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pEquiposLayout.createSequentialGroup()
                                .addComponent(lblCamposExcluidos, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pEquiposLayout.createSequentialGroup()
                                        .addComponent(ckCHU)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckDEL)
                                        .addGap(20, 20, 20)
                                        .addComponent(ckCHO)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckDRA))
                                    .addGroup(pEquiposLayout.createSequentialGroup()
                                        .addComponent(ckMP3)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckMP4)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckSTM)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckTNC)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pEquiposLayout.createSequentialGroup()
                        .addComponent(ckCongelarEquipo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pEquiposLayout.createSequentialGroup()
                        .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(separadorEquipos6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorEquipos5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorEquipos4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorEquipos3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorEquipos2)
                            .addComponent(separadorEquipos1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pEquiposLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEditarEquipos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAceptarEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEquiposLayout.createSequentialGroup()
                                .addComponent(lblDiasEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ckLunesEquipos)
                                    .addComponent(ckViernesEquipos))
                                .addGap(18, 18, 18)
                                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pEquiposLayout.createSequentialGroup()
                                        .addComponent(ckSabadoEquipos)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckDomingoEquipos))
                                    .addGroup(pEquiposLayout.createSequentialGroup()
                                        .addComponent(ckMartesEquipos)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckMiercolesEquipos)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckJuevesEquipos)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEquiposLayout.createSequentialGroup()
                                .addComponent(lblLogoEquipos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEquipos)
                                .addGap(18, 18, 18)
                                .addComponent(cbEquipos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))))
        );
        pEquiposLayout.setVerticalGroup(
            pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEquiposLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEquipos)
                        .addComponent(cbEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblLogoEquipos))
                .addGap(20, 20, 20)
                .addComponent(separadorEquipos1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiasEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckLunesEquipos)
                    .addComponent(ckMartesEquipos)
                    .addComponent(ckMiercolesEquipos)
                    .addComponent(ckJuevesEquipos))
                .addGap(15, 15, 15)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckSabadoEquipos)
                    .addComponent(ckDomingoEquipos)
                    .addComponent(ckViernesEquipos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorEquipos2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHorasEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckPrimeraEquipos)
                    .addComponent(ckSegundaEquipos))
                .addGap(10, 10, 10)
                .addComponent(separadorEquipos3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCamposExcluidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckMP3)
                    .addComponent(ckMP4)
                    .addComponent(ckSTM)
                    .addComponent(ckTNC))
                .addGap(13, 13, 13)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckCHU)
                    .addComponent(ckDRA)
                    .addComponent(ckDEL)
                    .addComponent(ckCHO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorEquipos4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoCoincidir)
                    .addComponent(cbNoCoincidir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(separadorEquipos5, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ckCongelarEquipo)
                .addGap(10, 10, 10)
                .addComponent(separadorEquipos6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarEquipos)
                    .addComponent(btnAceptarEquipos))
                .addContainerGap())
        );

        pCampos.setBackground(new java.awt.Color(255, 255, 255));
        pCampos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 4, true), "  HORARIO DE LOS CAMPOS  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(31, 87, 12))); // NOI18N
        pCampos.setPreferredSize(new java.awt.Dimension(668, 700));

        lblLogoCampos.setBackground(new java.awt.Color(255, 255, 255));
        lblLogoCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/campo.png"))); // NOI18N

        lblCampos.setBackground(new java.awt.Color(255, 255, 255));
        lblCampos.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblCampos.setForeground(new java.awt.Color(31, 87, 12));
        lblCampos.setText("Campo");

        cbCampos.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        cbCampos.setForeground(new java.awt.Color(31, 87, 12));
        cbCampos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un campo" }));
        cbCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCamposActionPerformed(evt);
            }
        });

        separadorCampos1.setForeground(new java.awt.Color(31, 87, 12));

        ckLunesCampos.setBackground(new java.awt.Color(255, 255, 255));
        ckLunesCampos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckLunesCampos.setText("Lunes");
        ckLunesCampos.setEnabled(false);
        ckLunesCampos.setMaximumSize(new java.awt.Dimension(111, 31));
        ckLunesCampos.setMinimumSize(new java.awt.Dimension(111, 31));
        ckLunesCampos.setPreferredSize(new java.awt.Dimension(111, 31));

        ckLunesPrimera.setBackground(new java.awt.Color(255, 255, 255));
        ckLunesPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckLunesPrimera.setText("Primera Hora");
        ckLunesPrimera.setEnabled(false);

        txtLunesPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtLunesPrimera.setEnabled(false);

        ckLunesSegunda.setBackground(new java.awt.Color(255, 255, 255));
        ckLunesSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckLunesSegunda.setText("Segunda Hora");
        ckLunesSegunda.setEnabled(false);

        txtLunesSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtLunesSegunda.setEnabled(false);

        separadorCampos2.setForeground(new java.awt.Color(31, 87, 12));

        ckMartesCampos.setBackground(new java.awt.Color(255, 255, 255));
        ckMartesCampos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckMartesCampos.setText("Martes");
        ckMartesCampos.setEnabled(false);
        ckMartesCampos.setMaximumSize(new java.awt.Dimension(111, 31));
        ckMartesCampos.setMinimumSize(new java.awt.Dimension(111, 31));
        ckMartesCampos.setPreferredSize(new java.awt.Dimension(111, 31));

        ckMartesPrimera.setBackground(new java.awt.Color(255, 255, 255));
        ckMartesPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckMartesPrimera.setText("Primera Hora");
        ckMartesPrimera.setEnabled(false);

        txtMartesPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMartesPrimera.setEnabled(false);

        ckMartesSegunda.setBackground(new java.awt.Color(255, 255, 255));
        ckMartesSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckMartesSegunda.setText("Segunda Hora");
        ckMartesSegunda.setEnabled(false);

        separadorCampos3.setForeground(new java.awt.Color(31, 87, 12));

        txtMartesSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMartesSegunda.setEnabled(false);

        ckMiercolesCampos.setBackground(new java.awt.Color(255, 255, 255));
        ckMiercolesCampos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckMiercolesCampos.setText("Miércoles");
        ckMiercolesCampos.setEnabled(false);

        ckMiercolesPrimera.setBackground(new java.awt.Color(255, 255, 255));
        ckMiercolesPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckMiercolesPrimera.setText("Primera Hora");
        ckMiercolesPrimera.setEnabled(false);

        txtMiercolesPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMiercolesPrimera.setEnabled(false);

        ckMiercolesSegunda.setBackground(new java.awt.Color(255, 255, 255));
        ckMiercolesSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckMiercolesSegunda.setText("Segunda Hora");
        ckMiercolesSegunda.setEnabled(false);

        txtMiercolesSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMiercolesSegunda.setEnabled(false);

        separadorCampos4.setForeground(new java.awt.Color(31, 87, 12));

        ckJuevesCampos.setBackground(new java.awt.Color(255, 255, 255));
        ckJuevesCampos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckJuevesCampos.setText("Jueves");
        ckJuevesCampos.setEnabled(false);
        ckJuevesCampos.setMaximumSize(new java.awt.Dimension(111, 31));
        ckJuevesCampos.setMinimumSize(new java.awt.Dimension(111, 31));
        ckJuevesCampos.setPreferredSize(new java.awt.Dimension(111, 31));

        ckJuevesPrimera.setBackground(new java.awt.Color(255, 255, 255));
        ckJuevesPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckJuevesPrimera.setText("Primera Hora");
        ckJuevesPrimera.setEnabled(false);

        txtJuevesPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtJuevesPrimera.setEnabled(false);

        ckJuevesSegunda.setBackground(new java.awt.Color(255, 255, 255));
        ckJuevesSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckJuevesSegunda.setText("Segunda Hora");
        ckJuevesSegunda.setEnabled(false);

        txtJuevesSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtJuevesSegunda.setEnabled(false);

        separadorCampos5.setForeground(new java.awt.Color(31, 87, 12));

        ckViernesCampos.setBackground(new java.awt.Color(255, 255, 255));
        ckViernesCampos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckViernesCampos.setText("Viernes");
        ckViernesCampos.setEnabled(false);
        ckViernesCampos.setMaximumSize(new java.awt.Dimension(111, 31));
        ckViernesCampos.setMinimumSize(new java.awt.Dimension(111, 31));
        ckViernesCampos.setPreferredSize(new java.awt.Dimension(111, 31));

        ckViernesPrimera.setBackground(new java.awt.Color(255, 255, 255));
        ckViernesPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckViernesPrimera.setText("Primera Hora");
        ckViernesPrimera.setEnabled(false);

        txtViernesPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtViernesPrimera.setEnabled(false);

        ckViernesSegunda.setBackground(new java.awt.Color(255, 255, 255));
        ckViernesSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckViernesSegunda.setText("Segunda Hora");
        ckViernesSegunda.setEnabled(false);

        txtViernesSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtViernesSegunda.setEnabled(false);

        separadorCampos6.setForeground(new java.awt.Color(31, 87, 12));

        ckSabadoCampos.setBackground(new java.awt.Color(255, 255, 255));
        ckSabadoCampos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckSabadoCampos.setText("Sábado");
        ckSabadoCampos.setEnabled(false);
        ckSabadoCampos.setMaximumSize(new java.awt.Dimension(111, 31));
        ckSabadoCampos.setMinimumSize(new java.awt.Dimension(111, 31));
        ckSabadoCampos.setPreferredSize(new java.awt.Dimension(111, 31));

        ckSabadoPrimera.setBackground(new java.awt.Color(255, 255, 255));
        ckSabadoPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckSabadoPrimera.setText("Primera Hora");
        ckSabadoPrimera.setEnabled(false);

        txtSabadoPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSabadoPrimera.setEnabled(false);

        ckSabadoSegunda.setBackground(new java.awt.Color(255, 255, 255));
        ckSabadoSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckSabadoSegunda.setText("Segunda Hora");
        ckSabadoSegunda.setEnabled(false);

        txtSabadoSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSabadoSegunda.setEnabled(false);

        separadorCampos7.setForeground(new java.awt.Color(31, 87, 12));

        ckDomingoCampos.setBackground(new java.awt.Color(255, 255, 255));
        ckDomingoCampos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckDomingoCampos.setText("Domingo");
        ckDomingoCampos.setEnabled(false);
        ckDomingoCampos.setMaximumSize(new java.awt.Dimension(111, 31));
        ckDomingoCampos.setMinimumSize(new java.awt.Dimension(111, 31));
        ckDomingoCampos.setPreferredSize(new java.awt.Dimension(111, 31));

        ckDomingoPrimera.setBackground(new java.awt.Color(255, 255, 255));
        ckDomingoPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckDomingoPrimera.setText("Primera Hora");
        ckDomingoPrimera.setEnabled(false);

        txtDomingoPrimera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDomingoPrimera.setEnabled(false);

        ckDomingoSegunda.setBackground(new java.awt.Color(255, 255, 255));
        ckDomingoSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckDomingoSegunda.setText("Segunda Hora");
        ckDomingoSegunda.setEnabled(false);

        txtDomingoSegunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDomingoSegunda.setEnabled(false);

        separadorCampos8.setForeground(new java.awt.Color(31, 87, 12));

        ckCongelarCampo.setBackground(new java.awt.Color(255, 255, 255));
        ckCongelarCampo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckCongelarCampo.setForeground(new java.awt.Color(255, 0, 0));
        ckCongelarCampo.setText("Congelar campo");
        ckCongelarCampo.setEnabled(false);
        ckCongelarCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckCongelarCampoActionPerformed(evt);
            }
        });

        separadorCampos9.setForeground(new java.awt.Color(31, 87, 12));

        btnEditarEquipos1.setBackground(new java.awt.Color(31, 87, 12));
        btnEditarEquipos1.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarEquipos1.setText("Editar Campo");
        btnEditarEquipos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEquipos1ActionPerformed(evt);
            }
        });

        btnAceptarEquipos1.setBackground(new java.awt.Color(31, 87, 12));
        btnAceptarEquipos1.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptarEquipos1.setText("Aceptar");
        btnAceptarEquipos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarEquipos1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pCamposLayout = new javax.swing.GroupLayout(pCampos);
        pCampos.setLayout(pCamposLayout);
        pCamposLayout.setHorizontalGroup(
            pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCamposLayout.createSequentialGroup()
                        .addComponent(ckLunesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ckLunesPrimera)
                        .addGap(18, 18, 18)
                        .addComponent(txtLunesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ckLunesSegunda)
                        .addGap(18, 18, 18)
                        .addComponent(txtLunesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(pCamposLayout.createSequentialGroup()
                        .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorCampos5)
                            .addComponent(separadorCampos2)
                            .addComponent(separadorCampos3)
                            .addComponent(separadorCampos4)
                            .addComponent(separadorCampos1)
                            .addComponent(separadorCampos6)
                            .addComponent(separadorCampos7)
                            .addComponent(separadorCampos8)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCamposLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEditarEquipos1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAceptarEquipos1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(separadorCampos9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pCamposLayout.createSequentialGroup()
                                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pCamposLayout.createSequentialGroup()
                                        .addComponent(ckJuevesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckJuevesPrimera)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtJuevesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckJuevesSegunda)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtJuevesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ckCongelarCampo)
                                    .addGroup(pCamposLayout.createSequentialGroup()
                                        .addComponent(ckDomingoCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckDomingoPrimera)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDomingoPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckDomingoSegunda)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDomingoSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pCamposLayout.createSequentialGroup()
                                        .addComponent(ckMiercolesCampos)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckMiercolesPrimera)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMiercolesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckMiercolesSegunda)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMiercolesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pCamposLayout.createSequentialGroup()
                                        .addComponent(ckViernesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckViernesPrimera)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtViernesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckViernesSegunda)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtViernesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pCamposLayout.createSequentialGroup()
                                        .addComponent(ckSabadoCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckSabadoPrimera)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSabadoPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckSabadoSegunda)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSabadoSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pCamposLayout.createSequentialGroup()
                                        .addComponent(ckMartesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckMartesPrimera)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMartesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ckMartesSegunda)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMartesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pCamposLayout.createSequentialGroup()
                                .addComponent(lblLogoCampos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCampos)
                                .addGap(18, 18, 18)
                                .addComponent(cbCampos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        pCamposLayout.setVerticalGroup(
            pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCamposLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCampos))
                    .addComponent(lblLogoCampos))
                .addGap(20, 20, 20)
                .addComponent(separadorCampos1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckLunesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckLunesPrimera)
                    .addComponent(txtLunesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckLunesSegunda)
                    .addComponent(txtLunesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(separadorCampos2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckMartesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckMartesPrimera)
                    .addComponent(txtMartesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckMartesSegunda)
                    .addComponent(txtMartesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorCampos3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckMiercolesCampos)
                    .addComponent(ckMiercolesPrimera)
                    .addComponent(txtMiercolesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckMiercolesSegunda)
                    .addComponent(txtMiercolesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorCampos4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckJuevesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckJuevesPrimera)
                    .addComponent(txtJuevesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckJuevesSegunda)
                    .addComponent(txtJuevesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorCampos5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckViernesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckViernesPrimera)
                    .addComponent(txtViernesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckViernesSegunda)
                    .addComponent(txtViernesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorCampos6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckSabadoCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckSabadoPrimera)
                    .addComponent(txtSabadoPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckSabadoSegunda)
                    .addComponent(txtSabadoSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorCampos7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckDomingoCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckDomingoPrimera)
                    .addComponent(txtDomingoPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckDomingoSegunda)
                    .addComponent(txtDomingoSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorCampos8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckCongelarCampo)
                .addGap(10, 10, 10)
                .addComponent(separadorCampos9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarEquipos1)
                    .addComponent(btnEditarEquipos1))
                .addContainerGap())
        );

        jExcluirFechas.setBackground(new java.awt.Color(255, 255, 255));
        jExcluirFechas.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 4, true), "  EXCLUIR FECHAS DEL CALENDARIO  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(31, 87, 12))); // NOI18N

        lbExcFechaInicio.setBackground(new java.awt.Color(255, 255, 255));
        lbExcFechaInicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbExcFechaInicio.setText("Fecha de Incio");

        txtExcFechaInicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExcFechaInicio.setEnabled(false);
        txtExcFechaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExcFechaInicioActionPerformed(evt);
            }
        });

        lblExcFechaFin.setBackground(new java.awt.Color(255, 255, 255));
        lblExcFechaFin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblExcFechaFin.setText("Fecha de Fin");

        txtExcFechaFin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExcFechaFin.setEnabled(false);
        txtExcFechaFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExcFechaFinActionPerformed(evt);
            }
        });

        lblDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        lblDescripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDescripcion.setText("Descripción");

        cbDescripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbDescripcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un motivo", "Periodo hábil", "Fiesta nacional", "Fiesta local", "Periodo vacacional", "Otros" }));
        cbDescripcion.setEnabled(false);

        btnExcEditar.setBackground(new java.awt.Color(31, 87, 12));
        btnExcEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnExcEditar.setText("Editar Fechas");
        btnExcEditar.setMaximumSize(new java.awt.Dimension(120, 23));
        btnExcEditar.setMinimumSize(new java.awt.Dimension(120, 23));
        btnExcEditar.setPreferredSize(new java.awt.Dimension(120, 23));
        btnExcEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcEditarActionPerformed(evt);
            }
        });

        btnExcExcluir.setBackground(new java.awt.Color(31, 87, 12));
        btnExcExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcExcluir.setText("Aceptar");
        btnExcExcluir.setMaximumSize(new java.awt.Dimension(120, 23));
        btnExcExcluir.setMinimumSize(new java.awt.Dimension(120, 23));
        btnExcExcluir.setPreferredSize(new java.awt.Dimension(120, 23));
        btnExcExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jExcluirFechasLayout = new javax.swing.GroupLayout(jExcluirFechas);
        jExcluirFechas.setLayout(jExcluirFechasLayout);
        jExcluirFechasLayout.setHorizontalGroup(
            jExcluirFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jExcluirFechasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbExcFechaInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtExcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblExcFechaFin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtExcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDescripcion)
                .addGap(18, 18, 18)
                .addComponent(cbDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExcEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jExcluirFechasLayout.setVerticalGroup(
            jExcluirFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jExcluirFechasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jExcluirFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbExcFechaInicio)
                    .addComponent(txtExcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExcFechaFin)
                    .addComponent(txtExcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcion)
                    .addComponent(btnExcEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 658, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(pCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE))
                    .addComponent(jExcluirFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jExcluirFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 659, Short.MAX_VALUE)
                    .addComponent(pCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCamposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCamposActionPerformed

    private void ckLunesEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckLunesEquiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckLunesEquiposActionPerformed

    private void ckMP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckMP3ActionPerformed

    }//GEN-LAST:event_ckMP3ActionPerformed

    private void ckCongelarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckCongelarEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckCongelarEquipoActionPerformed

    private void btnEditarEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEquiposActionPerformed
    if (cbEquipos.getSelectedItem().toString().equals("Selecciona un equipo")){
        ImageIcon icon = new ImageIcon("src/resources/warning.png");
        JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un equipo","Seleccione un equipo", JOptionPane.QUESTION_MESSAGE, icon);
    }else {
                this.enableEquipos();

        }
    }//GEN-LAST:event_btnEditarEquiposActionPerformed

    private void btnAceptarEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarEquiposActionPerformed
        ImageIcon icon = new ImageIcon("src/resources/aceptar.png");
        int input = JOptionPane.showConfirmDialog(null, "¿Desea aplicar las restricciones para el equipo seleccionado?", "Aplicar restricciones", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {
                
                this.restriccionesDeEquipo();
                this.disableEquipos();
        }  
    }//GEN-LAST:event_btnAceptarEquiposActionPerformed

    private void ckCongelarCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckCongelarCampoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckCongelarCampoActionPerformed

    private void btnEditarEquipos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEquipos1ActionPerformed
    if (cbCampos.getSelectedItem().toString().equals("Selecciona un campo")){
        ImageIcon icon = new ImageIcon("src/resources/warning.png");
        JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un campo","Seleccione un campo", JOptionPane.QUESTION_MESSAGE, icon);
    }else {
                 this.enableCampos();
        }
    }//GEN-LAST:event_btnEditarEquipos1ActionPerformed

    private void btnAceptarEquipos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarEquipos1ActionPerformed
        ImageIcon icon = new ImageIcon("src/resources/aceptar.png");
        int input = JOptionPane.showConfirmDialog(null, "¿Desea aplicar las restricciones para el campo seleccionado?", "Aplicar restricciones", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {
            
                this.restriccionesDeCampo();
                this.disableCampos();
        }  
    }//GEN-LAST:event_btnAceptarEquipos1ActionPerformed

    private void ckSabadoEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckSabadoEquiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckSabadoEquiposActionPerformed

    private void ckLunesPrimeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckLunesPrimeraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckLunesPrimeraActionPerformed

    private void ckLunesCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckLunesCamposActionPerformed
        if(ckLunesCampos.isSelected()){
            ckLunesPrimera.setEnabled(true);
            ckLunesSegunda.setEnabled(true);
            txtLunesPrimera.setEnabled(true);
            txtLunesSegunda.setEnabled(true);
        }else{
            ckLunesPrimera.setEnabled(false);
            ckLunesSegunda.setEnabled(false);
            txtLunesPrimera.setEnabled(false);
            txtLunesSegunda.setEnabled(false);
        }
    }//GEN-LAST:event_ckLunesCamposActionPerformed

    private void ckMartesCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckMartesCamposActionPerformed
        if(ckMartesCampos.isSelected()){
            ckMartesPrimera.setEnabled(true);
            ckMartesSegunda.setEnabled(true);
            txtMartesPrimera.setEnabled(true);
            txtMartesSegunda.setEnabled(true);
        }else{
            ckMartesPrimera.setEnabled(false);
            ckMartesSegunda.setEnabled(false);
            txtMartesPrimera.setEnabled(false);
            txtMartesSegunda.setEnabled(false);
        }
    }//GEN-LAST:event_ckMartesCamposActionPerformed

    private void ckMiercolesCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckMiercolesCamposActionPerformed
        if(ckMiercolesCampos.isSelected()){
            ckMiercolesPrimera.setEnabled(true);
            ckMiercolesSegunda.setEnabled(true);
            txtMiercolesPrimera.setEnabled(true);
            txtMiercolesSegunda.setEnabled(true);
        }else{
            ckMiercolesPrimera.setEnabled(false);
            ckMiercolesSegunda.setEnabled(false);
            txtMiercolesPrimera.setEnabled(false);
            txtMiercolesSegunda.setEnabled(false);
        }
    }//GEN-LAST:event_ckMiercolesCamposActionPerformed

    private void ckJuevesCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckJuevesCamposActionPerformed
        if(ckJuevesCampos.isSelected()){
            ckJuevesPrimera.setEnabled(true);
            ckJuevesSegunda.setEnabled(true);
            txtJuevesPrimera.setEnabled(true);
            txtJuevesSegunda.setEnabled(true);
        }else{
            ckJuevesPrimera.setEnabled(false);
            ckJuevesSegunda.setEnabled(false);
            txtJuevesPrimera.setEnabled(false);
            txtJuevesSegunda.setEnabled(false);
        }
    }//GEN-LAST:event_ckJuevesCamposActionPerformed

    private void ckViernesCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckViernesCamposActionPerformed
        if(ckViernesCampos.isSelected()){
            ckViernesPrimera.setEnabled(true);
            ckViernesSegunda.setEnabled(true);
            txtViernesPrimera.setEnabled(true);
            txtViernesSegunda.setEnabled(true);
        }else{
            ckViernesPrimera.setEnabled(false);
            ckViernesSegunda.setEnabled(false);
            txtViernesPrimera.setEnabled(false);
            txtViernesSegunda.setEnabled(false);
        }
    }//GEN-LAST:event_ckViernesCamposActionPerformed

    private void ckSabadoCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckSabadoCamposActionPerformed
        if(ckSabadoCampos.isSelected()){
            ckSabadoPrimera.setEnabled(true);
            ckSabadoSegunda.setEnabled(true);
            txtSabadoPrimera.setEnabled(true);
            txtSabadoSegunda.setEnabled(true);
        }else{
            ckSabadoPrimera.setEnabled(false);
            ckSabadoSegunda.setEnabled(false);
            txtSabadoPrimera.setEnabled(false);
            txtSabadoSegunda.setEnabled(false);
        }
    }//GEN-LAST:event_ckSabadoCamposActionPerformed

    private void ckDomingoCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckDomingoCamposActionPerformed
        if(ckDomingoCampos.isSelected()){
            ckDomingoPrimera.setEnabled(true);
            ckDomingoSegunda.setEnabled(true);
            txtDomingoPrimera.setEnabled(true);
            txtDomingoSegunda.setEnabled(true);
        }else{
            ckDomingoPrimera.setEnabled(false);
            ckDomingoSegunda.setEnabled(false);
            txtDomingoPrimera.setEnabled(false);
            txtDomingoSegunda.setEnabled(false);
        }
    }//GEN-LAST:event_ckDomingoCamposActionPerformed

    private void cbEquiposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEquiposItemStateChanged
        md.llenarComboNoCoincidir(this.cbNoCoincidir, this.cbEquipos.getSelectedIndex());
    }//GEN-LAST:event_cbEquiposItemStateChanged

    private void txtExcFechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExcFechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExcFechaInicioActionPerformed

    private void txtExcFechaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExcFechaFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExcFechaFinActionPerformed

    private void btnExcEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcEditarActionPerformed
        ImageIcon icon = new ImageIcon("src/resources/editar.png");
        int input = JOptionPane.showConfirmDialog(null, "¿Desea editar las fechas de exclusión en el calendario?", "Editar Fechas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {
            txtExcFechaInicio.setEnabled(true);
            txtExcFechaFin.setEnabled(true);
            cbDescripcion.setEnabled(true);
        }
    }//GEN-LAST:event_btnExcEditarActionPerformed

    private void btnExcExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcExcluirActionPerformed
        ImageIcon icon = new ImageIcon("src/resources/aceptar.png");
        int input = JOptionPane.showConfirmDialog(null, "¿Desea excluir las fechas seleccionadas?", "Excluir Fechas",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {

        }
    }//GEN-LAST:event_btnExcExcluirActionPerformed
   
    public void close() {
             dispose();             
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
            java.util.logging.Logger.getLogger(Restricciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Restricciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Restricciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Restricciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Restricciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarEquipos;
    private javax.swing.JButton btnAceptarEquipos1;
    private javax.swing.JButton btnEditarEquipos;
    private javax.swing.JButton btnEditarEquipos1;
    private javax.swing.JButton btnExcEditar;
    private javax.swing.JButton btnExcExcluir;
    private javax.swing.JComboBox<String> cbCampos;
    private javax.swing.JComboBox<String> cbDescripcion;
    private javax.swing.JComboBox<String> cbEquipos;
    private javax.swing.JComboBox<String> cbNoCoincidir;
    private javax.swing.JCheckBox ckCHO;
    private javax.swing.JCheckBox ckCHU;
    private javax.swing.JCheckBox ckCongelarCampo;
    private javax.swing.JCheckBox ckCongelarEquipo;
    private javax.swing.JCheckBox ckDEL;
    private javax.swing.JCheckBox ckDRA;
    private javax.swing.JCheckBox ckDomingoCampos;
    private javax.swing.JCheckBox ckDomingoEquipos;
    private javax.swing.JCheckBox ckDomingoPrimera;
    private javax.swing.JCheckBox ckDomingoSegunda;
    private javax.swing.JCheckBox ckJuevesCampos;
    private javax.swing.JCheckBox ckJuevesEquipos;
    private javax.swing.JCheckBox ckJuevesPrimera;
    private javax.swing.JCheckBox ckJuevesSegunda;
    private javax.swing.JCheckBox ckLunesCampos;
    private javax.swing.JCheckBox ckLunesEquipos;
    private javax.swing.JCheckBox ckLunesPrimera;
    private javax.swing.JCheckBox ckLunesSegunda;
    private javax.swing.JCheckBox ckMP3;
    private javax.swing.JCheckBox ckMP4;
    private javax.swing.JCheckBox ckMartesCampos;
    private javax.swing.JCheckBox ckMartesEquipos;
    private javax.swing.JCheckBox ckMartesPrimera;
    private javax.swing.JCheckBox ckMartesSegunda;
    private javax.swing.JCheckBox ckMiercolesCampos;
    private javax.swing.JCheckBox ckMiercolesEquipos;
    private javax.swing.JCheckBox ckMiercolesPrimera;
    private javax.swing.JCheckBox ckMiercolesSegunda;
    private javax.swing.JCheckBox ckPrimeraEquipos;
    private javax.swing.JCheckBox ckSTM;
    private javax.swing.JCheckBox ckSabadoCampos;
    private javax.swing.JCheckBox ckSabadoEquipos;
    private javax.swing.JCheckBox ckSabadoPrimera;
    private javax.swing.JCheckBox ckSabadoSegunda;
    private javax.swing.JCheckBox ckSegundaEquipos;
    private javax.swing.JCheckBox ckTNC;
    private javax.swing.JCheckBox ckViernesCampos;
    private javax.swing.JCheckBox ckViernesEquipos;
    private javax.swing.JCheckBox ckViernesPrimera;
    private javax.swing.JCheckBox ckViernesSegunda;
    private javax.swing.JPanel jExcluirFechas;
    private javax.swing.JLabel lbExcFechaInicio;
    private javax.swing.JLabel lblCampos;
    private javax.swing.JLabel lblCamposExcluidos;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDiasEquipos;
    private javax.swing.JLabel lblEquipos;
    private javax.swing.JLabel lblExcFechaFin;
    private javax.swing.JLabel lblHorasEquipos;
    private javax.swing.JLabel lblLogoCampos;
    private javax.swing.JLabel lblLogoEquipos;
    private javax.swing.JLabel lblNoCoincidir;
    private javax.swing.JPanel pCampos;
    private javax.swing.JPanel pEquipos;
    private javax.swing.JSeparator separadorCampos1;
    private javax.swing.JSeparator separadorCampos2;
    private javax.swing.JSeparator separadorCampos3;
    private javax.swing.JSeparator separadorCampos4;
    private javax.swing.JSeparator separadorCampos5;
    private javax.swing.JSeparator separadorCampos6;
    private javax.swing.JSeparator separadorCampos7;
    private javax.swing.JSeparator separadorCampos8;
    private javax.swing.JSeparator separadorCampos9;
    private javax.swing.JSeparator separadorEquipos1;
    private javax.swing.JSeparator separadorEquipos2;
    private javax.swing.JSeparator separadorEquipos3;
    private javax.swing.JSeparator separadorEquipos4;
    private javax.swing.JSeparator separadorEquipos5;
    private javax.swing.JSeparator separadorEquipos6;
    private javax.swing.JTextField txtDomingoPrimera;
    private javax.swing.JTextField txtDomingoSegunda;
    private javax.swing.JTextField txtExcFechaFin;
    private javax.swing.JTextField txtExcFechaInicio;
    private javax.swing.JTextField txtJuevesPrimera;
    private javax.swing.JTextField txtJuevesSegunda;
    private javax.swing.JTextField txtLunesPrimera;
    private javax.swing.JTextField txtLunesSegunda;
    private javax.swing.JTextField txtMartesPrimera;
    private javax.swing.JTextField txtMartesSegunda;
    private javax.swing.JTextField txtMiercolesPrimera;
    private javax.swing.JTextField txtMiercolesSegunda;
    private javax.swing.JTextField txtSabadoPrimera;
    private javax.swing.JTextField txtSabadoSegunda;
    private javax.swing.JTextField txtViernesPrimera;
    private javax.swing.JTextField txtViernesSegunda;
    // End of variables declaration//GEN-END:variables
}
