/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import connection.Conn;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sources.GestionarCampos;
import sources.GestionarEquipos;
import sources.MostrarDatos;
import sources.OCampos;
import sources.ORestriccion;

/**
 *
 * @author A644308
 */
public class Restricciones extends javax.swing.JFrame {
        private FondoVentana fondo;
        private final Conn conn = new Conn();
        private final MostrarDatos md = new MostrarDatos();
        private final GestionarCampos gc;
        private final GestionarEquipos ge;
        private boolean correcto = true;
        private int total = 0;
        private javax.swing.JCheckBox[] ckCampos;
        //Vector v=new Vector();
        
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
        ge = new GestionarEquipos(this);
        gc = new GestionarCampos(this);
        
        conn.conectar();
        total = conn.totalRegistros("campos");
        ResultSet campos = conn.getValues("*", "campos", "", "ID");
        ckCampos = new javax.swing.JCheckBox[total];
        pCamposExcluidos.setLayout(new GridLayout(0,3));
        int i = 0;
        try {
            while(campos.next()){
              ckCampos[i] = new javax.swing.JCheckBox(campos.getString("campo"));
              ckCampos[i].setEnabled(false);
              this.pCamposExcluidos.add(ckCampos[i]);
              i++;
            }   
        } catch (SQLException ex) {

        }
        conn.desconectar();
        
        
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
        
        ArrayList<ORestriccion> condiciones = new ArrayList<>();
        
        if(this.ckLunesEquipos.isSelected()){
            if(this.cbLunes.getSelectedIndex() > 0){
                condiciones.add(new ORestriccion(1,this.cbLunes.getSelectedIndex(),0,0));
            }else{
                System.out.println("no correcto");
                correcto = false;
            }
        }
        if(this.ckMartesEquipos.isSelected()){
            if(this.cbMartes.getSelectedIndex() > 0){
                condiciones.add(new ORestriccion(2,this.cbMartes.getSelectedIndex(),0,0));
            }else{
                correcto = false;
            }
        }
        if(this.ckMiercolesEquipos.isSelected()){
            if(this.cbMiercoles.getSelectedIndex() > 0){
                condiciones.add(new ORestriccion(3,this.cbMiercoles.getSelectedIndex(),0,0));
            }else{
                correcto = false;
            }
        }
        if(this.ckJuevesEquipos.isSelected()){
            if(this.cbJueves.getSelectedIndex() > 0){
                condiciones.add(new ORestriccion(4,this.cbJueves.getSelectedIndex(),0,0));
            }else{
                correcto = false;
            }
        }
        if(this.ckViernesEquipos.isSelected()){
            if(this.cbViernes.getSelectedIndex() > 0){
                condiciones.add(new ORestriccion(5,this.cbViernes.getSelectedIndex(),0,0));
            }else{
                correcto = false;
            }
        }
        if(this.ckSabadoEquipos.isSelected()){
            if(this.cbSabado.getSelectedIndex() > 0){
                condiciones.add(new ORestriccion(6,this.cbSabado.getSelectedIndex(),0,0));
            }else{
                correcto = false;
            }
        }
        if(this.ckDomingoEquipos.isSelected()){
            if(this.cbDomingo.getSelectedIndex() > 0){
                condiciones.add(new ORestriccion(7,this.cbDomingo.getSelectedIndex(),0,0));
            }else{
                correcto = false;
            }
        }
        
        if(correcto){
            ge.gestionarEquipo(condiciones);
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona una hora", "Error", JOptionPane.ERROR_MESSAGE);
        }
        /*String equipoSeleccionado = (String) cbEquipos.getSelectedItem();
        
        if(!"Selecciona un equipo".equals(equipoSeleccionado)){
            System.out.println("Mandar " + equipoSeleccionado + " a la BBDD.");
        }
        
        
        //this.comprobarCamposExcluidos();
        //this.comprobarHorasExcluidas();
        //this.comprobarEquiposDiasExcluidos();
        String equipoNoCoincidir = (String) cbNoCoincidir.getSelectedItem();
                if ("".equals(equipoNoCoincidir)){
                    
                }else{
                    System.out.println("Mandar " + equipoNoCoincidir + " a la BBDD.");
                }*/
    }
    
   
    /*public void comprobarEquiposDiasExcluidos(){

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
    }*/
    
    public void comprobarHorasExcluidas(){
//        if(ckPrimeraEquipos.isSelected()){
//                System.out.println("Mandar Primera Hora a BBDD");
//            }
//        if(ckSegundaEquipos.isSelected()){
//                System.out.println("Mandar Segunda Hora a BBDD");
//            }
    }
    
/*    public void comprobarCamposExcluidos(){
        
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
    }*/

    public void enableEquipos(){
                ckLunesEquipos.setEnabled(true);
                ckMartesEquipos.setEnabled(true);
                ckMiercolesEquipos.setEnabled(true);
                ckJuevesEquipos.setEnabled(true);
                ckViernesEquipos.setEnabled(true);
                ckSabadoEquipos.setEnabled(true);
                ckDomingoEquipos.setEnabled(true);
//                ckMP3.setEnabled(true);
//                ckMP4.setEnabled(true);
//                ckSTM.setEnabled(true);
//                ckTNC.setEnabled(true);
//                ckCHU.setEnabled(true);
//                ckDEL.setEnabled(true);
//                ckCHO.setEnabled(true);
//                ckDRA.setEnabled(true);
                cbNoCoincidir.setEnabled(true);
                ckCongelarEquipo.setEnabled(true);
                for(int i = 0; i < total; i++){
                    ckCampos[i].setEnabled(true);
                }
    }
    
    public void disableEquipos(){
                ckLunesEquipos.setEnabled(false);
                ckMartesEquipos.setEnabled(false);
                ckMiercolesEquipos.setEnabled(false);
                ckJuevesEquipos.setEnabled(false);
                ckViernesEquipos.setEnabled(false);
                ckSabadoEquipos.setEnabled(false);
                ckDomingoEquipos.setEnabled(false);
                cbLunes.setEnabled(false);
                cbMartes.setEnabled(false);
                cbMiercoles.setEnabled(false);
                cbJueves.setEnabled(false);
                cbViernes.setEnabled(false);
                cbSabado.setEnabled(false);
                cbDomingo.setEnabled(false);
//                ckMP3.setEnabled(false);
//                ckMP4.setEnabled(false);
//                ckSTM.setEnabled(false);
//                ckTNC.setEnabled(false);
//                ckCHU.setEnabled(false);
//                ckDEL.setEnabled(false);
//                ckCHO.setEnabled(false);
//                ckDRA.setEnabled(false);
                cbNoCoincidir.setEnabled(false);
                ckCongelarEquipo.setEnabled(false);
                for(int i = 0; i < total; i++){
                    ckCampos[i].setEnabled(false);
                }
    }
    
     public void restriccionesDeCampo(){
         
        /*String campoSeleccionado = (String) cbCampos.getSelectedItem();
        
        if(!"Selecciona un campo".equals(campoSeleccionado)){
            System.out.println("Mandar " + campoSeleccionado + " a la BBDD.");
        }*/
        
        ArrayList<OCampos> condiciones = new ArrayList<>();
        
        if(this.ckLunesCampos.isSelected()){
            if(this.ckLunesPrimera.isSelected()){
                condiciones.add(new OCampos(1,1));
            }
            if(this.ckLunesSegunda.isSelected()){
                condiciones.add(new OCampos(1,2));
            }
        }
        if(this.ckMartesCampos.isSelected()){
            if(this.ckMartesPrimera.isSelected()){
                condiciones.add(new OCampos(2,1));
            }
            if(this.ckMartesSegunda.isSelected()){
                condiciones.add(new OCampos(2,2));
            }
        }
        if(this.ckMiercolesCampos.isSelected()){
            if(this.ckMiercolesPrimera.isSelected()){
                condiciones.add(new OCampos(3,1));
            }
            if(this.ckMiercolesSegunda.isSelected()){
                condiciones.add(new OCampos(3,2));
            }
        }
        if(this.ckJuevesCampos.isSelected()){
            if(this.ckJuevesPrimera.isSelected()){
                condiciones.add(new OCampos(4,1));
            }
            if(this.ckJuevesSegunda.isSelected()){
                condiciones.add(new OCampos(4,2));
            }
        }
        if(this.ckViernesCampos.isSelected()){
            if(this.ckViernesPrimera.isSelected()){
                condiciones.add(new OCampos(5,1));
            }
            if(this.ckViernesSegunda.isSelected()){
                condiciones.add(new OCampos(5,2));
            }
        }
        if(this.ckSabadoCampos.isSelected()){
            if(this.ckSabadoPrimera.isSelected()){
                condiciones.add(new OCampos(6,1));
            }
            if(this.ckSabadoSegunda.isSelected()){
                condiciones.add(new OCampos(6,2));
            }
        }
        if(this.ckDomingoCampos.isSelected()){
            if(this.ckDomingoPrimera.isSelected()){
                condiciones.add(new OCampos(7,1));
            }
            if(this.ckDomingoSegunda.isSelected()){
                condiciones.add(new OCampos(7,2));
            }
        }
        
        gc.gestionarCampo(condiciones);
        
        /*this.comprobarCampoTexto();
        this.comprobarCampoHoraExcluida();
        this.comprobarCampoDiaExcluido();*/

    }
     
    /*public void comprobarCampoDiaExcluido(){
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
     }*/
    
    public void enableCampos(){
                cbEquipos.setEnabled(true);
                ckLunesCampos.setEnabled(true);
                ckMartesCampos.setEnabled(true);
                ckMiercolesCampos.setEnabled(true);
                ckJuevesCampos.setEnabled(true);
                ckViernesCampos.setEnabled(true);
                ckSabadoCampos.setEnabled(true);
                ckDomingoCampos.setEnabled(true);
                btnAceptarEquipos1.setEnabled(true);
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
        lblCamposExcluidos = new javax.swing.JLabel();
        separadorEquipos4 = new javax.swing.JSeparator();
        lblNoCoincidir = new javax.swing.JLabel();
        cbNoCoincidir = new javax.swing.JComboBox<>();
        separadorEquipos5 = new javax.swing.JSeparator();
        ckCongelarEquipo = new javax.swing.JCheckBox();
        separadorEquipos6 = new javax.swing.JSeparator();
        btnEditarEquipos = new javax.swing.JButton();
        btnAceptarEquipos = new javax.swing.JButton();
        cbLunes = new javax.swing.JComboBox<>();
        cbMartes = new javax.swing.JComboBox<>();
        cbMiercoles = new javax.swing.JComboBox<>();
        cbJueves = new javax.swing.JComboBox<>();
        cbViernes = new javax.swing.JComboBox<>();
        cbSabado = new javax.swing.JComboBox<>();
        cbDomingo = new javax.swing.JComboBox<>();
        pCamposExcluidos = new javax.swing.JPanel();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

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
        ckMartesEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckMartesEquiposActionPerformed(evt);
            }
        });

        ckMiercolesEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckMiercolesEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckMiercolesEquipos.setText("Miércoles");
        ckMiercolesEquipos.setEnabled(false);
        ckMiercolesEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckMiercolesEquiposActionPerformed(evt);
            }
        });

        ckJuevesEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckJuevesEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckJuevesEquipos.setText("Jueves");
        ckJuevesEquipos.setEnabled(false);
        ckJuevesEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckJuevesEquiposActionPerformed(evt);
            }
        });

        ckViernesEquipos.setBackground(new java.awt.Color(255, 255, 255));
        ckViernesEquipos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckViernesEquipos.setText("Viernes");
        ckViernesEquipos.setEnabled(false);
        ckViernesEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckViernesEquiposActionPerformed(evt);
            }
        });

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
        ckDomingoEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckDomingoEquiposActionPerformed(evt);
            }
        });

        separadorEquipos2.setForeground(new java.awt.Color(31, 87, 12));

        lblCamposExcluidos.setBackground(new java.awt.Color(255, 255, 255));
        lblCamposExcluidos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCamposExcluidos.setText("Campos excluídos:");
        lblCamposExcluidos.setMaximumSize(new java.awt.Dimension(191, 22));
        lblCamposExcluidos.setMinimumSize(new java.awt.Dimension(191, 22));
        lblCamposExcluidos.setPreferredSize(new java.awt.Dimension(191, 22));

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
        btnAceptarEquipos.setEnabled(false);
        btnAceptarEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarEquiposActionPerformed(evt);
            }
        });

        cbLunes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Hora", "Primera", "Segunda", "Ambas" }));
        cbLunes.setEnabled(false);
        cbLunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLunesActionPerformed(evt);
            }
        });

        cbMartes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Hora", "Primera", "Segunda", "Ambas" }));
        cbMartes.setEnabled(false);
        cbMartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMartesActionPerformed(evt);
            }
        });

        cbMiercoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Hora", "Primera", "Segunda", "Ambas" }));
        cbMiercoles.setEnabled(false);

        cbJueves.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Hora", "Primera", "Segunda", "Ambas" }));
        cbJueves.setEnabled(false);

        cbViernes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Hora", "Primera", "Segunda", "Ambas" }));
        cbViernes.setEnabled(false);

        cbSabado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Hora", "Primera", "Segunda", "Ambas" }));
        cbSabado.setEnabled(false);

        cbDomingo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Hora", "Primera", "Segunda", "Ambas" }));
        cbDomingo.setEnabled(false);

        pCamposExcluidos.setBackground(new java.awt.Color(255, 255, 255));
        pCamposExcluidos.setForeground(new java.awt.Color(255, 255, 255));
        pCamposExcluidos.setPreferredSize(new java.awt.Dimension(600, 200));

        javax.swing.GroupLayout pCamposExcluidosLayout = new javax.swing.GroupLayout(pCamposExcluidos);
        pCamposExcluidos.setLayout(pCamposExcluidosLayout);
        pCamposExcluidosLayout.setHorizontalGroup(
            pCamposExcluidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        pCamposExcluidosLayout.setVerticalGroup(
            pCamposExcluidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pEquiposLayout = new javax.swing.GroupLayout(pEquipos);
        pEquipos.setLayout(pEquiposLayout);
        pEquiposLayout.setHorizontalGroup(
            pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEquiposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEquiposLayout.createSequentialGroup()
                        .addComponent(separadorEquipos4)
                        .addContainerGap())
                    .addGroup(pEquiposLayout.createSequentialGroup()
                        .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pEquiposLayout.createSequentialGroup()
                                .addComponent(lblNoCoincidir, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbNoCoincidir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ckCongelarEquipo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(separadorEquipos5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pEquiposLayout.createSequentialGroup()
                        .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(separadorEquipos1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEquiposLayout.createSequentialGroup()
                                .addComponent(lblLogoEquipos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEquipos)
                                .addGap(18, 18, 18)
                                .addComponent(cbEquipos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEquiposLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEditarEquipos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAceptarEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(separadorEquipos6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorEquipos2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEquiposLayout.createSequentialGroup()
                                .addComponent(lblDiasEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ckMiercolesEquipos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ckJuevesEquipos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ckMartesEquipos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ckLunesEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbMartes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbJueves, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbMiercoles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ckSabadoEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ckViernesEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ckDomingoEquipos))
                                .addGap(6, 6, 6)
                                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbDomingo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbSabado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEquiposLayout.createSequentialGroup()
                                .addComponent(lblCamposExcluidos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pCamposExcluidos, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEquiposLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckMartesEquipos)
                            .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ckSabadoEquipos)
                                .addComponent(cbSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckMiercolesEquipos)
                            .addComponent(cbMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckDomingoEquipos)
                            .addComponent(cbDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckJuevesEquipos)
                            .addComponent(cbJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDiasEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ckLunesEquipos)
                        .addComponent(cbLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ckViernesEquipos)
                        .addComponent(cbViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorEquipos2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEquiposLayout.createSequentialGroup()
                        .addComponent(lblCamposExcluidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pCamposExcluidos, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorEquipos4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoCoincidir)
                    .addComponent(cbNoCoincidir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorEquipos5, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckCongelarEquipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorEquipos6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
        cbCampos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCamposItemStateChanged(evt);
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
        ckLunesCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckLunesCamposActionPerformed(evt);
            }
        });

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
        ckMartesCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckMartesCamposActionPerformed(evt);
            }
        });

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
        ckMiercolesCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckMiercolesCamposActionPerformed(evt);
            }
        });

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
        ckJuevesCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckJuevesCamposActionPerformed(evt);
            }
        });

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
        ckViernesCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckViernesCamposActionPerformed(evt);
            }
        });

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
        ckSabadoCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckSabadoCamposActionPerformed(evt);
            }
        });

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
        ckDomingoCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckDomingoCamposActionPerformed(evt);
            }
        });

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
        btnAceptarEquipos1.setEnabled(false);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarEquipos1)
                    .addComponent(btnEditarEquipos1))
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(31, 87, 12));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Inactividad");

        jMenuItem1.setBackground(new java.awt.Color(31, 87, 12));
        jMenuItem1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        jMenuItem1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/lupa.png"))); // NOI18N
        jMenuItem1.setText(" Ver días de inactividad");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 658, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                    .addComponent(pEquipos, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        int input = JOptionPane.showConfirmDialog(null, "¿Desea guardar los horarios para el campo seleccionado?", "Gestión de campos", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {
            if(this.ckCongelarCampo.isSelected()){
                gc.congelarCampo(this.cbCampos.getSelectedIndex(), true);
            }else{  
                gc.congelarCampo(this.cbCampos.getSelectedIndex(), false);
            }
            this.restriccionesDeCampo();
            this.disableCampos();
        }  
    }//GEN-LAST:event_btnAceptarEquipos1ActionPerformed

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

    private void btnAceptarEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarEquiposActionPerformed
        ImageIcon icon = new ImageIcon("src/resources/aceptar.png");
        int input = JOptionPane.showConfirmDialog(null, "¿Desea aplicar las restricciones para el equipo seleccionado?", "Aplicar restricciones",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {

//            this.restriccionesDeEquipo();
            if(correcto){
                this.disableEquipos();
            }
        }
    }//GEN-LAST:event_btnAceptarEquiposActionPerformed

    private void btnEditarEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEquiposActionPerformed
        if (cbEquipos.getSelectedItem().toString().equals("Selecciona un equipo")){
            ImageIcon icon = new ImageIcon("src/resources/warning.png");
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un equipo","Seleccione un equipo", JOptionPane.QUESTION_MESSAGE, icon);
        }else {
            this.enableEquipos();
            btnAceptarEquipos.setEnabled(true);

        }
    }//GEN-LAST:event_btnEditarEquiposActionPerformed

    private void ckCongelarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckCongelarEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckCongelarEquipoActionPerformed

    private void ckSabadoEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckSabadoEquiposActionPerformed
        if(ckSabadoEquipos.isSelected()){
            cbSabado.setEnabled(true);

        }else{
            cbSabado.setEnabled(false);

        }
    }//GEN-LAST:event_ckSabadoEquiposActionPerformed

    private void ckLunesEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckLunesEquiposActionPerformed

        if(ckLunesEquipos.isSelected()){
            cbLunes.setEnabled(true);

        }else{
            cbLunes.setEnabled(false);

        }       
    }//GEN-LAST:event_ckLunesEquiposActionPerformed

    private void cbEquiposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEquiposItemStateChanged
        md.llenarComboNoCoincidir(this.cbNoCoincidir, this.cbEquipos.getSelectedIndex());
        ge.mostrarRestricciones();
    }//GEN-LAST:event_cbEquiposItemStateChanged

    private void cbMartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMartesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMartesActionPerformed

    private void ckMartesEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckMartesEquiposActionPerformed
        if(ckMartesEquipos.isSelected()){
            cbMartes.setEnabled(true);

        }else{
            cbMartes.setEnabled(false);

        }
    }//GEN-LAST:event_ckMartesEquiposActionPerformed

    private void ckMiercolesEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckMiercolesEquiposActionPerformed
        if(ckMiercolesEquipos.isSelected()){
            cbMiercoles.setEnabled(true);

        }else{
            cbMiercoles.setEnabled(false);

        }
    }//GEN-LAST:event_ckMiercolesEquiposActionPerformed

    private void ckJuevesEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckJuevesEquiposActionPerformed
        if(ckJuevesEquipos.isSelected()){
            cbJueves.setEnabled(true);

        }else{
            cbJueves.setEnabled(false);

        }
    }//GEN-LAST:event_ckJuevesEquiposActionPerformed

    private void ckViernesEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckViernesEquiposActionPerformed
        if(ckViernesEquipos.isSelected()){
            cbViernes.setEnabled(true);

        }else{
            cbViernes.setEnabled(false);

        }
    }//GEN-LAST:event_ckViernesEquiposActionPerformed

    private void ckDomingoEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckDomingoEquiposActionPerformed
        if(ckDomingoEquipos.isSelected()){
            cbDomingo.setEnabled(true);

        }else{
            cbDomingo.setEnabled(false);

        }
    }//GEN-LAST:event_ckDomingoEquiposActionPerformed

    private void cbCamposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCamposItemStateChanged
        gc.mostrarDatos();
    }//GEN-LAST:event_cbCamposItemStateChanged

    private void cbLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLunesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLunesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Inactividad  frm = new Inactividad ();

        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
   
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
    public javax.swing.JComboBox<String> cbCampos;
    public javax.swing.JComboBox<String> cbDomingo;
    public javax.swing.JComboBox<String> cbEquipos;
    public javax.swing.JComboBox<String> cbJueves;
    public javax.swing.JComboBox<String> cbLunes;
    public javax.swing.JComboBox<String> cbMartes;
    public javax.swing.JComboBox<String> cbMiercoles;
    public javax.swing.JComboBox<String> cbNoCoincidir;
    public javax.swing.JComboBox<String> cbSabado;
    public javax.swing.JComboBox<String> cbViernes;
    public javax.swing.JCheckBox ckCongelarCampo;
    public javax.swing.JCheckBox ckCongelarEquipo;
    public javax.swing.JCheckBox ckDomingoCampos;
    public javax.swing.JCheckBox ckDomingoEquipos;
    public javax.swing.JCheckBox ckDomingoPrimera;
    public javax.swing.JCheckBox ckDomingoSegunda;
    public javax.swing.JCheckBox ckJuevesCampos;
    public javax.swing.JCheckBox ckJuevesEquipos;
    public javax.swing.JCheckBox ckJuevesPrimera;
    public javax.swing.JCheckBox ckJuevesSegunda;
    public javax.swing.JCheckBox ckLunesCampos;
    public javax.swing.JCheckBox ckLunesEquipos;
    public javax.swing.JCheckBox ckLunesPrimera;
    public javax.swing.JCheckBox ckLunesSegunda;
    public javax.swing.JCheckBox ckMartesCampos;
    public javax.swing.JCheckBox ckMartesEquipos;
    public javax.swing.JCheckBox ckMartesPrimera;
    public javax.swing.JCheckBox ckMartesSegunda;
    public javax.swing.JCheckBox ckMiercolesCampos;
    public javax.swing.JCheckBox ckMiercolesEquipos;
    public javax.swing.JCheckBox ckMiercolesPrimera;
    public javax.swing.JCheckBox ckMiercolesSegunda;
    public javax.swing.JCheckBox ckSabadoCampos;
    public javax.swing.JCheckBox ckSabadoEquipos;
    public javax.swing.JCheckBox ckSabadoPrimera;
    public javax.swing.JCheckBox ckSabadoSegunda;
    public javax.swing.JCheckBox ckViernesCampos;
    public javax.swing.JCheckBox ckViernesEquipos;
    public javax.swing.JCheckBox ckViernesPrimera;
    public javax.swing.JCheckBox ckViernesSegunda;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblCampos;
    private javax.swing.JLabel lblCamposExcluidos;
    private javax.swing.JLabel lblDiasEquipos;
    private javax.swing.JLabel lblEquipos;
    private javax.swing.JLabel lblLogoCampos;
    private javax.swing.JLabel lblLogoEquipos;
    private javax.swing.JLabel lblNoCoincidir;
    private javax.swing.JPanel pCampos;
    private javax.swing.JPanel pCamposExcluidos;
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
    private javax.swing.JSeparator separadorEquipos4;
    private javax.swing.JSeparator separadorEquipos5;
    private javax.swing.JSeparator separadorEquipos6;
    public javax.swing.JTextField txtDomingoPrimera;
    public javax.swing.JTextField txtDomingoSegunda;
    public javax.swing.JTextField txtJuevesPrimera;
    public javax.swing.JTextField txtJuevesSegunda;
    public javax.swing.JTextField txtLunesPrimera;
    public javax.swing.JTextField txtLunesSegunda;
    public javax.swing.JTextField txtMartesPrimera;
    public javax.swing.JTextField txtMartesSegunda;
    public javax.swing.JTextField txtMiercolesPrimera;
    public javax.swing.JTextField txtMiercolesSegunda;
    public javax.swing.JTextField txtSabadoPrimera;
    public javax.swing.JTextField txtSabadoSegunda;
    public javax.swing.JTextField txtViernesPrimera;
    public javax.swing.JTextField txtViernesSegunda;
    // End of variables declaration//GEN-END:variables
}
