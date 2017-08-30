package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import views.Restricciones;

/**
 *
 * @author santy
 */
public class GestionarCampos {
    private final Conn conn = new Conn();
    private Restricciones r = null;
    
    public GestionarCampos(Restricciones rect){
        this.r = rect;
    }
    
    public GestionarCampos(){
        
    }
    
    public void guardarCampo(String campo) throws SQLException{
            conn.conectar();
            conn.insertData("campos", "null,'" + campo + "', 1");
            conn.getConection().commit();
            conn.desconectar();
    }
    
    public void editarCampo(String anterior, String nuevo) throws SQLException{
            conn.conectar();
            conn.updateData("campos", "CAMPO = '" + nuevo + "'", "CAMPO LIKE '" + anterior+ "'");
            conn.getConection().commit();
            conn.desconectar();
    }
    
    public void eliminarCampo(String campo) throws SQLException{
            conn.conectar();
            conn.deleteData("campos", "CAMPO like '" + campo + "'");
            conn.getConection().commit();
            conn.desconectar();
    }
    
    public void vaciarCampo() throws SQLException{
            conn.conectar();
            conn.deleteTable("campos");
            conn.getConection().commit();
            conn.desconectar();
    }
    
    public void gestionarCampo(ArrayList<OCampos> condiciones){
        try {
            int id = r.cbCampos.getSelectedIndex();
            conn.conectar();
            conn.deleteData("cam_horarios", "ID_CAMPO = " + id);
            conn.getConection().commit();
            
            for(int i = 0; i < condiciones.size(); i++){
                conn.insertData("cam_horarios", "NULL," + id +","+condiciones.get(i).getDia()+","+condiciones.get(i).getHora()+", 0");
                conn.getConection().commit();
            }
            
            conn.desconectar();
        } catch (SQLException ex) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al guardar los datos\n"+ex, "Error", JOptionPane.QUESTION_MESSAGE, icon);

        }finally{
            r.cbCampos.setSelectedIndex(0);
        }
    }
    
    public void congelarCampo(String campo, boolean estado) throws SQLException{
            conn.conectar();
            conn.updateData("campos", "CONGELADO = " + estado, "CAMPO like '" + campo + "'");
            conn.getConection().commit();
            conn.desconectar();
    }
    
    public void contarHorarios(JLabel lblTotal){
        conn.conectar();
        int total = conn.totalRegistros("cam_horarios ch INNER JOIN campos c ON ch.ID_CAMPO = c.ID", "c.CONGELADO = 0");
        lblTotal.setText(String.valueOf(total));
        conn.desconectar();
    }
    
    public void mostrarDatos(){
        try {
            limpiarSeleccion();
            int idCampo = r.cbCampos.getSelectedIndex();
            conn.conectar();
            ResultSet cam = conn.getValues("ch.*, d.dia, h.hora, h.horario",
                    "cam_horarios ch LEFT JOIN dias d ON ch.ID_DIA = d.ID LEFT JOIN hora h ON ch.ID_HORA = h.ID",
                    "ch.ID_CAMPO = "+idCampo, "");
            while(cam.next()){
                if(cam.getString("dia") != null){
                    switch (cam.getString("dia")){
                        case "Lunes":
                            r.ckLunesCampos.setSelected(true);
                            if(cam.getString("horario").equalsIgnoreCase("primera")){
                                r.ckLunesPrimera.setSelected(true);
                                r.txtLunesPrimera.setText(cam.getString("hora"));
                            }
                            if(cam.getString("horario").equalsIgnoreCase("segunda")){
                                r.ckLunesSegunda.setSelected(true);
                                r.txtLunesSegunda.setText(cam.getString("hora"));
                            }
                            break;
                        case "Martes":
                            r.ckMartesCampos.setSelected(true);
                            if(cam.getString("horario").equalsIgnoreCase("primera")){
                                r.ckMartesPrimera.setSelected(true);
                                r.txtMartesPrimera.setText(cam.getString("hora"));
                            }
                            if(cam.getString("horario").equalsIgnoreCase("segunda")){
                                r.ckMartesSegunda.setSelected(true);
                                r.txtMartesSegunda.setText(cam.getString("hora"));
                            }
                            break;
                        case "Miércoles":
                            r.ckMiercolesCampos.setSelected(true);
                            if(cam.getString("horario").equalsIgnoreCase("primera")){
                                r.ckMiercolesPrimera.setSelected(true);
                                r.txtMiercolesPrimera.setText(cam.getString("hora"));
                            }
                            if(cam.getString("horario").equalsIgnoreCase("segunda")){
                                r.ckMiercolesSegunda.setSelected(true);
                                r.txtMiercolesSegunda.setText(cam.getString("hora"));
                            }
                            break;
                        case "Jueves":
                            r.ckJuevesCampos.setSelected(true);
                            if(cam.getString("horario").equalsIgnoreCase("primera")){
                                r.ckJuevesPrimera.setSelected(true);
                                r.txtJuevesPrimera.setText(cam.getString("hora"));
                            }
                            if(cam.getString("horario").equalsIgnoreCase("segunda")){
                                r.ckJuevesSegunda.setSelected(true);
                                r.txtJuevesSegunda.setText(cam.getString("hora"));
                            }
                            break;
                        case "Viernes":
                            r.ckViernesCampos.setSelected(true);
                            if(cam.getString("horario").equalsIgnoreCase("primera")){
                                r.ckViernesPrimera.setSelected(true);
                                r.txtViernesPrimera.setText(cam.getString("hora"));
                            }
                            if(cam.getString("horario").equalsIgnoreCase("segunda")){
                                r.ckViernesSegunda.setSelected(true);
                                r.txtViernesSegunda.setText(cam.getString("hora"));
                            }
                            break;
                        case "Sábado":
                            r.ckSabadoCampos.setSelected(true);
                            if(cam.getString("horario").equalsIgnoreCase("primera")){
                                r.ckSabadoPrimera.setSelected(true);
                                r.txtSabadoPrimera.setText(cam.getString("hora"));
                            }
                            if(cam.getString("horario").equalsIgnoreCase("segunda")){
                                r.ckSabadoSegunda.setSelected(true);
                                r.txtSabadoSegunda.setText(cam.getString("hora"));
                            }
                            break;
                        case "Domingo":
                            r.ckDomingoCampos.setSelected(true);
                            if(cam.getString("horario").equalsIgnoreCase("primera")){
                                r.ckDomingoPrimera.setSelected(true);
                                r.txtDomingoPrimera.setText(cam.getString("hora"));
                            }
                            if(cam.getString("horario").equalsIgnoreCase("segunda")){
                                r.ckDomingoSegunda.setSelected(true);
                                r.txtDomingoSegunda.setText(cam.getString("hora"));
                            }
                            break;
                    }
                }
                
            }
            cam.close();
            String campo = (String) r.cbCampos.getSelectedItem();
            ResultSet frozen = conn.getValues("CONGELADO", "campos", "CAMPO like '" + campo +"'", "");
            while (frozen.next()){
                if(frozen.getInt("CONGELADO")==1){
                    r.ckCongelarCampo.setSelected(true);
                }
            }
            frozen.close();
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionarCampos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarCamposHabiles(JTable tabla){
        DefaultTableModel model = (DefaultTableModel)tabla.getModel();
        try {
            Object[] fila = new Object[4];
            conn.conectar();
            ResultSet camposHabiles = conn.getValues("c.ID, c.CAMPO, d.dia, h.hora", "campos c inner join cam_horarios ch on c.ID = ch.ID_CAMPO INNER JOIN dias d ON ch.ID_DIA = d.ID INNER JOIN hora h ON ch.ID_HORA = h.ID", "c.CONGELADO = 0 and ch.ASIGNADO = 0", "c.ID");
            while (camposHabiles.next()){
                fila[0] = camposHabiles.getInt("ID");
                fila[1] = camposHabiles.getString("CAMPO");
                fila[2] = camposHabiles.getString("dia");
                fila[3] = camposHabiles.getString("hora");
                
                model.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionarCampos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conn.desconectar();
        }
        tabla.setModel(model);
    }
    
    private void limpiarSeleccion(){
        r.disableCampos();               
        r.txtLunesPrimera.setText("");
        r.txtLunesSegunda.setText("");
        r.txtMartesPrimera.setText("");
        r.txtMartesSegunda.setText("");
        r.txtMiercolesPrimera.setText("");
        r.txtMiercolesSegunda.setText("");
        r.txtJuevesPrimera.setText("");
        r.txtJuevesSegunda.setText("");
        r.txtViernesPrimera.setText("");
        r.txtViernesSegunda.setText("");
        r.txtSabadoPrimera.setText("");
        r.txtSabadoSegunda.setText("");
        r.txtDomingoPrimera.setText("");
        r.txtDomingoSegunda.setText("");
        r.ckLunesCampos.setSelected(false);
        r.ckMartesCampos.setSelected(false);
        r.ckMiercolesCampos.setSelected(false);
        r.ckJuevesCampos.setSelected(false);
        r.ckViernesCampos.setSelected(false);
        r.ckSabadoCampos.setSelected(false);
        r.ckDomingoCampos.setSelected(false);         
        r.ckLunesPrimera.setSelected(false);
        r.ckLunesSegunda.setSelected(false);
        r.ckMartesPrimera.setSelected(false);
        r.ckMartesSegunda.setSelected(false);                
        r.ckMiercolesPrimera.setSelected(false);
        r.ckMiercolesSegunda.setSelected(false);
        r.ckJuevesPrimera.setSelected(false);
        r.ckJuevesSegunda.setSelected(false); 
        r.ckViernesPrimera.setSelected(false);
        r.ckViernesSegunda.setSelected(false);
        r.ckSabadoPrimera.setSelected(false);
        r.ckSabadoSegunda.setSelected(false);
        r.ckDomingoPrimera.setSelected(false);
        r.ckDomingoSegunda.setSelected(false);
        r.ckCongelarCampo.setSelected(false);
    }
}