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
    
    public void guardarArbitro( String nombre, String apellido, int nivel ) throws SQLException{
        
            conn.conectar();

            conn.insertData("arbitros", "null,'" + nombre + "', '" + apellido + "','" + nivel + "', 0");
            conn.getConection().commit();
            conn.desconectar();      
    }
    
    public void editarArbitro(String[] arbitro, String nombre, String apellido, int nivel) throws SQLException{
            conn.conectar();
            conn.updateData("arbitros", "NOMBRE = '" + nombre + "', APELLIDOS = '" +apellido + "',  NIVEL = '" + nivel + "'",  "NOMBRE LIKE '" + arbitro[0]+ "' and APELLIDOS like '"+ arbitro[1] +"'");
            conn.getConection().commit();
            conn.desconectar();
        
    }
        
    /*public void eliminarEquipo(String equipo) throws SQLException{

            conn.conectar();
            conn.deleteData("equipos", "NOMBRE like '" + equipo + "'");
            conn.getConection().commit();
            conn.desconectar();

    }*/
        
    public void vaciarEquipo() throws SQLException{

            conn.conectar();
            conn.deleteTable("arbitros");
            conn.getConection().commit();
            conn.desconectar();

    }
    
    /*public void congelarEquipo(int idEquipo, boolean estado) throws SQLException{
            conn.conectar();
            conn.updateData("equipos", "congelado = " + estado, "ID = " + idEquipo);
            conn.getConection().commit();
            conn.desconectar();

    }*/
    
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
    
}
