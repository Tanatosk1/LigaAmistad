package sources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author santy
 */
public class GeneraCalendario {
    String[] strDays = new String[]{"Domingo", "Lunes", "Martes", "Miércoles",
        			    "Jueves", "Viernes", "Sábado"};
    public void generaFechas(String fInicio, String fFin, JTable tabla){
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Object[] fila = new Object[9];
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = fInicio;
        Calendar cInicio = Calendar.getInstance();
        
        try {
            Date date = formatter.parse(dateInString);
            cInicio.setTime(date);
            cInicio.add(Calendar.DATE, 1);
            for(int i = 0; i < 14; i++){
                tabla.setValueAt(formatter.format(cInicio.getTime()), i, 2);
                tabla.setValueAt(strDays[cInicio.get(Calendar.DAY_OF_WEEK)-1], i, 3);
            }
            cInicio.add(Calendar.DATE, 1);
            for(int i = 14; i < 28; i++){
                tabla.setValueAt(formatter.format(cInicio.getTime()), i, 2);
                tabla.setValueAt(strDays[cInicio.get(Calendar.DAY_OF_WEEK)-1], i, 3);
            }
            cInicio.add(Calendar.DATE, 1);
            for(int i = 28; i < 41; i++){
                tabla.setValueAt(formatter.format(cInicio.getTime()), i, 2);
                tabla.setValueAt(strDays[cInicio.get(Calendar.DAY_OF_WEEK)-1], i, 3);
            }
            

        } catch (ParseException e) {
         
        ImageIcon icon = new ImageIcon("src/resources/warning.png");
        JOptionPane.showMessageDialog(null, "Introduce la fecha de inicio y fin del campeonato","Error en fechas", JOptionPane.QUESTION_MESSAGE, icon);
  
        }
    }
}
