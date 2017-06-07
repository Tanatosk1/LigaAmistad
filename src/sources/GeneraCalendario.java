package sources;

import connection.Conn;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import views.Calendario;

/**
 *
 * @author santy
 */
public class GeneraCalendario {
    String[] strDays = new String[]{"Domingo", "Lunes", "Martes", "Miércoles",
        			    "Jueves", "Viernes", "Sábado"};
    int totalPartidosJornada;
    int totalCamposDisponibles;
  
    public void generaFechas(String fInicio, String fFin, JTable tabla, JComboBox jornada){
        Conn conn = new Conn();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Object[] fila = new Object[9];

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = fInicio;
        String dateFinString = fFin;
        Calendar cInicio = Calendar.getInstance();
        Calendar cFin = Calendar.getInstance();
        
        conn.conectar();
        totalPartidosJornada = conn.totalRegistros("campeonato", "JORNADA = " + jornada.getSelectedItem());
        totalCamposDisponibles = conn.totalRegistros("campos", "CONGELADO = 0");
        
        conn.desconectar();
        
        try {
            Date dateIni = formatter.parse(dateInString);
            Date dateFin = formatter.parse(dateFinString);
            cInicio.setTime(dateIni);
            cFin.setTime(dateFin);
            
            for(int i = 0; i < totalPartidosJornada; i++){
                System.out.println(ThreadLocalRandom.current().nextInt(cInicio.get(Calendar.DATE), cFin.get(Calendar.DATE)+1));
            }
            
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
