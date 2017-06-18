package sources;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Santy
 */
public class ModelTable extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable tabla, Object value, boolean isSelected,
            boolean hasFocus, int row, int column){
        /*if(value == null){
            return this;
        }*/
        Component render = super.getTableCellRendererComponent(tabla, value, isSelected, hasFocus, row, column);
        if(column == 3){
            String dia = (String)value;
            System.out.println(dia);
            if(dia != null && dia.equals("Domingo")){
                render.setBackground(Color.red);
            }else{
                render.setBackground(Color.white);
            }
        }
        return this;
    }
}
