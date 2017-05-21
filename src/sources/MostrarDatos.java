package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import views.Restricciones;

/**
 *
 * @author santy
 */
public class MostrarDatos {
    private final Conn con = new Conn();
    
    public void llenarComboCategorias(JComboBox cbCategoria){
        con.conectar();
        ResultSet categoria = con.getValues("COMPETICION", "Competicion", "", "Competicion");
            try {
                while(categoria.next()){
                    cbCategoria.addItem(categoria.getString("COMPETICION"));
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
    }
    
    public void llenarComboDivisiones(JComboBox cbDivision){
        con.conectar();
        ResultSet division = con.getValues("DIVISION", "Division", "", "DIVISION");
            try {
                while(division.next()){
                    cbDivision.addItem(division.getString("DIVISION"));
                    
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
    }
    
    public void llenarComboJornadas(JComboBox cbJornadas){
        con.conectar();
        ResultSet jornada = con.getValues("DISTINCT JORNADA", "Campeonato", "", "JORNADA");
            try {
                while(jornada.next()){
                    cbJornadas.addItem(jornada.getInt("JORNADA"));
                    
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
    }
    
    public void llenarComboEquipos(JComboBox cbEquipos){
        con.conectar();
        ResultSet equipos = con.getValues("NOMBRE", "Equipos", "", "ID");
            try {
                while(equipos.next()){
                    cbEquipos.addItem(equipos.getString("NOMBRE"));
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
    }
    
    public void llenarComboNoCoincidir(JComboBox cbNoCoincidir, int id){
        cbNoCoincidir.removeAllItems();
        try {
            
            int idCat = 0;
            con.conectar();
            ResultSet idCategoria = con.getValues("ID_COMPETICION", "Equipos", "ID = " + id, "");
            while(idCategoria.next()){
                idCat = idCategoria.getInt("ID_COMPETICION");
            }
            ResultSet noCoincidir = con.getValues("NOMBRE", "Equipos", "ID_COMPETICION != " + idCat, "ID");
            while(noCoincidir.next()){
                cbNoCoincidir.addItem(noCoincidir.getString("NOMBRE"));
            }
            con.desconectar();
        }catch (SQLException ex) {
            Logger.getLogger(MostrarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void llenarComboCampos(JComboBox cbCampos){
        con.conectar();
        ResultSet campos = con.getValues("CAMPO", "Campos", "", "Campo");
            try {
                while(campos.next()){
                    cbCampos.addItem(campos.getString("CAMPO"));
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
    }
    
    public void llenarTCalendario(JTable tCalendario){
        DefaultTableModel model = (DefaultTableModel) tCalendario.getModel();
        Object[] fila = new Object[9];
        con.conectar();
        String select = "c.ID, c.JORNADA, c.FECHA, c.HORA, l.NOMBRE AS \"LOCAL\", "
                + "v.NOMBRE AS \"VISITANTE\", ca.CAMPO, c.JUGADO, com.COMPETICION AS \"CATEGORIA\", "
                + "d.DIVISION AS \"DIVISION\"";
        String from = " Campeonato c INNER JOIN Equipos l ON c.ID_LOCAL = l.ID "
                + "INNER JOIN Equipos v ON c.ID_VISITANTE = v.ID "
                + "LEFT JOIN Campos ca ON c.ID_CAMPO = ca.ID INNER JOIN Competicion com ON l.ID_COMPETICION = com.ID "
                + "INNER JOIN Division d ON l.ID_DIVISION = d.ID";
        String order = "c.JORNADA, com.ID, d.ID"; 
        ResultSet campeonato = con.getValues(select, from, "", order);
            try {
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
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
        tCalendario.setModel(model);
    }
}
