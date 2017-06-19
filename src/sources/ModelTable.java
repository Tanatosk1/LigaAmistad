package sources;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Santy
 */
public class ModelTable extends DefaultTableCellRenderer{
    ArrayList fila;
    
    public ModelTable(ArrayList fila){
        this.fila = fila;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable tabla, Object value, boolean isSelected,
            boolean hasFocus, int row, int column){
       
        if(value == null){
            return this;
        }
        Component render = super.getTableCellRendererComponent(tabla, value, isSelected, hasFocus, row, column);
        for(int i = 0; i < fila.size(); i++){
            if(row == (int)fila.get(i)){
                render.setBackground(Color.red);
            }else{
                render.setBackground(Color.white);
            }
        }
        return this;
    }
}
