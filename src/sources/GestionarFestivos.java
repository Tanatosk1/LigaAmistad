package sources;

import connection.Conn;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author santy
 */
public class GestionarFestivos {
    private final Conn conn = new Conn();

    
        public void guardarAplazado (String fecha, String motivo) throws SQLException{

            conn.conectar();
            conn.insertData("festivos", "null,'" + fecha + "','" + motivo + "'" );
            conn.getConection().commit();
            conn.desconectar();
    }

}
