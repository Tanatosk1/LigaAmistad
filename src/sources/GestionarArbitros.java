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
    
    public void guardarArbitro( String nombre, String apellido, int especial ) throws SQLException{
        
            conn.conectar();

            conn.insertData("arbitros", "null,'" + nombre + "', '" + apellido + "','" + especial + "', 0");
            conn.getConection().commit();
            conn.desconectar();      
    }
    
    /*public void editarArbitro(String nombre, String apellido, boolean especial, boolean activo) throws SQLException{

            conn.updateData("arbitros", "NOMBRE = '" + nombre + "', APELLIDOS = '" +apellido + "',  NIVEL = '" + especial + "'",  "NOMBRE LIKE '" + anterior+ "'");
            conn.getConection().commit();
            conn.desconectar();
        
    }
        
    public void eliminarEquipo(String equipo) throws SQLException{

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
    
}
