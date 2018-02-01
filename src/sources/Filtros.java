package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Object[] fila = new Object[11];
        String where = "";
        Calendar getDay = Calendar.getInstance();
        con.conectar();
        int cat = categoria;
        int div = division;
        int jor = jornada;
        String select = "c.ID, c.JORNADA, c.FECHA, c.HORA, a.NOMBRE AS \"ARBITRO\", l.NOMBRE AS \"LOCAL\", "
                + "v.NOMBRE AS \"VISITANTE\", ca.CAMPO, c.APLAZADO, com.COMPETICION AS \"CATEGORIA\", "
                + "d.DIVISION AS \"DIVISION\"";
        String from = " Campeonato c INNER JOIN Equipos l ON c.ID_LOCAL = l.ID "
                + "INNER JOIN Equipos v ON c.ID_VISITANTE = v.ID "
                + "LEFT JOIN Campos ca ON c.ID_CAMPO = ca.ID INNER JOIN Competicion com ON l.ID_COMPETICION = com.ID "
                + "INNER JOIN Division d ON l.ID_DIVISION = d.ID "
                + "LEFT JOIN arbitros a ON a.ID = c.ID_ARBITRO";
        if(cat != 0 && div != 0 && jor == 0){
            where = "com.ID = " + cat + " and d.ID = "+div;
        }else if(cat != 0 && div == 0 && jor != 0){
            where = "com.ID = " + cat + " and c.JORNADA = " + jor;
        }else if(cat != 0 && div == 0 && jor == 0){
            where = "com.ID = " + cat;
        }else if(cat == 0 && div != 0 && jor == 0){
            where = "d.ID = " + div;
        }else if(cat == 0 && div != 0 && jor != 0){
            where = "d.ID = "+div+" and c.JORNADA = " + jor;
        }else if(cat == 0 && div == 0 && jor != 0){
            where = "c.JORNADA = " + jor;
        }else if(cat != 0 && div != 0 && jor != 0){
            where = "com.ID = " + cat + " and d.ID = " + div + " and c.JORNADA = " + jor;
        }
        String order = "c.FECHA DESC";
        //String order = "c.JORNADA, com.COMPETICION, d.DIVISION"; 
        ResultSet campeonato = con.getValues(select, from, where, order);    
        SimpleDateFormat formatterShow = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaFila = null;
            try {
                if(!campeonato.first()){
                    ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
                    JOptionPane.showMessageDialog(null, "No hay datos con los campos seleccionados", "No hay datos", JOptionPane.QUESTION_MESSAGE, icon);
                }else{
                    campeonato.absolute(0);
                    while(campeonato.next()){
                        fila[0] = campeonato.getInt("ID");
                        fila[1] = campeonato.getInt("JORNADA");
                        if(campeonato.getDate("FECHA") != null){
                            fechaFila = campeonato.getDate("FECHA");
                            fila[2] = formatterShow.format(fechaFila);
                            getDay.setTime(campeonato.getDate("FECHA"));
                            int day = getDay.get(Calendar.DAY_OF_WEEK)-1;
                            fila[3] = getDia(day);
                            fila[4] = campeonato.getString("HORA").substring(0, 5);
                        }else{
                            fila[2] = null;
                            fila[3] = null;
                            fila[4] = null;
                        }
                        fila[5] = campeonato.getString("ARBITRO");
                        fila[6] = campeonato.getString("CAMPO");
                        fila[7] = campeonato.getString("LOCAL");
                        fila[8] = campeonato.getString("VISITANTE");
                        
                        if(campeonato.getString("DIVISION").equalsIgnoreCase("sin división")){
                            fila[9] = campeonato.getString("CATEGORIA");
                        }else{
                            fila[9] = campeonato.getString("CATEGORIA")+" - "+campeonato.getString("DIVISION");
                        }
                        if(campeonato.getInt("APLAZADO") == 1){
                            fila[10] = true;
                        }else{
                            fila[10] = null;
                        }
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
    
    public Filtros(JTable tabla, Date inicio, Date fin){
        vaciarTabla(tabla);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Object[] fila = new Object[11];
        
        Calendar getDay = Calendar.getInstance();
        con.conectar();
        String select = "c.ID, c.JORNADA, c.FECHA, c.HORA, a.NOMBRE AS \"ARBITRO\", l.NOMBRE AS \"LOCAL\", "
                + "v.NOMBRE AS \"VISITANTE\", ca.CAMPO, c.APLAZADO, com.COMPETICION AS \"CATEGORIA\", "
                + "d.DIVISION AS \"DIVISION\"";
        String from = " Campeonato c INNER JOIN Equipos l ON c.ID_LOCAL = l.ID "
                + "INNER JOIN Equipos v ON c.ID_VISITANTE = v.ID "
                + "LEFT JOIN Campos ca ON c.ID_CAMPO = ca.ID INNER JOIN Competicion com ON l.ID_COMPETICION = com.ID "
                + "INNER JOIN Division d ON l.ID_DIVISION = d.ID "
                + "LEFT JOIN arbitros a ON a.ID = c.ID_ARBITRO";
        String where = "c.FECHA BETWEEN '" + formatter.format(inicio)+"' AND '" + formatter.format(fin) +"'";
        //String order = "c.FECHA DESC";
        String order = "c.JORNADA, com.ID, d.ID, c.FECHA ASC, c.HORA ASC"; 
        ResultSet campeonato = con.getValues(select, from, where, order);    
            try {
                if(!campeonato.first()){
                    ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
                    JOptionPane.showMessageDialog(null, "No hay datos con los campos seleccionados", "No hay datos", JOptionPane.QUESTION_MESSAGE, icon);
                }else{
                    campeonato.absolute(0);
                    while(campeonato.next()){
                        fila[0] = campeonato.getInt("ID");
                        fila[1] = campeonato.getInt("JORNADA");
                        fila[2] = campeonato.getString("FECHA");
                        if(campeonato.getDate("FECHA") != null){
                            getDay.setTime(campeonato.getDate("FECHA"));
                            int day = getDay.get(Calendar.DAY_OF_WEEK)-1;
                            fila[3] = getDia(day);
                        }else{
                            fila[3] = null;
                        }
                        fila[4] = campeonato.getString("HORA");
                        fila[5] = campeonato.getString("ARBITRO");
                        fila[6] = campeonato.getString("CAMPO");
                        fila[7] = campeonato.getString("LOCAL");
                        fila[8] = campeonato.getString("VISITANTE");
                        
                        if(campeonato.getString("DIVISION").equalsIgnoreCase("sin división")){
                            fila[9] = campeonato.getString("CATEGORIA");
                        }else{
                            fila[9] = campeonato.getString("CATEGORIA")+" - "+campeonato.getString("DIVISION");
                        }
                        if(campeonato.getInt("APLAZADO") == 1){
                            fila[10] = true;
                        }else{
                            fila[10] = null;
                        }
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
    
   public final String getDia(int dia){
        switch(dia){
            case 1:
                return "Lunes";
             case 2:
                return "Martes";
            case 3:
                return "Miércoles";
            case 4:
                return "Jueves";
            case 5:
                return "Viernes";
            case 6:
                return "Sábado";
            case 7:
                return "Domingo";
        }
        return null;
    }
}
