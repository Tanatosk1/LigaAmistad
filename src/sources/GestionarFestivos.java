package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import views.Restricciones;

/**
 *
 * @author santy
 */
public class GestionarFestivos {
    private final Conn conn = new Conn();

    
        public void guardarFestivo (String fecha, String motivo) throws SQLException{

            conn.conectar();
            conn.insertData("festivos", "null,'" + fecha + "','" + motivo + "'" );
            conn.getConection().commit();
            conn.desconectar();
    }
        
        public void eliminarDiaFestivo(int id) throws SQLException{
            conn.conectar();           
            ResultSet rid = conn.getValues("ID", "festivos", "ID = '" + id + "'", "");
            try {
                if(rid.next()){
                    conn.deleteData("festivos", "ID = '" + id + "'");
                    conn.getConection().commit();
                    conn.desconectar();
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          
}
