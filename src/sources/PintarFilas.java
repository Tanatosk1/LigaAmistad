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
        System.out.println("PintarFilas");
        this.row = row;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        for (Object row1 : this.row) {
            System.out.println(row1);
            if(table.getValueAt(row, 5).equals(row1)){
                component.setBackground(Color.red);
            }
            if(table.getValueAt(row, 6).equals(row1)){
                component.setBackground(Color.red);
            }
        }        
        return component;
    }
    
}
