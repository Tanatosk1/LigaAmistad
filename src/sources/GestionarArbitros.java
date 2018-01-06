package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import views.Restricciones;

/**
 *
 * @author Santy
 */
public class GestionarArbitros {
    private final Conn conn = new Conn();
    private Restricciones res = null;
    
    public GestionarArbitros(Restricciones res){
        this.res = res;
    }
    
    public GestionarArbitros(){
        
    }
    
    public void guardarArbitro( String nombre, String apellido, int nivel ) throws SQLException{
        
            conn.conectar();

            conn.insertData("arbitros", "null,'" + nombre + "', '" + apellido + "','" + nivel + "', 0");
            conn.getConection().commit();
            conn.desconectar();      
    }
    
    public void editarArbitro(String[] arbitro, String nombre, String apellido, int nivel) throws SQLException{
            conn.conectar();
            if(arbitro.length > 1){
                conn.updateData("arbitros", "NOMBRE = '" + nombre + "', APELLIDOS = '" +apellido + "',  NIVEL = '" + nivel + "'",  "NOMBRE LIKE '" + arbitro[0]+ "' and APELLIDOS like '"+ arbitro[1] +"'");
            }else{
                conn.updateData("arbitros", "NOMBRE = '" + nombre + "', APELLIDOS = '" +apellido + "',  NIVEL = '" + nivel + "'",  "NOMBRE LIKE '" + arbitro[0]+ "'");
            }
            conn.getConection().commit();
            conn.desconectar();
        
    }
        
    public void eliminarArbitro(String nombre, String apellido) throws SQLException{

            conn.conectar();
            conn.deleteData("arbitros", "NOMBRE like '" + nombre + "' and APELLIDOS like '" + apellido + "'");
            conn.getConection().commit();
            conn.desconectar();

    }
        
    public void vaciarArbitros() throws SQLException{

            conn.conectar();
            conn.deleteTable("arbitros");
            conn.getConection().commit();
            conn.desconectar();

    }
    
    public void congelarArbitro(int idArbitro, boolean estado) throws SQLException{
            conn.conectar();
            conn.updateData("arbitros", "congelado = " + estado, "ID = " + idArbitro);
            conn.getConection().commit();
            conn.desconectar();

    }
    
    public boolean getNivel(String nombre, String apellido) throws SQLException{
        boolean nivel = false;
        conn.conectar();
        ResultSet rsNivel = conn.getValues("nivel", "arbitros", "NOMBRE like '"+nombre+"' and APELLIDOS like '"+apellido+"' LIMIT 1", "");
        while(rsNivel.next()){
            if(rsNivel.getInt("NIVEL") == 1){
                nivel = true;
                return nivel;
            }
        }
        conn.desconectar();
        return nivel;
    }
    
    public void gestionarArbitro(){
        try {
            int id = res.cbArbitros.getSelectedIndex();
            conn.conectar();
            conn.updateData("arbitros", "LUNES = " + res.ckLunesArbitro.isSelected() +
                                        ", MARTES = " + res.ckMartesArbitro.isSelected() +
                                        ", MIERCOLES = " + res.ckMiercolesArbitro.isSelected() +
                                        ", JUEVES = " + res.ckJuevesArbitro.isSelected() +
                                        ", VIERNES = " + res.ckViernesArbitro.isSelected() +
                                        ", SABADO = " + res.ckSabadoArbitro.isSelected() + 
                                        ", DOMINGO = " + res.ckDomingoArbitro.isSelected() +
                                        ", IDCAMPO = " + res.cbNoCoincidirArbitro.getSelectedIndex(), "ID = " + id);
            conn.getConection().commit();
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionarArbitros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarDatos(){
        try {
            limpiarSeleccion();
            int idArbitro = res.cbArbitros.getSelectedIndex();
            conn.conectar();
            ResultSet arb = conn.getValues("*", "arbitros", "ID = "+idArbitro, "");
            while(arb.next()){
                if(arb.getInt("LUNES") == 1){
                    res.ckLunesArbitro.setSelected(true);
                }
                if(arb.getInt("MARTES") == 1){
                    res.ckMartesArbitro.setSelected(true);
                }
                if(arb.getInt("MIERCOLES") == 1){
                    res.ckMiercolesArbitro.setSelected(true);
                }
                if(arb.getInt("JUEVES") == 1){
                    res.ckJuevesArbitro.setSelected(true);
                }
                if(arb.getInt("VIERNES") == 1){
                    res.ckViernesArbitro.setSelected(true);
                }
                if(arb.getInt("SABADO") == 1){
                    res.ckSabadoArbitro.setSelected(true);
                }
                if(arb.getInt("DOMINGO") == 1){
                    res.ckDomingoArbitro.setSelected(true);
                }
                res.cbNoCoincidirArbitro.setSelectedIndex(arb.getInt("IDCAMPO"));
            }
            arb.close();
            ResultSet frozen = conn.getValues("CONGELADO", "arbitros", "ID = " + idArbitro,"");
            while (frozen.next()){
                if(frozen.getInt("CONGELADO")==1){
                    res.ckCongelarArbitro.setSelected(true);
                }
            }
            frozen.close();
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionarCampos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void limpiarSeleccion(){
        res.ckLunesArbitro.setSelected(false);
        res.ckMartesArbitro.setSelected(false);
        res.ckMiercolesArbitro.setSelected(false);
        res.ckJuevesArbitro.setSelected(false);
        res.ckViernesArbitro.setSelected(false);
        res.ckSabadoArbitro.setSelected(false);
        res.ckDomingoArbitro.setSelected(false);
        res.ckCongelarArbitro.setSelected(false);
        res.cbNoCoincidirArbitro.setSelectedIndex(0);
    }
    
}
