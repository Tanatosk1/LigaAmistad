package views;

import connection.Conn;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sources.GestionarArbitros;
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
        private final GestionarArbitros ga;
        private boolean correcto = true;
        public int total = 0;
        public javax.swing.JCheckBox[] ckCampos;
        
public Restricciones() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
        setIconImage(icon);
        fondo = new FondoVentana();
        this.add(fondo);
        
        md.llenarComboEquipos(this.cbEquipos);
        md.llenarComboCampos(this.cbCampos);
        md.llenarComboArbitros(this.cbArbitros);
        md.llenarCamposArbitros(this.cbNoCoincidirArbitro);
        ge = new GestionarEquipos(this);
        gc = new GestionarCampos(this);
        ga = new GestionarArbitros(this);
        
        conn.conectar();
        total = conn.totalRegistros("campos");
        ResultSet campos = conn.getValues("*", "campos", "", "ID");
        
        ckCampos = new javax.swing.JCheckBox[total+1];
        pCamposExcluidos.setLayout(new GridLayout(0,3));
        pCamposExcluidos.setBackground(Color.white);
        
        
        int i = 1;
        try {
            while(campos.next()){
              ckCampos[i] = new javax.swing.JCheckBox(campos.getString("campo"));
              ckCampos[i].setEnabled(false);
              ckCampos[i].setBackground(Color.white);
              ckCampos[i].setFont(new java.awt.Font("Tahoma", 0, 12));
              this.pCamposExcluidos.add(ckCampos[i]);
              i++;
            }   
        } catch (SQLException ex) {

        }
        conn.desconectar();
        
        gc.contarHorarios(lblNumeroCamposActivos);
        ga.contarHorarios(lblNumeroArbitrosActivos);
        
        
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
            switch (this.cbLunes.getSelectedIndex()) {
                case 1:
                    condiciones.add(new ORestriccion(1,this.cbLunes.getSelectedIndex(),null,null,null));
                    correcto = true;
                    break;
                case 2:
                    condiciones.add(new ORestriccion(1,this.cbLunes.getSelectedIndex(),null,null,null));
                    correcto = true;
                    break;
                case 3:
                    condiciones.add(new ORestriccion(1,1,null,null,null));
                    condiciones.add(new ORestriccion(1,2,null,null,null));
                    correcto = true;
                    break;
                default:
                    correcto = false;
                    break;
            }
        }
        if(this.ckMartesEquipos.isSelected()){
            switch (this.cbMartes.getSelectedIndex()) {
                case 1:
                    condiciones.add(new ORestriccion(2,1,null,null,null));
                    correcto = true;
                    break;
                case 2:
                    condiciones.add(new ORestriccion(2,2,null,null,null));
                    correcto = true;
                    break;
                case 3:
                    condiciones.add(new ORestriccion(2,1,null,null,null));
                    condiciones.add(new ORestriccion(2,2,null,null,null));
                    correcto = true;
                    break;
                default:
                    correcto = false;
                    break;
            }
        }
        if(this.ckMiercolesEquipos.isSelected()){
            switch (this.cbMiercoles.getSelectedIndex()) {
                case 1:
                    condiciones.add(new ORestriccion(3,1,null,null,null));
                    correcto = true;
                    break;
                case 2:
                    condiciones.add(new ORestriccion(3,2,null,null,null));
                    correcto = true;
                    break;
                case 3:
                    condiciones.add(new ORestriccion(3,1,null,null,null));
                    condiciones.add(new ORestriccion(3,2,null,null,null));
                    correcto = true;
                    break;
                default:
                    correcto = false;
                    break;
            }
        }
        if(this.ckJuevesEquipos.isSelected()){
            switch (this.cbJueves.getSelectedIndex()) {
                case 1:
                    condiciones.add(new ORestriccion(4,1,null,null,null));
                    correcto = true;
                    break;
                case 2:
                    condiciones.add(new ORestriccion(4,2,null,null,null));
                    correcto = true;
                    break;
                case 3:
                    condiciones.add(new ORestriccion(4,1,null,null,null));
                    condiciones.add(new ORestriccion(4,2,null,null,null));
                    correcto = true;
                    break;
                default:
                    correcto = false;
                    break;
            }
        }
        if(this.ckViernesEquipos.isSelected()){
            switch (this.cbViernes.getSelectedIndex()) {
                case 1:
                    condiciones.add(new ORestriccion(5,1,null,null,null));
                    correcto = true;
                    break;
                case 2:
                    condiciones.add(new ORestriccion(5,2,null,null,null));
                    correcto = true;
                    break;
                case 3:
                    condiciones.add(new ORestriccion(5,1,null,null,null));
                    condiciones.add(new ORestriccion(5,2,null,null,null));
                    correcto = true;
                    break;
                default:
                    correcto = false;
                    break;
            }
        }
        if(this.ckSabadoEquipos.isSelected()){
            switch (this.cbSabado.getSelectedIndex()) {
                case 1:
                    condiciones.add(new ORestriccion(6,1,null,null,null));
                    correcto = true;
                    break;
                case 2:
                    condiciones.add(new ORestriccion(6,2,null,null,null));
                    correcto = true;
                    break;
                case 3:
                    condiciones.add(new ORestriccion(6,1,null,null,null));
                    condiciones.add(new ORestriccion(6,2,null,null,null));
                    correcto = true;
                    break;
                default:
                    correcto = false;
                    break;
            }
        }
        if(this.ckDomingoEquipos.isSelected()){
            switch (this.cbDomingo.getSelectedIndex()) {
                case 1:
                    condiciones.add(new ORestriccion(7,1,null,null,null));
                    correcto = true;
                    break;
                case 2:
                    condiciones.add(new ORestriccion(7,2,null,null,null));
                    correcto = true;
                    break;
                case 3:
                    condiciones.add(new ORestriccion(7,1,null,null,null));
                    condiciones.add(new ORestriccion(7,2,null,null,null));
                    correcto = true;
                    break;
                default:
                    correcto = false;
                    break;
            }
        }
        
        for(int i = 1; i < total+1; i++){
            if(this.ckCampos[i].isSelected()){
                condiciones.add(new ORestriccion(null, null, i, null,null));
            }
        }
        
        if(this.cbNoCoincidir.getSelectedIndex() > 0){
            conn.conectar();
            ResultSet idEquipoNoCoincidir = conn.getValues("ID", "Equipos", "NOMBRE like '" + cbNoCoincidir.getSelectedItem()+"'", "");
            try {
                while(idEquipoNoCoincidir.next()){
                    condiciones.add(new ORestriccion(null, null, null, idEquipoNoCoincidir.getInt("ID"),null));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    idEquipoNoCoincidir.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                conn.desconectar();
            }   
        }
        
        if(this.cbNoCoincidir1.getSelectedIndex() > 0){
            condiciones.add(new ORestriccion(null, null, null, null, cbNoCoincidir1.getSelectedIndex()));
        }
        
        if(correcto){
            ge.gestionarEquipo(condiciones);
        }else{
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(rootPane, "Selecciona una hora", "Error", JOptionPane.QUESTION_MESSAGE, icon);
  
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
                cbNoCoincidir.setEnabled(true);
                cbNoCoincidir1.setEnabled(true);
                ckCongelarEquipo.setEnabled(true);
                for(int i = 1; i < total+1; i++){
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
                cbNoCoincidir.setEnabled(false);
                cbNoCoincidir1.setEnabled(false);
                ckCongelarEquipo.setEnabled(false);
                for(int i = 1; i < total+1; i++){
                    ckCampos[i].setSelected(false);
                    ckCampos[i].setEnabled(false);
                }
    }
    
     public void restriccionesDeCampo(){
         
       
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
                btnAceptarCampo.setEnabled(true);
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
    
    public void enableHorasAsignar(){
        cmbHorasAsignar.setEnabled(true);
    }
    
    public void enableArbitros(){
        
                cbNoCoincidirArbitro.setEnabled(true);
                ckLunesArbitro.setEnabled(true);
                ckMartesArbitro.setEnabled(true);
                ckMiercolesArbitro.setEnabled(true);
                ckJuevesArbitro.setEnabled(true);
                ckViernesArbitro.setEnabled(true);
                ckSabadoArbitro.setEnabled(true);
                ckDomingoArbitro.setEnabled(true);
                btnAceptarArbitro.setEnabled(true);
                ckCongelarArbitro.setEnabled(true);
    }
    
    public void disableArbitros(){
        
                cbNoCoincidirArbitro.setEnabled(false);
                ckLunesArbitro.setEnabled(false);
                ckMartesArbitro.setEnabled(false);
                ckMiercolesArbitro.setEnabled(false);
                ckJuevesArbitro.setEnabled(false);
                ckViernesArbitro.setEnabled(false);
                ckSabadoArbitro.setEnabled(false);
                ckDomingoArbitro.setEnabled(false);
                btnAceptarArbitro.setEnabled(false);
                ckCongelarArbitro.setEnabled(false);
    }
    
    public void horasEquipossEnabled (){
    
                if (ckLunesEquipos.isSelected()){
                    cbLunes.setEnabled(true);
                }
                if (ckMartesEquipos.isSelected()){
                    cbMartes.setEnabled(true);
                } 
                if (ckMiercolesEquipos.isSelected()){
                    cbMiercoles.setEnabled(true);
                }
                if (ckJuevesEquipos.isSelected()){
                    cbJueves.setEnabled(true);
                }                 
                if (ckViernesEquipos.isSelected()){
                    cbViernes.setEnabled(true);
                }
                if (ckSabadoEquipos.isSelected()){
                    cbSabado.setEnabled(true);
                } 
                if (ckDomingoEquipos.isSelected()){
                    cbDomingo.setEnabled(true);
                }              
    }
    public void horasCamposEnabled (){
    
                if (ckLunesCampos.isSelected()){
                    ckLunesPrimera.setEnabled(true);
                    ckLunesSegunda.setEnabled(true);
                }
                if (ckMartesCampos.isSelected()){
                    ckMartesPrimera.setEnabled(true);
                    ckMartesSegunda.setEnabled(true); 
                } 
                if (ckMiercolesCampos.isSelected()){
                    ckMiercolesPrimera.setEnabled(true);
                    ckMiercolesSegunda.setEnabled(true);
                }
                if (ckJuevesCampos.isSelected()){
                    ckJuevesPrimera.setEnabled(true);
                    ckJuevesSegunda.setEnabled(true); 
                }                 
                if (ckViernesCampos.isSelected()){
                    ckViernesPrimera.setEnabled(true);
                    ckViernesSegunda.setEnabled(true);
                }
                if (ckSabadoCampos.isSelected()){
                    ckSabadoPrimera.setEnabled(true);
                    ckSabadoSegunda.setEnabled(true);
                } 
                if (ckDomingoCampos.isSelected()){
                    ckDomingoPrimera.setEnabled(true);
                    ckDomingoSegunda.setEnabled(true); 
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
        lblNoCoincidir1 = new javax.swing.JLabel();
        cbNoCoincidir1 = new javax.swing.JComboBox<>();
        separadorEquipos7 = new javax.swing.JSeparator();
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
        ckDomingoCampos = new javax.swing.JCheckBox();
        ckDomingoPrimera = new javax.swing.JCheckBox();
        txtDomingoPrimera = new javax.swing.JTextField();
        ckDomingoSegunda = new javax.swing.JCheckBox();
        txtDomingoSegunda = new javax.swing.JTextField();
        separadorCampos8 = new javax.swing.JSeparator();
        ckCongelarCampo = new javax.swing.JCheckBox();
        btnEditarArbitro = new javax.swing.JButton();
        btnAceptarArbitro = new javax.swing.JButton();
        lblCamposActivos = new javax.swing.JLabel();
        lblNumeroCamposActivos = new javax.swing.JLabel();
        separadorCampos10 = new javax.swing.JSeparator();
        lblLogoArbitros1 = new javax.swing.JLabel();
        blArbitro = new javax.swing.JLabel();
        cbArbitros = new javax.swing.JComboBox<>();
        ckJuevesArbitro = new javax.swing.JCheckBox();
        ckDomingoArbitro = new javax.swing.JCheckBox();
        ckMiercolesArbitro = new javax.swing.JCheckBox();
        ckViernesArbitro = new javax.swing.JCheckBox();
        ckSabadoArbitro = new javax.swing.JCheckBox();
        ckLunesArbitro = new javax.swing.JCheckBox();
        ckMartesArbitro = new javax.swing.JCheckBox();
        separadorCampos11 = new javax.swing.JSeparator();
        separadorCampos12 = new javax.swing.JSeparator();
        lblNoCoincidir2 = new javax.swing.JLabel();
        cbNoCoincidirArbitro = new javax.swing.JComboBox<>();
        separadorCampos13 = new javax.swing.JSeparator();
        lblCamposActivos1 = new javax.swing.JLabel();
        lblNumeroArbitrosActivos = new javax.swing.JLabel();
        ckCongelarArbitro = new javax.swing.JCheckBox();
        btnEditarCampo = new javax.swing.JButton();
        btnAceptarCampo = new javax.swing.JButton();
        lblHorasASeleccionar = new javax.swing.JLabel();
        cmbHorasAsignar = new javax.swing.JComboBox<>();
        mbRestricciones = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Configuración");
        setMinimumSize(new java.awt.Dimension(1366, 768));

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

        lblNoCoincidir1.setBackground(new java.awt.Color(255, 255, 255));
        lblNoCoincidir1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNoCoincidir1.setText("No coincidir con árbitro:");

        cbNoCoincidir1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbNoCoincidir1.setEnabled(false);
        cbNoCoincidir1.setPreferredSize(new java.awt.Dimension(300, 28));
        cbNoCoincidir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNoCoincidir1ActionPerformed(evt);
            }
        });

        separadorEquipos7.setForeground(new java.awt.Color(31, 87, 12));

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
                            .addComponent(separadorEquipos6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorEquipos2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEquiposLayout.createSequentialGroup()
                                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                                .addComponent(cbSabado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEquiposLayout.createSequentialGroup()
                                        .addComponent(lblCamposExcluidos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pCamposExcluidos, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(pEquiposLayout.createSequentialGroup()
                        .addComponent(lblNoCoincidir1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbNoCoincidir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(separadorEquipos7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pEquiposLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEditarEquipos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptarEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        pEquiposLayout.setVerticalGroup(
            pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEquiposLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEquipos)
                        .addComponent(cbEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblLogoEquipos))
                .addGap(18, 18, 18)
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
                    .addComponent(lblCamposExcluidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pCamposExcluidos, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(separadorEquipos7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoCoincidir1)
                    .addComponent(cbNoCoincidir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarEquipos)
                    .addComponent(btnEditarEquipos))
                .addGap(18, 18, 18))
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

        btnEditarArbitro.setBackground(new java.awt.Color(31, 87, 12));
        btnEditarArbitro.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarArbitro.setText("Editar Árbitro");
        btnEditarArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarArbitroActionPerformed(evt);
            }
        });

        btnAceptarArbitro.setBackground(new java.awt.Color(31, 87, 12));
        btnAceptarArbitro.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptarArbitro.setText("Aceptar");
        btnAceptarArbitro.setEnabled(false);
        btnAceptarArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarArbitroActionPerformed(evt);
            }
        });

        lblCamposActivos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCamposActivos.setText("Número de horas activas: ");

        lblNumeroCamposActivos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNumeroCamposActivos.setText("0");

        separadorCampos10.setForeground(new java.awt.Color(31, 87, 12));

        lblLogoArbitros1.setBackground(new java.awt.Color(255, 255, 255));
        lblLogoArbitros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/arbitros.png"))); // NOI18N

        blArbitro.setBackground(new java.awt.Color(255, 255, 255));
        blArbitro.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        blArbitro.setForeground(new java.awt.Color(31, 87, 12));
        blArbitro.setText("Árbitro");

        cbArbitros.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        cbArbitros.setForeground(new java.awt.Color(31, 87, 12));
        cbArbitros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un árbitro" }));
        cbArbitros.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbArbitrosItemStateChanged(evt);
            }
        });

        ckJuevesArbitro.setBackground(new java.awt.Color(255, 255, 255));
        ckJuevesArbitro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckJuevesArbitro.setText("Jueves");
        ckJuevesArbitro.setEnabled(false);
        ckJuevesArbitro.setMaximumSize(new java.awt.Dimension(111, 31));
        ckJuevesArbitro.setMinimumSize(new java.awt.Dimension(111, 31));
        ckJuevesArbitro.setPreferredSize(new java.awt.Dimension(111, 31));
        ckJuevesArbitro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckJuevesArbitroItemStateChanged(evt);
            }
        });

        ckDomingoArbitro.setBackground(new java.awt.Color(255, 255, 255));
        ckDomingoArbitro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckDomingoArbitro.setText("Domingo");
        ckDomingoArbitro.setEnabled(false);
        ckDomingoArbitro.setMaximumSize(new java.awt.Dimension(111, 31));
        ckDomingoArbitro.setMinimumSize(new java.awt.Dimension(111, 31));
        ckDomingoArbitro.setPreferredSize(new java.awt.Dimension(111, 31));
        ckDomingoArbitro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckDomingoArbitroItemStateChanged(evt);
            }
        });

        ckMiercolesArbitro.setBackground(new java.awt.Color(255, 255, 255));
        ckMiercolesArbitro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckMiercolesArbitro.setText("Miércoles");
        ckMiercolesArbitro.setEnabled(false);
        ckMiercolesArbitro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckMiercolesArbitroItemStateChanged(evt);
            }
        });

        ckViernesArbitro.setBackground(new java.awt.Color(255, 255, 255));
        ckViernesArbitro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckViernesArbitro.setText("Viernes");
        ckViernesArbitro.setEnabled(false);
        ckViernesArbitro.setMaximumSize(new java.awt.Dimension(111, 31));
        ckViernesArbitro.setMinimumSize(new java.awt.Dimension(111, 31));
        ckViernesArbitro.setPreferredSize(new java.awt.Dimension(111, 31));
        ckViernesArbitro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckViernesArbitroItemStateChanged(evt);
            }
        });

        ckSabadoArbitro.setBackground(new java.awt.Color(255, 255, 255));
        ckSabadoArbitro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckSabadoArbitro.setText("Sábado");
        ckSabadoArbitro.setEnabled(false);
        ckSabadoArbitro.setMaximumSize(new java.awt.Dimension(111, 31));
        ckSabadoArbitro.setMinimumSize(new java.awt.Dimension(111, 31));
        ckSabadoArbitro.setPreferredSize(new java.awt.Dimension(111, 31));
        ckSabadoArbitro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckSabadoArbitroItemStateChanged(evt);
            }
        });

        ckLunesArbitro.setBackground(new java.awt.Color(255, 255, 255));
        ckLunesArbitro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckLunesArbitro.setText("Lunes");
        ckLunesArbitro.setEnabled(false);
        ckLunesArbitro.setMaximumSize(new java.awt.Dimension(111, 31));
        ckLunesArbitro.setMinimumSize(new java.awt.Dimension(111, 31));
        ckLunesArbitro.setPreferredSize(new java.awt.Dimension(111, 31));
        ckLunesArbitro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckLunesArbitroItemStateChanged(evt);
            }
        });

        ckMartesArbitro.setBackground(new java.awt.Color(255, 255, 255));
        ckMartesArbitro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ckMartesArbitro.setText("Martes");
        ckMartesArbitro.setEnabled(false);
        ckMartesArbitro.setMaximumSize(new java.awt.Dimension(111, 31));
        ckMartesArbitro.setMinimumSize(new java.awt.Dimension(111, 31));
        ckMartesArbitro.setPreferredSize(new java.awt.Dimension(111, 31));
        ckMartesArbitro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckMartesArbitroItemStateChanged(evt);
            }
        });

        separadorCampos11.setForeground(new java.awt.Color(31, 87, 12));

        separadorCampos12.setForeground(new java.awt.Color(31, 87, 12));

        lblNoCoincidir2.setBackground(new java.awt.Color(255, 255, 255));
        lblNoCoincidir2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNoCoincidir2.setText("No coincidir con campo:");

        cbNoCoincidirArbitro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbNoCoincidirArbitro.setEnabled(false);
        cbNoCoincidirArbitro.setPreferredSize(new java.awt.Dimension(300, 28));

        separadorCampos13.setForeground(new java.awt.Color(31, 87, 12));

        lblCamposActivos1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCamposActivos1.setText("Número de horas activas totales: ");

        lblNumeroArbitrosActivos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNumeroArbitrosActivos.setText("0");

        ckCongelarArbitro.setBackground(new java.awt.Color(255, 255, 255));
        ckCongelarArbitro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ckCongelarArbitro.setForeground(new java.awt.Color(255, 0, 0));
        ckCongelarArbitro.setText("Congelar Árbitro");
        ckCongelarArbitro.setEnabled(false);

        btnEditarCampo.setBackground(new java.awt.Color(31, 87, 12));
        btnEditarCampo.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarCampo.setText("Editar Campo");
        btnEditarCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCampoActionPerformed(evt);
            }
        });

        btnAceptarCampo.setBackground(new java.awt.Color(31, 87, 12));
        btnAceptarCampo.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptarCampo.setText("Aceptar");
        btnAceptarCampo.setEnabled(false);
        btnAceptarCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarCampoActionPerformed(evt);
            }
        });

        lblHorasASeleccionar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHorasASeleccionar.setText("Horas a asignar:");
        lblHorasASeleccionar.setToolTipText("");

        cmbHorasAsignar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbHorasAsignar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "2", "4", "6", "8", "10", "12", "14" }));
        cmbHorasAsignar.setSelectedIndex(-1);
        cmbHorasAsignar.setToolTipText("");
        cmbHorasAsignar.setEnabled(false);
        cmbHorasAsignar.setPreferredSize(new java.awt.Dimension(300, 28));
        cmbHorasAsignar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHorasAsignarItemStateChanged(evt);
            }
        });
        cmbHorasAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHorasAsignarActionPerformed(evt);
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
                        .addComponent(lblLogoArbitros1)
                        .addGap(18, 18, 18)
                        .addComponent(blArbitro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbArbitros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pCamposLayout.createSequentialGroup()
                        .addComponent(lblLogoCampos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCampos)
                        .addGap(18, 18, 18)
                        .addComponent(cbCampos, 0, 452, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCamposLayout.createSequentialGroup()
                        .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(separadorCampos10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorCampos8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorCampos5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorCampos2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorCampos3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorCampos4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorCampos1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorCampos6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCamposLayout.createSequentialGroup()
                                .addComponent(ckCongelarCampo)
                                .addGap(18, 18, 18)
                                .addComponent(lblCamposActivos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumeroCamposActivos)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCamposLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEditarCampo)
                        .addGap(8, 8, 8)
                        .addComponent(btnAceptarCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pCamposLayout.createSequentialGroup()
                        .addComponent(lblNoCoincidir2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbNoCoincidirArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(pCamposLayout.createSequentialGroup()
                        .addComponent(lblHorasASeleccionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbHorasAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(pCamposLayout.createSequentialGroup()
                        .addComponent(ckSabadoArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckDomingoArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarArbitro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptarArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(pCamposLayout.createSequentialGroup()
                        .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pCamposLayout.createSequentialGroup()
                                .addComponent(lblCamposActivos1)
                                .addGap(18, 18, 18)
                                .addComponent(lblNumeroArbitrosActivos))
                            .addComponent(ckCongelarArbitro)
                            .addGroup(pCamposLayout.createSequentialGroup()
                                .addComponent(ckLunesArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ckMartesArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ckMiercolesArbitro)
                                .addGap(18, 18, 18)
                                .addComponent(ckJuevesArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ckViernesArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(separadorCampos11, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(separadorCampos12, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(separadorCampos13, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(ckJuevesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ckJuevesPrimera)
                                .addGap(18, 18, 18)
                                .addComponent(txtJuevesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ckJuevesSegunda)
                                .addGap(18, 18, 18)
                                .addComponent(txtJuevesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(ckMartesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ckMartesPrimera)
                                .addGap(18, 18, 18)
                                .addComponent(txtMartesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ckMartesSegunda)
                                .addGap(18, 18, 18)
                                .addComponent(txtMartesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pCamposLayout.createSequentialGroup()
                                .addComponent(ckLunesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ckLunesPrimera)
                                .addGap(18, 18, 18)
                                .addComponent(txtLunesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ckLunesSegunda)
                                .addGap(18, 18, 18)
                                .addComponent(txtLunesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pCamposLayout.setVerticalGroup(
            pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCamposLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCampos))
                    .addComponent(lblLogoCampos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorCampos1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckLunesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckLunesPrimera)
                    .addComponent(txtLunesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckLunesSegunda)
                    .addComponent(txtLunesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorCampos2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckMartesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckMartesPrimera)
                    .addComponent(txtMartesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckMartesSegunda)
                    .addComponent(txtMartesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorCampos3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckMiercolesCampos)
                    .addComponent(ckMiercolesPrimera)
                    .addComponent(txtMiercolesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckMiercolesSegunda)
                    .addComponent(txtMiercolesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorCampos4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckJuevesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckJuevesPrimera)
                    .addComponent(txtJuevesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckJuevesSegunda)
                    .addComponent(txtJuevesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorCampos5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckViernesCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckViernesPrimera)
                    .addComponent(txtViernesPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckViernesSegunda)
                    .addComponent(txtViernesSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorCampos6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckSabadoCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckSabadoPrimera)
                    .addComponent(txtSabadoPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckSabadoSegunda)
                    .addComponent(txtSabadoSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorCampos10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckDomingoCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckDomingoPrimera)
                    .addComponent(txtDomingoPrimera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckDomingoSegunda)
                    .addComponent(txtDomingoSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorCampos8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckCongelarCampo)
                    .addComponent(lblCamposActivos)
                    .addComponent(lblNumeroCamposActivos))
                .addGap(6, 6, 6)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarCampo)
                    .addComponent(btnAceptarCampo))
                .addGap(35, 35, 35)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbArbitros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(blArbitro))
                    .addComponent(lblLogoArbitros1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHorasASeleccionar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbHorasAsignar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorCampos11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckLunesArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckMartesArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckMiercolesArbitro)
                    .addComponent(ckJuevesArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckViernesArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckSabadoArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckDomingoArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarArbitro)
                    .addComponent(btnAceptarArbitro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separadorCampos12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoCoincidir2)
                    .addComponent(cbNoCoincidirArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorCampos13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckCongelarArbitro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCamposActivos1)
                    .addComponent(lblNumeroArbitrosActivos))
                .addContainerGap())
        );

        mbRestricciones.setBackground(new java.awt.Color(31, 87, 12));
        mbRestricciones.setForeground(new java.awt.Color(255, 255, 255));
        setJMenuBar(mbRestricciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 658, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                    .addComponent(pEquipos, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ckLunesPrimeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckLunesPrimeraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckLunesPrimeraActionPerformed

    private void btnAceptarEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarEquiposActionPerformed
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/aceptar.png"));
        int input = JOptionPane.showConfirmDialog(null, "¿Desea aplicar las restricciones para el equipo seleccionado?", "Aplicar restricciones",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {
            cbEquipos.setEnabled(true);
            if(this.ckCongelarEquipo.isSelected()){
                try {
                    ge.congelarEquipo(this.cbEquipos.getSelectedIndex(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{  
                try {
                    ge.congelarEquipo(this.cbEquipos.getSelectedIndex(), false);
                } catch (SQLException ex) {
                    Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.restriccionesDeEquipo();
            if(correcto){
                this.disableEquipos();
            }
        } else {
            cbEquipos.setEnabled(true);
            this.disableEquipos();
            btnAceptarEquipos.setEnabled(false);
            cbEquipos.setSelectedItem("Selecciona un equipo");
        
        }
    }//GEN-LAST:event_btnAceptarEquiposActionPerformed

    private void btnEditarEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEquiposActionPerformed
        if (cbEquipos.getSelectedItem().toString().equals("Selecciona un equipo")){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un equipo","Seleccione un equipo", JOptionPane.QUESTION_MESSAGE, icon);
        }else {
            horasEquipossEnabled ();
            cbEquipos.setEnabled(false);
            this.enableEquipos();
            //cbNoCoincidir1.addItem("");
            //md.llenarComboArbitros(this.cbNoCoincidir1);
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
        this.cbNoCoincidir1.removeAllItems();
        this.cbNoCoincidir1.addItem("");
        md.llenarComboArbitros(this.cbNoCoincidir1);
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

    private void cbLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLunesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLunesActionPerformed

    private void cbArbitrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbArbitrosItemStateChanged
        ga.mostrarDatos();
    }//GEN-LAST:event_cbArbitrosItemStateChanged

    private void btnAceptarArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarArbitroActionPerformed
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/aceptar.png"));
        int input = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios para el árbitro seleccionado?", "Gestión de Árbitros",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {
            cbArbitros.setEnabled(true);
            if(this.ckCongelarArbitro.isSelected()){
                try {
                    ga.congelarArbitro(this.cbArbitros.getSelectedIndex(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    ga.congelarArbitro(this.cbArbitros.getSelectedIndex(), false);
                } catch (SQLException ex) {
                    Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ga.gestionarArbitro();
            this.disableArbitros();
            btnAceptarArbitro.setEnabled(false);
            cbArbitros.setSelectedItem("Selecciona un árbitro");
            cmbHorasAsignar.setSelectedIndex(-1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            ga.contarHorarios(lblNumeroArbitrosActivos);
        }else{
            this.disableArbitros();
            btnAceptarArbitro.setEnabled(false);
            cbArbitros.setSelectedItem("Selecciona un árbitro");
            cbArbitros.setEnabled(true);
        }
    }//GEN-LAST:event_btnAceptarArbitroActionPerformed

    private void btnEditarArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarArbitroActionPerformed
        if (cbArbitros.getSelectedItem().toString().equals("Selecciona un árbitro")){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un árbitro","Seleccione un árbitro", JOptionPane.QUESTION_MESSAGE, icon);
        }else {
            cbArbitros.setEnabled(false);
            this.enableHorasAsignar();
            //this.enableArbitros();
        }
    }//GEN-LAST:event_btnEditarArbitroActionPerformed

    private void ckCongelarCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckCongelarCampoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckCongelarCampoActionPerformed

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

    private void cbCamposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCamposItemStateChanged
        gc.mostrarDatos();
    }//GEN-LAST:event_cbCamposItemStateChanged

    private void btnEditarCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCampoActionPerformed
        if (cbCampos.getSelectedItem().toString().equals("Selecciona un campo")){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un campo","Seleccione un campo", JOptionPane.QUESTION_MESSAGE, icon);
        }else {
            horasCamposEnabled ();
            cbCampos.setEnabled(false);
            this.enableCampos();
        }
    }//GEN-LAST:event_btnEditarCampoActionPerformed

    private void btnAceptarCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarCampoActionPerformed
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/aceptar.png"));
        int input = JOptionPane.showConfirmDialog(null, "¿Desea guardar los horarios para el campo seleccionado?", "Gestión de campos",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {
            cbCampos.setEnabled(true);
            if(this.ckCongelarCampo.isSelected()){
                try {
                    gc.congelarCampo(this.cbCampos.getSelectedItem().toString(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    gc.congelarCampo(this.cbCampos.getSelectedItem().toString(), false);
                } catch (SQLException ex) {
                    Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            this.restriccionesDeCampo();
            this.disableCampos();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            gc.contarHorarios(lblNumeroCamposActivos);
        }else{
            this.disableCampos();
            btnAceptarCampo.setEnabled(false);
            cbCampos.setSelectedItem("Selecciona un campo");
            cbCampos.setEnabled(true);
        }
    }//GEN-LAST:event_btnAceptarCampoActionPerformed

    private void cbCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCamposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCamposActionPerformed

    private void cbNoCoincidir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNoCoincidir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNoCoincidir1ActionPerformed

    private void cmbHorasAsignarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHorasAsignarItemStateChanged
        if(this.cmbHorasAsignar.getSelectedItem().equals("0")){
            this.disableArbitros();
            this.ckLunesArbitro.setSelected(false);
            this.ckMartesArbitro.setSelected(false);
            this.ckMiercolesArbitro.setSelected(false);
            this.ckJuevesArbitro.setSelected(false);
            this.ckViernesArbitro.setSelected(false);
            this.ckSabadoArbitro.setSelected(false);
            this.ckDomingoArbitro.setSelected(false);
            btnAceptarArbitro.setEnabled(true);
        }else if(this.cmbHorasAsignar.getSelectedItem().equals("14")){
            this.ckLunesArbitro.setSelected(true);
            this.ckMartesArbitro.setSelected(true);
            this.ckMiercolesArbitro.setSelected(true);
            this.ckJuevesArbitro.setSelected(true);
            this.ckViernesArbitro.setSelected(true);
            this.ckSabadoArbitro.setSelected(true);
            this.ckDomingoArbitro.setSelected(true);
        }else{
            this.enableArbitros();   
        }
    }//GEN-LAST:event_cmbHorasAsignarItemStateChanged

    private void cmbHorasAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHorasAsignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHorasAsignarActionPerformed

    private void ckLunesArbitroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckLunesArbitroItemStateChanged
        if(this.cmbHorasAsignar.getSelectedIndex() > 0){
            int horas = Integer.parseInt(cmbHorasAsignar.getSelectedItem().toString());
            switch(horas){
                case 2:
                    if(this.ckLunesArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 4:
                    if(this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() ||
                        this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                        this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                        this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                        this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                        this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 6:
                    if(this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 8:
                    if(this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 10:
                    if(this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 12:
                    if(this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
            }
            this.btnEditarArbitro.setEnabled(false);
            this.btnAceptarArbitro.setEnabled(true);      
        }
    }//GEN-LAST:event_ckLunesArbitroItemStateChanged

    private void ckMartesArbitroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckMartesArbitroItemStateChanged
        if(this.cmbHorasAsignar.getSelectedIndex() > 0){
            int horas = Integer.parseInt(cmbHorasAsignar.getSelectedItem().toString());
            switch(horas){
                case 2:
                    if(this.ckMartesArbitro.isSelected()){
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 4:
                    if(this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                        this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                        this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                        this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                        this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                        this.ckMartesArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 6:
                    if(this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 8:
                    if(this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 10:
                    if(this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 12:
                    if(this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
            }
            this.btnEditarArbitro.setEnabled(false);
            this.btnAceptarArbitro.setEnabled(true);
                 
        }
    }//GEN-LAST:event_ckMartesArbitroItemStateChanged

    private void ckMiercolesArbitroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckMiercolesArbitroItemStateChanged
        if(this.cmbHorasAsignar.getSelectedIndex() > 0){
            int horas = Integer.parseInt(cmbHorasAsignar.getSelectedItem().toString());
            switch(horas){
                case 2:
                    if(this.ckMiercolesArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 4:
                    if(this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() ||
                        this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                        this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                        this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                        this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                        this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 6:
                    if(this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 8:
                    if(this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 10:
                    if(this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 12:
                    if(this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
            }
            this.btnEditarArbitro.setEnabled(false);
            this.btnAceptarArbitro.setEnabled(true);     
        }
    }//GEN-LAST:event_ckMiercolesArbitroItemStateChanged

    private void ckJuevesArbitroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckJuevesArbitroItemStateChanged
        if(this.cmbHorasAsignar.getSelectedIndex() > 0){
            int horas = Integer.parseInt(cmbHorasAsignar.getSelectedItem().toString());
            switch(horas){
                case 2:
                    if(this.ckJuevesArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 4:
                    if(this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() ||
                        this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                        this.ckJuevesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                        this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                        this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                        this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 6:
                    if(this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 8:
                    if(this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 10:
                    if(this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 12:
                    if(this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
            }
            this.btnEditarArbitro.setEnabled(false);
            this.btnAceptarArbitro.setEnabled(true);     
        }
    }//GEN-LAST:event_ckJuevesArbitroItemStateChanged

    private void ckViernesArbitroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckViernesArbitroItemStateChanged
        if(this.cmbHorasAsignar.getSelectedIndex() > 0){
            int horas = Integer.parseInt(cmbHorasAsignar.getSelectedItem().toString());
            switch(horas){
                case 2:
                    if(this.ckViernesArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 4:
                    if(this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() ||
                        this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                        this.ckViernesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                        this.ckViernesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                        this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                        this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 6:
                    if(this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 8:
                    if(this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 10:
                    if(this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 12:
                    if(this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckViernesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
            }
            this.btnEditarArbitro.setEnabled(false);
            this.btnAceptarArbitro.setEnabled(true);      
        }
    }//GEN-LAST:event_ckViernesArbitroItemStateChanged

    private void ckSabadoArbitroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckSabadoArbitroItemStateChanged
        if(this.cmbHorasAsignar.getSelectedIndex() > 0){
            int horas = Integer.parseInt(cmbHorasAsignar.getSelectedItem().toString());
            switch(horas){
                case 2:
                    if(this.ckSabadoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 4:
                    if(this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() ||
                        this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                        this.ckSabadoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                        this.ckSabadoArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                        this.ckSabadoArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                        this.ckSabadoArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 6:
                    if(this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 8:
                    if(this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 10:
                    if(this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
                case 12:
                    if(this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected() ||
                       this.ckSabadoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckDomingoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckDomingoArbitro.setEnabled(true);
                    }
                    break;
            }
            this.btnEditarArbitro.setEnabled(false);
            this.btnAceptarArbitro.setEnabled(true);
        }
    }//GEN-LAST:event_ckSabadoArbitroItemStateChanged

    private void ckDomingoArbitroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckDomingoArbitroItemStateChanged
        if(this.cmbHorasAsignar.getSelectedIndex() > 0){
            int horas = Integer.parseInt(cmbHorasAsignar.getSelectedItem().toString());
            switch(horas){
                case 2:
                    if(this.ckDomingoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                    }
                    break;
                case 4:
                    if(this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() ||
                        this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                        this.ckDomingoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                        this.ckDomingoArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                        this.ckDomingoArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                        this.ckDomingoArbitro.isSelected() && this.ckSabadoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                    }
                    break;
                case 6:
                    if(this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                    }
                    break;
                case 8:
                    if(this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                    }
                    break;
                case 10:
                    if(this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected()) {
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                    }
                    break;
                case 12:
                    if(this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMartesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected() ||
                       this.ckDomingoArbitro.isSelected() && this.ckMiercolesArbitro.isSelected() && this.ckJuevesArbitro.isSelected() && this.ckLunesArbitro.isSelected() && this.ckViernesArbitro.isSelected() && this.ckSabadoArbitro.isSelected()){
                        this.ckMartesArbitro.setEnabled(false);
                        this.ckMiercolesArbitro.setEnabled(false);
                        this.ckJuevesArbitro.setEnabled(false);
                        this.ckLunesArbitro.setEnabled(false);
                        this.ckViernesArbitro.setEnabled(false);
                        this.ckSabadoArbitro.setEnabled(false);
                    }else{
                        this.ckMartesArbitro.setEnabled(true);
                        this.ckMiercolesArbitro.setEnabled(true);
                        this.ckJuevesArbitro.setEnabled(true);
                        this.ckLunesArbitro.setEnabled(true);
                        this.ckViernesArbitro.setEnabled(true);
                        this.ckSabadoArbitro.setEnabled(true);
                    }
                    break;
            }
            this.btnEditarArbitro.setEnabled(false);
            this.btnAceptarArbitro.setEnabled(true);
        }
    }//GEN-LAST:event_ckDomingoArbitroItemStateChanged

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
    private javax.swing.JLabel blArbitro;
    private javax.swing.JButton btnAceptarArbitro;
    private javax.swing.JButton btnAceptarCampo;
    private javax.swing.JButton btnAceptarEquipos;
    private javax.swing.JButton btnEditarArbitro;
    private javax.swing.JButton btnEditarCampo;
    private javax.swing.JButton btnEditarEquipos;
    public javax.swing.JComboBox<String> cbArbitros;
    public javax.swing.JComboBox<String> cbCampos;
    public javax.swing.JComboBox<String> cbDomingo;
    public javax.swing.JComboBox<String> cbEquipos;
    public javax.swing.JComboBox<String> cbJueves;
    public javax.swing.JComboBox<String> cbLunes;
    public javax.swing.JComboBox<String> cbMartes;
    public javax.swing.JComboBox<String> cbMiercoles;
    public javax.swing.JComboBox<String> cbNoCoincidir;
    public javax.swing.JComboBox<String> cbNoCoincidir1;
    public javax.swing.JComboBox<String> cbNoCoincidirArbitro;
    public javax.swing.JComboBox<String> cbSabado;
    public javax.swing.JComboBox<String> cbViernes;
    public javax.swing.JCheckBox ckCongelarArbitro;
    public javax.swing.JCheckBox ckCongelarCampo;
    public javax.swing.JCheckBox ckCongelarEquipo;
    public javax.swing.JCheckBox ckDomingoArbitro;
    public javax.swing.JCheckBox ckDomingoCampos;
    public javax.swing.JCheckBox ckDomingoEquipos;
    public javax.swing.JCheckBox ckDomingoPrimera;
    public javax.swing.JCheckBox ckDomingoSegunda;
    public javax.swing.JCheckBox ckJuevesArbitro;
    public javax.swing.JCheckBox ckJuevesCampos;
    public javax.swing.JCheckBox ckJuevesEquipos;
    public javax.swing.JCheckBox ckJuevesPrimera;
    public javax.swing.JCheckBox ckJuevesSegunda;
    public javax.swing.JCheckBox ckLunesArbitro;
    public javax.swing.JCheckBox ckLunesCampos;
    public javax.swing.JCheckBox ckLunesEquipos;
    public javax.swing.JCheckBox ckLunesPrimera;
    public javax.swing.JCheckBox ckLunesSegunda;
    public javax.swing.JCheckBox ckMartesArbitro;
    public javax.swing.JCheckBox ckMartesCampos;
    public javax.swing.JCheckBox ckMartesEquipos;
    public javax.swing.JCheckBox ckMartesPrimera;
    public javax.swing.JCheckBox ckMartesSegunda;
    public javax.swing.JCheckBox ckMiercolesArbitro;
    public javax.swing.JCheckBox ckMiercolesCampos;
    public javax.swing.JCheckBox ckMiercolesEquipos;
    public javax.swing.JCheckBox ckMiercolesPrimera;
    public javax.swing.JCheckBox ckMiercolesSegunda;
    public javax.swing.JCheckBox ckSabadoArbitro;
    public javax.swing.JCheckBox ckSabadoCampos;
    public javax.swing.JCheckBox ckSabadoEquipos;
    public javax.swing.JCheckBox ckSabadoPrimera;
    public javax.swing.JCheckBox ckSabadoSegunda;
    public javax.swing.JCheckBox ckViernesArbitro;
    public javax.swing.JCheckBox ckViernesCampos;
    public javax.swing.JCheckBox ckViernesEquipos;
    public javax.swing.JCheckBox ckViernesPrimera;
    public javax.swing.JCheckBox ckViernesSegunda;
    private javax.swing.JComboBox<String> cmbHorasAsignar;
    private javax.swing.JLabel lblCampos;
    private javax.swing.JLabel lblCamposActivos;
    private javax.swing.JLabel lblCamposActivos1;
    private javax.swing.JLabel lblCamposExcluidos;
    private javax.swing.JLabel lblDiasEquipos;
    private javax.swing.JLabel lblEquipos;
    private javax.swing.JLabel lblHorasASeleccionar;
    private javax.swing.JLabel lblLogoArbitros1;
    private javax.swing.JLabel lblLogoCampos;
    private javax.swing.JLabel lblLogoEquipos;
    private javax.swing.JLabel lblNoCoincidir;
    private javax.swing.JLabel lblNoCoincidir1;
    private javax.swing.JLabel lblNoCoincidir2;
    private javax.swing.JLabel lblNumeroArbitrosActivos;
    private javax.swing.JLabel lblNumeroCamposActivos;
    private javax.swing.JMenuBar mbRestricciones;
    private javax.swing.JPanel pCampos;
    private javax.swing.JPanel pCamposExcluidos;
    private javax.swing.JPanel pEquipos;
    private javax.swing.JSeparator separadorCampos1;
    private javax.swing.JSeparator separadorCampos10;
    private javax.swing.JSeparator separadorCampos11;
    private javax.swing.JSeparator separadorCampos12;
    private javax.swing.JSeparator separadorCampos13;
    private javax.swing.JSeparator separadorCampos2;
    private javax.swing.JSeparator separadorCampos3;
    private javax.swing.JSeparator separadorCampos4;
    private javax.swing.JSeparator separadorCampos5;
    private javax.swing.JSeparator separadorCampos6;
    private javax.swing.JSeparator separadorCampos8;
    private javax.swing.JSeparator separadorEquipos1;
    private javax.swing.JSeparator separadorEquipos2;
    private javax.swing.JSeparator separadorEquipos4;
    private javax.swing.JSeparator separadorEquipos5;
    private javax.swing.JSeparator separadorEquipos6;
    private javax.swing.JSeparator separadorEquipos7;
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
