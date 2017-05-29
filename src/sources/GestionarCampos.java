package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import views.Restricciones;

/**
 *
 * @author santy
 */
public class GestionarCampos {
    private final Conn conn = new Conn();
    private final Restricciones r;
    
    public GestionarCampos(Restricciones rect){
        this.r = rect;
    }
    
    public void guardarCampo(String campo){
        try {
            conn.conectar();
            conn.insertData("campos", campo);
            conn.getConection().commit();
            conn.desconectar();
            JOptionPane.showMessageDialog(null, "Campo guardado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void gestionarCampo(ArrayList<OCampos> condiciones){
        try {
            int id = r.cbCampos.getSelectedIndex();
            conn.conectar();
            conn.deleteData("cam_horarios", "ID_CAMPO = " + id);
            conn.getConection().commit();
            
            for(int i = 0; i < condiciones.size(); i++){
                conn.insertData("cam_horarios", id +","+condiciones.get(i).getDia()+","+condiciones.get(i).getHora());
                conn.getConection().commit();
            }
            
            conn.desconectar();
            r.cbCampos.setSelectedIndex(0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos\n"+ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    public void mostrarDatos(){
        try {
            limpiarSeleccion();
            int idCampo = r.cbCampos.getSelectedIndex();
            conn.conectar();
            ResultSet cam = conn.getValues("ch.*, d.dia, h.hora, h.horario",
                    "cam_horarios ch INNER JOIN dias d ON ch.ID_DIA = d.ID INNER JOIN hora h ON ch.ID_HORA = h.ID",
                    "ch.ID_CAMPO = "+idCampo, "");
            while(cam.next()){
                switch (cam.getString("dia")){
                    case "Lunes":
                        r.ckLunesCampos.setSelected(true);
                        if(cam.getString("horario").equalsIgnoreCase("primera")){
                            r.ckLunesPrimera.setSelected(true);
                            r.txtLunesPrimera.setText(cam.getString("hora"));
                        }else if(cam.getString("horario").equalsIgnoreCase("segunda")){
                            r.ckLunesSegunda.setSelected(true);
                            r.txtLunesSegunda.setText(cam.getString("hora"));
                        }
                        break;
                    case "Martes":
                        r.ckMartesCampos.setSelected(true);
                        if(cam.getString("horario").equalsIgnoreCase("primera")){
                            r.ckMartesPrimera.setSelected(true);
                            r.txtMartesPrimera.setText(cam.getString("hora"));
                        }else if(cam.getString("horario").equalsIgnoreCase("segunda")){
                            r.ckMartesSegunda.setSelected(true);
                            r.txtMartesSegunda.setText(cam.getString("hora"));
                        }
                        break;
                    case "Miércoles":
                        r.ckMiercolesCampos.setSelected(true);
                        if(cam.getString("horario").equalsIgnoreCase("primera")){
                            r.ckMiercolesPrimera.setSelected(true);
                            r.txtMiercolesPrimera.setText(cam.getString("hora"));
                        }else if(cam.getString("horario").equalsIgnoreCase("segunda")){
                            r.ckMiercolesSegunda.setSelected(true);
                            r.txtMiercolesSegunda.setText(cam.getString("hora"));
                        }
                        break;
                    case "Jueves":
                        r.ckJuevesCampos.setSelected(true);
                        if(cam.getString("horario").equalsIgnoreCase("primera")){
                            r.ckJuevesPrimera.setSelected(true);
                            r.txtJuevesPrimera.setText(cam.getString("hora"));
                        }else if(cam.getString("horario").equalsIgnoreCase("segunda")){
                            r.ckJuevesSegunda.setSelected(true);
                            r.txtJuevesSegunda.setText(cam.getString("hora"));
                        }
                        break;
                    case "Viernes":
                        r.ckViernesCampos.setSelected(true);
                        if(cam.getString("horario").equalsIgnoreCase("primera")){
                            r.ckViernesPrimera.setSelected(true);
                            r.txtViernesPrimera.setText(cam.getString("hora"));
                        }else if(cam.getString("horario").equalsIgnoreCase("segunda")){
                            r.ckViernesSegunda.setSelected(true);
                            r.txtViernesSegunda.setText(cam.getString("hora"));
                        }
                        break;
                    case "Sábado":
                        r.ckSabadoCampos.setSelected(true);
                        if(cam.getString("horario").equalsIgnoreCase("primera")){
                            r.ckSabadoPrimera.setSelected(true);
                            r.txtSabadoPrimera.setText(cam.getString("hora"));
                        }else if(cam.getString("horario").equalsIgnoreCase("segunda")){
                            r.ckSabadoSegunda.setSelected(true);
                            r.txtSabadoSegunda.setText(cam.getString("hora"));
                        }
                        break;
                    case "Domingo":
                        r.ckDomingoCampos.setSelected(true);
                        if(cam.getString("horario").equalsIgnoreCase("primera")){
                            r.ckDomingoPrimera.setSelected(true);
                            r.txtDomingoPrimera.setText(cam.getString("hora"));
                        }else if(cam.getString("horario").equalsIgnoreCase("segunda")){
                            r.ckDomingoSegunda.setSelected(true);
                            r.txtDomingoSegunda.setText(cam.getString("hora"));
                        }
                        break;
                }
            }
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionarCampos.class.getName()).log(Level.SEVERE, null, ex);
        }
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