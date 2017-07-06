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
public class GestionarEquipos {
    private final Conn conn = new Conn();
    private final Restricciones r;
    
    public GestionarEquipos(Restricciones rest){
        this.r = rest;
    }
    
    public void gestionarEquipo(ArrayList<ORestriccion> condiciones){
        try {
            int id = r.cbEquipos.getSelectedIndex();
            conn.conectar();
            conn.deleteData("restricciones", "ID_EQUIPO = " + id);
            conn.getConection().commit();
            
            for(int i = 0; i < condiciones.size(); i++){
                conn.insertData("restricciones", "null,"+id +","+condiciones.get(i).getDia()+","+
                                 condiciones.get(i).getHora()+","+condiciones.get(i).getCampo()+","+
                                 condiciones.get(i).getNoCoincidir());
                conn.getConection().commit();
            }
            
            conn.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos\n"+ex, "Error", JOptionPane.ERROR_MESSAGE);
        }finally{
            r.cbEquipos.setSelectedIndex(0);
        }
    }
    
        public void guardarEquipo(String equipo){
        try {
            conn.conectar();
            conn.insertData("equipos", "null,'" + equipo + "', 1, 1, 1");
            conn.getConection().commit();
            conn.desconectar();
            JOptionPane.showMessageDialog(null, "Equipo guardado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public void vaciarEquipo(){
        try{
            conn.conectar();
            conn.deleteTable("equipos");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al vaciar los datos\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void congelarEquipo(int idEquipo, boolean estado){
        try {
            conn.conectar();
            conn.updateData("equipos", "congelado = " + estado, "ID = " + idEquipo);
            conn.getConection().commit();
            conn.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos\n"+ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrarRestricciones(){
        try {
            limpiarCampos();
            int idEquipo = r.cbEquipos.getSelectedIndex();
            conn.conectar();
            ResultSet equi = conn.getValues("r.*, d.dia, e.nombre",
                    "restricciones r LEFT JOIN dias d ON r.ID_DIA = d.ID LEFT JOIN equipos e ON r.ID_COINCIDE = e.ID",
                    "r.ID_EQUIPO = " + idEquipo, "");
            while(equi.next()){
                if(equi.getString("dia") != null){
                    switch (equi.getString("dia")){
                        case "Lunes":
                            r.ckLunesEquipos.setSelected(true);
                            r.cbLunes.setSelectedIndex(equi.getInt("HORA"));
                            break;
                        case "Martes":
                            r.ckMartesEquipos.setSelected(true);
                            r.cbMartes.setSelectedIndex(equi.getInt("HORA"));
                            break;
                        case "Miércoles":
                            r.ckMiercolesEquipos.setSelected(true);
                            r.cbMiercoles.setSelectedIndex(equi.getInt("HORA"));
                            break;
                        case "Jueves":
                            r.ckJuevesEquipos.setSelected(true);
                            r.cbJueves.setSelectedIndex(equi.getInt("HORA"));
                            break;
                        case "Viernes":
                            r.ckViernesEquipos.setSelected(true);
                            r.cbViernes.setSelectedIndex(equi.getInt("HORA"));
                            break;
                        case "Sábado":
                            r.ckSabadoEquipos.setSelected(true);
                            r.cbSabado.setSelectedIndex(equi.getInt("HORA"));
                            break;
                        case "Domingo":
                            r.ckDomingoEquipos.setSelected(true);
                            r.cbDomingo.setSelectedIndex(equi.getInt("HORA"));
                            break;
                    }
                }
                if(equi.getObject("ID_CAMPO") != null){
                    for(int i = 0; i < r.total+1; i++){
                        if(i == equi.getInt("ID_CAMPO")){
                            r.ckCampos[i].setSelected(true);
                        }
                    }
                }
                if(equi.getObject("ID_COINCIDE") != null){
                    r.cbNoCoincidir.setSelectedItem(equi.getString("nombre"));
                } 
            }
            
            ResultSet eCongelado = conn.getValues("CONGELADO", "equipos", "ID = " + idEquipo, "");
            while(eCongelado.next()){
                if(eCongelado.getBoolean("CONGELADO")){
                    r.ckCongelarEquipo.setSelected(true);
                }else{
                    r.ckCongelarEquipo.setSelected(false);
                }
            }
            
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionarEquipos.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    private void limpiarCampos(){
        r.ckLunesEquipos.setSelected(false);
        r.ckMartesEquipos.setSelected(false);
        r.ckMiercolesEquipos.setSelected(false);
        r.ckJuevesEquipos.setSelected(false);
        r.ckViernesEquipos.setSelected(false);
        r.ckSabadoEquipos.setSelected(false);
        r.ckDomingoEquipos.setSelected(false);
        r.ckCongelarEquipo.setSelected(false);
        r.cbLunes.setSelectedIndex(0);
        r.cbMartes.setSelectedIndex(0);
        r.cbMiercoles.setSelectedIndex(0);
        r.cbJueves.setSelectedIndex(0);
        r.cbViernes.setSelectedIndex(0);
        r.cbSabado.setSelectedIndex(0);
        r.cbDomingo.setSelectedIndex(0);
        r.cbNoCoincidir.setSelectedIndex(0);
        for(int i = 1; i < r.total+1; i++){
            r.ckCampos[i].setSelected(false);
        }
        
    }
}
