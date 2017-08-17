package sources;

import connection.Conn;
import java.sql.SQLException;

/**
 *
 * @author santy
 */
public class GestionarFestivos {
    private final Conn conn = new Conn();
    
        public void guardarDivision(String fecha_inicio) throws SQLException{

            conn.conectar();
      //      conn.insertData("festivos", "null,'" + division + "'" );
            conn.getConection().commit();
            conn.desconectar();
    }
    
}
