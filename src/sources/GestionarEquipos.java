package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
    
    public GestionarEquipos(){
        r = null;
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
                
                if(condiciones.get(i).getNoCoincidir() != null){
                    conn.insertData("restricciones", "null,"+condiciones.get(i).getNoCoincidir()+",null, null, null,"+id);
                    conn.getConection().commit();
                }
            }
            
            conn.desconectar();
        } catch (SQLException ex) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al guardar los datos\n"+ex, "Error", JOptionPane.QUESTION_MESSAGE, icon);
        }finally{
            r.cbEquipos.setSelectedIndex(0);
        }
    }
    
    public void guardarEquipo( String equipo, String scategoria, String sdivision ) throws SQLException{
        
            conn.conectar();
             
            ResultSet rdivision = conn.getValues("ID", "division", "DIVISION = '" + sdivision + "'", "");
            try {
                while(rdivision.next()){
                   sdivision=(rdivision.getString("ID")); 
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            ResultSet rcategoria = conn.getValues("ID", "competicion", "COMPETICION = '" + scategoria + "'", "");
            try {
                while(rcategoria.next()){
                   scategoria=(rcategoria.getString("ID")); 
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }           

            int categoria = Integer.parseInt(scategoria);    
            int division = Integer.parseInt(sdivision);

            conn.insertData("equipos", "null,'" + equipo + "', '" + categoria + "','" + division + "', 1");
            conn.getConection().commit();
            conn.desconectar();      
    }
    
    public void editarEquipo(String anterior, String nuevo, String scategoria, String sdivision) throws SQLException{

            conn.conectar();
            ResultSet rdivision = conn.getValues("ID", "division", "DIVISION = '" + sdivision + "'", "");
            try {
                while(rdivision.next()){
                   sdivision=(rdivision.getString("ID")); 
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            ResultSet rcategoria = conn.getValues("ID", "competicion", "COMPETICION = '" + scategoria + "'", "");
            try {
                while(rcategoria.next()){
                   scategoria=(rcategoria.getString("ID")); 
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }           

            int categoria = Integer.parseInt(scategoria);    
            int division = Integer.parseInt(sdivision);
            conn.updateData("equipos", "NOMBRE = '" + nuevo + "', ID_COMPETICION = '" +categoria + "', ID_DIVISION = '" + division + "'",  "NOMBRE LIKE '" + anterior+ "'");
            conn.getConection().commit();
            conn.desconectar();
        
    }
        
    public void eliminarEquipo(String equipo) throws SQLException{

            conn.conectar();
            conn.deleteData("equipos", "NOMBRE like '" + equipo + "'");
            conn.getConection().commit();
            conn.desconectar();

    }
        
    public void vaciarEquipo() throws SQLException{

            conn.conectar();
            conn.deleteTable("equipos");
            conn.getConection().commit();
            conn.desconectar();

    }
    
    public void congelarEquipo(int idEquipo, boolean estado) throws SQLException{
            conn.conectar();
            conn.updateData("equipos", "congelado = " + estado, "ID = " + idEquipo);
            conn.getConection().commit();
            conn.desconectar();

    }
    
    public void mostrarRestricciones(){
        try {
            limpiarCampos();
            int idEquipo = r.cbEquipos.getSelectedIndex();
            conn.conectar();
            ResultSet equi = conn.getValues("r.*, d.dia, e.nombre",
                    "restricciones r LEFT JOIN dias d ON r.ID_DIA = d.ID LEFT JOIN equipos e ON r.ID_COINCIDE = e.ID",
                    "r.ID_EQUIPO = " + idEquipo, "");
            int ambasLunes = 0;
            int ambasMartes = 0;
            int ambasMiercoles = 0;
            int ambasJueves = 0;
            int ambasViernes = 0;
            int ambasSabado = 0;
            int ambasDomingo = 0;
            while(equi.next()){
                if(equi.getString("dia") != null){
                    switch (equi.getString("dia")){
                        case "Lunes":
                            ambasLunes++;
                            r.ckLunesEquipos.setSelected(true);
                            if(ambasLunes == 2){
                                r.cbLunes.setSelectedIndex(3); 
                            }else{
                               r.cbLunes.setSelectedIndex(equi.getInt("HORA")); 
                            }
                            break;
                        case "Martes":
                            ambasMartes++;
                            r.ckMartesEquipos.setSelected(true);
                            if(ambasMartes == 2){
                                r.cbMartes.setSelectedIndex(3);
                            }else{
                                r.cbMartes.setSelectedIndex(equi.getInt("HORA"));
                            }
                            break;
                        case "Miércoles":
                            ambasMiercoles++;
                            r.ckMiercolesEquipos.setSelected(true);
                            if(ambasMiercoles == 2){
                                r.cbMiercoles.setSelectedIndex(3);
                            }else{
                                r.cbMiercoles.setSelectedIndex(equi.getInt("HORA"));
                            }
                            break;
                        case "Jueves":
                            ambasJueves++;
                            r.ckJuevesEquipos.setSelected(true);
                            if(ambasJueves == 2){
                               r.cbJueves.setSelectedIndex(3); 
                            }else{
                                r.cbJueves.setSelectedIndex(equi.getInt("HORA"));
                            }
                            break;
                        case "Viernes":
                            ambasViernes++;
                            r.ckViernesEquipos.setSelected(true);
                            if(ambasViernes == 2){
                                r.cbViernes.setSelectedIndex(3);
                            }else{
                                r.cbViernes.setSelectedIndex(equi.getInt("HORA"));
                            }
                            break;
                        case "Sábado":
                            ambasSabado++;
                            r.ckSabadoEquipos.setSelected(true);
                            if(ambasSabado == 2){
                                r.cbSabado.setSelectedIndex(3);
                            }else{
                                r.cbSabado.setSelectedIndex(equi.getInt("HORA"));
                            }
                            break;
                        case "Domingo":
                            ambasDomingo++;
                            r.ckDomingoEquipos.setSelected(true);
                            if(ambasDomingo == 2){
                                r.cbDomingo.setSelectedIndex(3);
                            }else{
                                r.cbDomingo.setSelectedIndex(equi.getInt("HORA"));
                            }
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
