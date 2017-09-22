package sources;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author santy
 */
public class PintarFilas extends DefaultTableCellRenderer{

    private final ArrayList row;
        
    public PintarFilas(ArrayList row){
        this.row = row;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        component.setBackground(Color.white);
        if(!this.row.isEmpty()){
        for (int l = 0; l < this.row.size(); l++) {
            if(value.equals(this.row.get(l))){
                component.setBackground(Color.red);
            }
        }
        }
        this.row.clear();
        return component;
    }
    
}
