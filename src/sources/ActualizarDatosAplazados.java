package sources;

import connection.Conn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author santy
 */
public class ActualizarDatosAplazados implements TableModelListener, ActionListener{
    
    private static JTable tAplazados;
    private final Conn conn = new Conn();
    private MostrarDatos md;
    private Date fechaInicio;
    private Date fechaFin;
    
    public ActualizarDatosAplazados(){

    }
    
    public ActualizarDatosAplazados(JTable table, Date fInicio, Date fFin){
        ActualizarDatosAplazados.tAplazados = table;
        this.fechaInicio = fInicio;
        this.fechaFin = fFin;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        conn.conectar();
        DefaultTableModel model = (DefaultTableModel) tAplazados.getModel();
        try {
            switch(e.getColumn()){
                case 7:
                    //Creamos un combo box sin modelo
                    JComboBox<Object> jc = new JComboBox<>();
                    //Creamos un modelo de combobox y le añadimos 3 elementos
                    DefaultComboBoxModel modelo = new DefaultComboBoxModel();

                    ResultSet dias = conn.getValues("DISTINCT d.DIA", "dias d INNER JOIN cam_horarios ch ON d.id = ch.ID_DIA", "ID_CAMPO = (SELECT ID FROM campos where CAMPO like '"+model.getValueAt(e.getFirstRow(), e.getColumn())+"') AND ASIGNADO = 0", "");
                    while (dias.next()){
                        modelo.addElement(dias.getString("DIA"));
                    }
                    //Asignamos el modelo al combobox
                    jc.setModel(modelo);
                    //Ahora vamos a recoger una columna que será donde insertemos el combobox
                    TableColumn columna = tAplazados.getColumnModel().getColumn(3);
                    //Creamos un nuevo editor de celda. Tambien puede insertarse checkboxs y textfields
                    TableCellEditor editor = new DefaultCellEditor(jc);
                    //Le asignamos a la columna el editor creado
                    columna.setCellEditor(editor);
                    dias.close();
                    break;
                case 3:
                    //Creamos un combo box sin modelo
                    JComboBox<Object> hor = new JComboBox<>();
                    //Creamos un modelo de combobox y le añadimos 3 elementos
                    DefaultComboBoxModel modeloHor = new DefaultComboBoxModel();
                    ResultSet horas = conn.getValues("DISTINCT h.HORA", "hora h INNER JOIN cam_horarios ch ON h.id = ch.ID_HORA", "ID_CAMPO = (SELECT ID FROM campos where CAMPO like '"+model.getValueAt(e.getFirstRow(), 7)+"') AND ASIGNADO = 0 AND ID_DIA = (SELECT ID FROM dias WHERE dia like '"+model.getValueAt(e.getFirstRow(), 3)+"')", "");
                    while (horas.next()){
                        modeloHor.addElement(horas.getString("HORA"));
                    }
                    //Asignamos el modelo al combobox
                    hor.setModel(modeloHor);
                    //Ahora vamos a recoger una columna que será donde insertemos el combobox
                    TableColumn columnaHor = tAplazados.getColumnModel().getColumn(4);
                    //Creamos un nuevo editor de celda. Tambien puede insertarse checkboxs y textfields
                    TableCellEditor editorHor = new DefaultCellEditor(hor);
                    //Le asignamos a la columna el editor creado
                    columnaHor.setCellEditor(editorHor);
                    horas.close();
                    pintarFecha(tAplazados.getSelectedRow(), fechaInicio, fechaFin, tAplazados);
                    break;
                case 4:                    
                    break;
            }
        } catch (SQLException ex) {}finally{
            conn.desconectar();
        }
    }
    
    private void pintarFecha(int r, Date dateIni, Date dateFin, JTable tabla){
        SimpleDateFormat formatterShow = new SimpleDateFormat("yyyy-MM-dd");
            while(dateIni.getTime() <= dateFin.getTime()){
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateIni);
                if(getDia(cal.get(Calendar.DAY_OF_WEEK)-1).equalsIgnoreCase((String)tabla.getValueAt(r, 3))){
                    tabla.setValueAt(formatterShow.format(cal.getTime()), r, 2);
                }
                cal.add(Calendar.DAY_OF_YEAR, 1);
                dateIni = cal.getTime();
            }
    }
    
    private String getDia(int dia){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/guardar.png"));
        int input = JOptionPane.showConfirmDialog(null, "¿Desea añadir el partido aplazado a la jornada?", "Añadir partido aplazado", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {   
            for(int i = 0; i < ActualizarDatosAplazados.tAplazados.getRowCount(); i++){
                if(ActualizarDatosAplazados.tAplazados.getValueAt(i, 2) != null){
                    try {
                        conn.conectar();
                        int campo = 0;
                        ResultSet rcampo = conn.getValues("ID", "campos", "CAMPO like '" + ActualizarDatosAplazados.tAplazados.getValueAt(i, 7) + "'", "");
                        try {
                            while(rcampo.next()){
                                campo = (rcampo.getInt("ID"));
                            }
                        }catch (SQLException ex) {}

                        conn.updateData("campeonato", "FECHA = '"+tAplazados.getValueAt(i, 2)+"', HORA = '"+tAplazados.getValueAt(i, 4)+"', ID_CAMPO = "+ campo +", APLAZADO = 0", "ID = " + tAplazados.getValueAt(i, 0));
                        conn.getConection().commit();

                    }catch (SQLException ex) {
                        Logger.getLogger(ActualizarDatosAplazados.class.getName()).log(Level.SEVERE, null, ex);
                    }finally{
                        conn.desconectar();
                    }
                }
            }
            DefaultTableModel model = (DefaultTableModel) ActualizarDatosAplazados.tAplazados.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                model.removeRow(i);
                i-=1;
            }
            llenarTAplazados(ActualizarDatosAplazados.tAplazados);
        }
    }
    
    private void llenarTAplazados(JTable tAplazados){
        DefaultTableModel model = (DefaultTableModel) tAplazados.getModel();
        Object[] fila = new Object[9];
        conn.conectar();
        String select = "c.ID, c.JORNADA, c.FECHA, c.HORA, l.NOMBRE AS \"LOCAL\", "
                + "v.NOMBRE AS \"VISITANTE\", ca.CAMPO, c.APLAZADO, com.COMPETICION AS \"CATEGORIA\", "
                + "d.DIVISION AS \"DIVISION\"";
        String from = " Campeonato c INNER JOIN Equipos l ON c.ID_LOCAL = l.ID "
                + "INNER JOIN Equipos v ON c.ID_VISITANTE = v.ID "
                + "LEFT JOIN Campos ca ON c.ID_CAMPO = ca.ID INNER JOIN Competicion com ON l.ID_COMPETICION = com.ID "
                + "INNER JOIN Division d ON l.ID_DIVISION = d.ID";
        String order = "c.JORNADA, com.ID, d.ID"; 
        ResultSet campeonato = conn.getValues(select, from, "APLAZADO = 1", order);
            try {
                while(campeonato.next()){
                    fila[0] = campeonato.getInt("ID");
                    fila[1] = campeonato.getInt("JORNADA");
                    ResultSet campos_horas = conn.getValues("DISTINCT ch.ID_CAMPO, h.hora", "cam_horarios ch inner join hora h on ch.ID_HORA = h.ID inner join campos c on ch.ID_CAMPO = c.ID", "c.CONGELADO = 0", "ch.ID_CAMPO LIMIT 2");
                    try{
                        while(campos_horas.next()){
                            
                        }
                    }catch(SQLException ex){}
                    fila[5] = campeonato.getString("LOCAL");
                    fila[6] = campeonato.getString("VISITANTE");
                    fila[7] = "Seleccione un campo";
                    JComboBox< Object > jc2 = new JComboBox<>();
                    DefaultComboBoxModel modelo2 = new DefaultComboBoxModel();

                    ResultSet campos = conn.getValues("DISTINCT c.ID, c.CAMPO", "campos c inner join cam_horarios ch on c.ID = ch.ID_CAMPO", "c.CONGELADO = 0 and ch.ASIGNADO = 0", "c.ID");
                    try {
                        while(campos.next()){
                            modelo2.addElement(campos.getString("CAMPO"));
                        } 
                    }catch (SQLException ex) {}
                    
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
            }catch (SQLException ex) {}
        conn.desconectar();
        tAplazados.setModel(model);
    }
    
}
