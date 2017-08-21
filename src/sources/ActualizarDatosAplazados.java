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
    
    private JTable tAplazados;
    private final Conn conn = new Conn();
    private MostrarDatos md;
    private Date fechaInicio;
    private Date fechaFin;
    
    public ActualizarDatosAplazados(){

    }
    
    public ActualizarDatosAplazados(JTable table, Date fInicio, Date fFin){
        this.tAplazados = table;
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
                    ResultSet horas = conn.getValues("DISTINCT h.HORA", "hora h INNER JOIN cam_horarios ch ON h.id = ch.ID_HORA", "ID_CAMPO = (SELECT ID FROM campos where CAMPO like '"+model.getValueAt(e.getFirstRow(), 7)+"') AND ASIGNADO = 0", "");
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
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarDatosAplazados.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
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
            DefaultTableModel model = (DefaultTableModel) tAplazados.getModel();
            int[] rows = tAplazados.getSelectedRows();
            int filsel = tAplazados.getSelectedRow();
            if(filsel == -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila","Seleccione una  fila de la tabla", JOptionPane.QUESTION_MESSAGE, icon);
            }else{
//                try {
//                    for(int i=0;i<rows.length;i++){
//                        int id = (int) model.getValueAt(filsel, 0);
////                        String fecha = (String) model.getValueAt(filsel, 2);
//                        //String fecha = "2017-08-28";
//                        String hora = (String) model.getValueAt(filsel, 4);
//                        String campo = (String) model.getValueAt(filsel, 7);
//                        
////                        System.out.println(id);
////                        System.out.println(fecha);
////                        System.out.println(hora);
////                        System.out.println(campo);
//                        //gc.actualizarAplazados (id, fecha, hora, campo);
//                        
//                        conn.conectar();
//                        ResultSet rcampo = conn.getValues("ID", "campos", "CAMPO = '" + campo + "'", "");
//                        try {
//                            while(rcampo.next()){
//                               campo=(rcampo.getString("ID")); 
//                            } 
//                        }catch (SQLException ex) {
//                            //Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    int scampo = Integer.parseInt(campo);
//                    System.out.println("ID CAMPO " +scampo );
//                    conn.updateData("campeonato", "FECHA = '" + fecha + "', HORA = '" + hora + "', ID_CAMPO = '" + scampo + "'",  "ID = '" + id + "'");
//                    conn.getConection().commit();
//                    conn.desconectar();
//                    }
//                } catch (NumberFormatException | SQLException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
    
}
