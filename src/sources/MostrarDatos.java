package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import views.Restricciones;

/**
 *
 * @author santy
 */
public class MostrarDatos {
    private final Conn con = new Conn();
        
    public void llenarComboCategorias(JComboBox cbCategoria){
        con.conectar();
        ResultSet categoria = con.getValues("COMPETICION", "Competicion", "", "ID");
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
        ResultSet division = con.getValues("DIVISION", "Division", "", "ID");
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
            cbNoCoincidir.addItem("");
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
        ResultSet campos = con.getValues("CAMPO", "Campos", "", "ID");
            try {
                while(campos.next()){
                    cbCampos.addItem(campos.getString("CAMPO"));
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
    }
    
    public void llenarCamposArbitros(JComboBox cbNoCoincidirArbitro){
        con.conectar();
        cbNoCoincidirArbitro.addItem("");
        ResultSet campos = con.getValues("CAMPO", "Campos", "", "ID");
            try {
                while(campos.next()){
                    cbNoCoincidirArbitro.addItem(campos.getString("CAMPO"));
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
    }
    
    public void llenarComboArbitros(JComboBox cbArbitro){
        con.conectar();
        ResultSet arbitros = con.getValues("*", "arbitros", "", "ID");
            try {
                while(arbitros.next()){
                    cbArbitro.addItem(arbitros.getString("NOMBRE") + " " + arbitros.getString("APELLIDOS"));
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
    }
      
    public void llenarTCalendario(JTable tCalendario){
        DefaultTableModel model = (DefaultTableModel) tCalendario.getModel();
        Object[] fila = new Object[11];
        Date fechaFila = null;
        con.conectar();
        String select = "c.ID, c.JORNADA, c.FECHA, c.HORA, a.APELLIDOS, a.NOMBRE AS \"ARBITRO\",  l.NOMBRE AS \"LOCAL\", "
                + "v.NOMBRE AS \"VISITANTE\", ca.CAMPO, c.APLAZADO, com.COMPETICION AS \"CATEGORIA\", "
                + "d.DIVISION AS \"DIVISION\"";
        String from = " Campeonato c INNER JOIN Equipos l ON c.ID_LOCAL = l.ID "
                + "INNER JOIN Equipos v ON c.ID_VISITANTE = v.ID "
                + "LEFT JOIN Campos ca ON c.ID_CAMPO = ca.ID INNER JOIN Competicion com ON l.ID_COMPETICION = com.ID "
                + "INNER JOIN Division d ON l.ID_DIVISION = d.ID "
                + "LEFT JOIN arbitros a ON a.ID = c.ID_ARBITRO";
        String order = "c.JORNADA, c.FECHA, ca.CAMPO, c.HORA ASC"; 
        ResultSet campeonato = con.getValues(select, from, "", order);
            SimpleDateFormat formatterShow = new SimpleDateFormat("dd-MM-yyyy");
            try {
                while(campeonato.next()){
                    fila[0] = campeonato.getInt("ID");
                    fila[1] = campeonato.getInt("JORNADA");
                    if(campeonato.getDate("FECHA") != null){
                        fechaFila = campeonato.getDate("FECHA");
                        fila[2] = formatterShow.format(fechaFila);
                        fila[3] = calculaDia((Date)campeonato.getDate("FECHA"));
                        fila[4] = campeonato.getString("HORA").substring(0, 5);
                    }else{
                        fila[2] = "";
                        fila[3] = "";
                        fila[4] = "";
                    }
                    if(campeonato.getString("ARBITRO") != null){
                        fila[5] = campeonato.getString("ARBITRO") + " " + campeonato.getString("APELLIDOS");
                    }else{
                        fila[5] = "";
                    }
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
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
        tCalendario.setModel(model);
    }
    
    public void llenarTAplazados(JTable tAplazados){
        DefaultTableModel model = (DefaultTableModel) tAplazados.getModel();
        Object[] fila = new Object[9];
        con.conectar();
        String select = "c.ID, c.JORNADA, c.FECHA, c.HORA, l.NOMBRE AS \"LOCAL\", "
                + "v.NOMBRE AS \"VISITANTE\", ca.CAMPO, c.APLAZADO, com.COMPETICION AS \"CATEGORIA\", "
                + "d.DIVISION AS \"DIVISION\"";
        String from = " Campeonato c INNER JOIN Equipos l ON c.ID_LOCAL = l.ID "
                + "INNER JOIN Equipos v ON c.ID_VISITANTE = v.ID "
                + "LEFT JOIN Campos ca ON c.ID_CAMPO = ca.ID INNER JOIN Competicion com ON l.ID_COMPETICION = com.ID "
                + "INNER JOIN Division d ON l.ID_DIVISION = d.ID";
        String order = "c.JORNADA, com.ID, d.ID"; 
        ResultSet campeonato = con.getValues(select, from, "APLAZADO = 1", order);
            try {
                while(campeonato.next()){
                    fila[0] = campeonato.getInt("ID");
                    fila[1] = campeonato.getInt("JORNADA");
                    ResultSet campos_horas = con.getValues("DISTINCT ch.ID_CAMPO, h.hora", "cam_horarios ch inner join hora h on ch.ID_HORA = h.ID inner join campos c on ch.ID_CAMPO = c.ID", "c.CONGELADO = 0", "ch.ID_CAMPO LIMIT 2");
                    try{
                        while(campos_horas.next()){
                            
                        }
                    }catch(SQLException ex){}
                    fila[5] = campeonato.getString("LOCAL");
                    fila[6] = campeonato.getString("VISITANTE");
                    fila[7] = "Seleccione un campo";
                    JComboBox< Object > jc2 = new JComboBox<>();
                    DefaultComboBoxModel modelo2 = new DefaultComboBoxModel();

                    ResultSet campos = con.getValues("DISTINCT c.ID, c.CAMPO", "campos c inner join cam_horarios ch on c.ID = ch.ID_CAMPO", "c.CONGELADO = 0 and ch.ASIGNADO = 0", "c.ID");
                    try {
                        while(campos.next()){
                            modelo2.addElement(campos.getString("CAMPO"));
                        } 
                    }catch (SQLException ex) {
                        Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    jc2.setModel(modelo2);
                    TableColumn columna2 = tAplazados.getColumnModel().getColumn(7);
                    TableCellEditor editor2 = new DefaultCellEditor(jc2);
                    columna2.setCellEditor(editor2);
                    
                    if(campeonato.getString("DIVISION").equalsIgnoreCase("sin división")){
                        fila[8] = campeonato.getString("CATEGORIA");
                    }else{
                        fila[8] = campeonato.getString("CATEGORIA")+" - "+campeonato.getString("DIVISION");
                    }
                    model.addRow(fila);                   
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
        tAplazados.setModel(model);
    }

        public void llenarTInactivos(JTable tInactivos){
        DefaultTableModel model = (DefaultTableModel) tInactivos.getModel();
        Object[] fila = new Object[10];
        con.conectar();
        String select = "ID, FECHA, MOTIVO";
        String from = "festivos";
        String order = "ID, FECHA, MOTIVO";  
        ResultSet festivos = con.getValues(select, from, "", order);
            try {
                while(festivos.next()){
                    fila[0] = festivos.getInt("ID");
                    fila[1] = festivos.getDate("FECHA");
                    fila[2] = festivos.getString("MOTIVO");
                    model.addRow(fila);                   
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
        tInactivos.setModel(model);
    }
    
    public void llenarComboAgregarEquiposCategoria(JComboBox cbAgregarEquipoCategoria, String equipo) {
        con.conectar();
        String select ="c.COMPETICION";
        String from ="competicion c INNER JOIN equipos e ON c.ID = e.ID_COMPETICION";
        String where ="e.NOMBRE = '"+equipo+"'" ;
        ResultSet equipos = con.getValues(select, from, where , "");        

            try {
                while(equipos.next()){
                cbAgregarEquipoCategoria.setSelectedItem(equipos.getString("COMPETICION"));
                }

            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
    }
    
    public void llenarComboAgregarEquiposDivision (JComboBox cbAgregarEquipoDivision, String equipo) {
        con.conectar();
        String select ="d.DIVISION";
        String from ="division d INNER JOIN equipos e on d.ID = e.ID_DIVISION";
        String where ="e.NOMBRE = '"+equipo+"'" ;
        ResultSet equipos = con.getValues(select, from, where , "");        

            try {
                while(equipos.next()){
                cbAgregarEquipoDivision.setSelectedItem(equipos.getString("DIVISION"));
                }

            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        con.desconectar();
    }
    
    public void llenarComboEditarArbitros(JComboBox cmbArbitros){
        con.conectar();
        String select = "*";
        String from ="arbitros";
        
        ResultSet arbitros = con.getValues(select, from, "", "");
        try{
            while(arbitros.next()){
                cmbArbitros.addItem(arbitros.getString("NOMBRE") +" "+ arbitros.getString("APELLIDOS"));
            }
        }catch(SQLException ex){}
        
    }
    
    private String calculaDia(Date fecha){
        String dia = "";
        Calendar d = Calendar.getInstance();
        String[] strDays = new String[]{"Domingo", "Lunes", "Martes", "Miércoles",
        			    "Jueves", "Viernes", "Sábado"};
        
        d.setTime(fecha);
        dia = strDays[d.get(Calendar.DAY_OF_WEEK)-1];
        return dia;
    }
    
    public void reiniciarTablaInactivos(JTable tInactivos){
        try{
            DefaultTableModel temp = (DefaultTableModel) tInactivos.getModel();
            int a =temp.getRowCount()-0;
            for(int i=0; i<a; i++)
                temp.removeRow(0); 
            }catch(Exception e){
            System.out.println(e);
            }
    }
    

    public static int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String fechaInicioString = df.format(fechaInicial);
            try {
            fechaInicial = df.parse(fechaInicioString);
            } catch (ParseException ex) {
            }
            String fechaFinalString = df.format(fechaFinal);
             try {
            fechaFinal = df.parse(fechaFinalString);
            } catch (ParseException ex) {
            }
             long fechaInicialMs = fechaInicial.getTime();
             long fechaFinalMs = fechaFinal.getTime();
            long diferencia = fechaFinalMs - fechaInicialMs;
            double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            return ((int) dias);
            
    }
}
