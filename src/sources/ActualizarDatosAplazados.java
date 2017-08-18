package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
public class ActualizarDatosAplazados implements TableModelListener{
    
    private final JTable tAplazados;
    private final Conn conn = new Conn();
    
    public ActualizarDatosAplazados(JTable table){
        this.tAplazados = table;
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
                    break;
                case 4:
                    System.out.println(e.getColumn());
                    break;
        }
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarDatosAplazados.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.desconectar();
    }
    
}
