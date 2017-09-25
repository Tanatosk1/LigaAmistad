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
    private final int jornada;
        
    public PintarFilas(ArrayList row, int jornada){
        this.row = row;
        this.jornada = jornada;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        component.setBackground(Color.white);
        
        if(table.getValueAt(row, 1).equals(this.jornada)){
            if(column == 5){
                for(int i = 0; i < this.row.size(); i++){
                    if(value.equals(this.row.get(i))){
                        component.setBackground(Color.red);
                    }
                }
            }else if(column == 6){
                for(int j = 0; j < this.row.size(); j++){
                    if(value.equals(this.row.get(j))){
                        component.setBackground(Color.red);
                    }
                }  
            }
        }
        return component;
    }
    
}
