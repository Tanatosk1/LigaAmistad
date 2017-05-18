package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import views.Restricciones;

/**
 *
 * @author santy
 */
public class Filtros {
    private final Conn con = new Conn();
    
    public Filtros(int categoria, int division, int jornada, JTable tabla){
        vaciarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Object[] fila = new Object[9];
        String where = "";
        con.conectar();
        int cat = categoria;
        int div = division;
        int jor = jornada;
        String select = "c.ID, c.JORNADA, c.FECHA, c.HORA, l.NOMBRE AS \"LOCAL\", "
                + "v.NOMBRE AS \"VISITANTE\", ca.CAMPO, c.JUGADO, com.COMPETICION AS \"CATEGORIA\", "
                + "d.DIVISION AS \"DIVISION\"";
        String from = " Campeonato c INNER JOIN Equipos l ON c.ID_LOCAL = l.ID "
                + "INNER JOIN Equipos v ON c.ID_VISITANTE = v.ID "
                + "LEFT JOIN Campos ca ON c.ID_CAMPO = ca.ID INNER JOIN Competicion com ON l.ID_COMPETICION = com.ID "
                + "INNER JOIN Division d ON l.ID_DIVISION = d.ID";
        if(cat != 0 && div < 0 && jor != 0){
            where = "com.ID = " + cat + " and d.ID = 4 and c.JORNADA = " + jor;
        }else if(cat != 0 && div != 0 && jor == 0){
            where = "com.ID = " + cat + " and d.ID = "+(div-1);
        }else if(cat != 0 && div == 0 && jor != 0){
            where = "com.ID = " + cat + " and c.JORNADA = " + jor;
        }else if(cat != 0 && div == 0 && jor == 0){
            where = "com.ID = " + cat;
        }else if(cat == 0 && div < 0 && jor == 0){
            where = "d.ID = 4";
        }else if(cat == 0 && div != 0 && jor == 0){
            where = "d.ID = " + (div-1);
        }else if(cat == 0 && div < 0 && jor != 0){
            where = "d.ID = 4 and c.JORNADA = " + jor; 
        }else if(cat == 0 && div != 0 && jor != 0){
            where = "d.ID = "+(div-1)+" and c.JORNADA = " + jor;
        }else if(cat == 0 && div == 0 && jor != 0){
            where = "c.JORNADA = " + jor;
        }else if(cat != 0 && div != 0 && jor != 0){
            where = "com.ID = " + cat + " and d.ID = " + (div-1) + " and c.JORNADA = " + jor;
        }
        String order = "c.JORNADA, com.ID, d.ID"; 
        ResultSet campeonato = con.getValues(select, from, where, order);    
            try {
                if(!campeonato.first()){
                    JOptionPane.showMessageDialog(null, "No hay datos con los campos seleccionados", "No hay datos", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    campeonato.absolute(0);
                    while(campeonato.next()){
                        fila[0] = campeonato.getInt("JORNADA");
                        fila[1] = campeonato.getString("FECHA");
                        fila[2] = "";
                        fila[3] = campeonato.getString("HORA");
                        fila[4] = campeonato.getString("LOCAL");
                        fila[5] = campeonato.getString("VISITANTE");
                        fila[6] = campeonato.getString("CAMPO");
                        fila[7] = campeonato.getString("CATEGORIA")+" - "+campeonato.getString("DIVISION");
        
                        model.addRow(fila);
                    }
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        try {
            campeonato.close();
        } catch (SQLException ex) {
            Logger.getLogger(Filtros.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.desconectar();
        tabla.setModel(model);
    }
    
    private void vaciarTabla(JTable tabla){
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < tabla.getRowCount(); i++) {
           model.removeRow(i);
           i-=1;
       }
   }
    
    
}
