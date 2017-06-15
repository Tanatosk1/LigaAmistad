package sources;

import connection.Conn;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author santy
 */
public class GeneraCalendario {
    String[] strDays = new String[]{"Domingo", "Lunes", "Martes", "Miércoles",
        			    "Jueves", "Viernes", "Sábado"};
    int totalPartidosJornada;
    int totalCamposDisponibles;
    int totalPartidosMostrados;
    int jornada;
  
    public void generaFechas(String fInicio, String fFin, JTable tabla, JComboBox jornada){
        Conn conn = new Conn();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        //Object[] fila = new Object[10];
        this.jornada = (int) jornada.getSelectedItem();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = fInicio;
        String dateFinString = fFin;
        Calendar cInicio = Calendar.getInstance();
        Calendar cFin = Calendar.getInstance();
        
        conn.conectar();
        totalPartidosJornada = conn.totalRegistros("campeonato", "JORNADA = " + jornada.getSelectedItem());
        totalCamposDisponibles = conn.totalRegistros("campos", "CONGELADO = 0");
        
        
        conn.desconectar();
        try {
            totalPartidosMostrados = model.getRowCount();
            Date dateIni = formatter.parse(dateInString);
            Date dateFin = formatter.parse(dateFinString);
            cInicio.setTime(dateIni);
            cFin.setTime(dateFin);
            Calendar setDay = Calendar.getInstance();
            int dias=(int) ((dateFin.getTime()- dateIni.getTime())/86400000) + 1;
            
            for(int i = 0; i < totalPartidosMostrados; i++){
                if(this.jornada == (int)model.getValueAt(i, 1)){
                    int dia = ThreadLocalRandom.current().nextInt(cInicio.get(Calendar.DATE), cFin.get(Calendar.DATE)+1);
                    int campo = ThreadLocalRandom.current().nextInt(totalCamposDisponibles)+1;
                    for(int j = 0; j < dias; j++){
                        if(dia == cInicio.get(Calendar.DATE)+j){
                            setDay.set(cInicio.get(Calendar.YEAR), cInicio.get(Calendar.MONTH),dia);
                            tabla.setValueAt(formatter.format(setDay.getTime()), i, 2);
                            tabla.setValueAt(strDays[setDay.get(Calendar.DAY_OF_WEEK)-1], i, 3);
                            tabla.setValueAt(campo, i, 7);

                        }
                    }
                }
            }
        } catch (ParseException e) {}
    }
    
    public void guardarCalendario(JTable tabla, JComboBox jornada){
        try {
            Conn conn = new Conn();
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            this.jornada = (int) jornada.getSelectedItem();
            
            conn.conectar();
            for(int i = 0; i < tabla.getRowCount(); i++){
                if(model.getValueAt(i, 2) != null){
                    conn.updateData("campeonato", "fecha = '" + model.getValueAt(i, 2) + "'", "ID = " + model.getValueAt(i, 0));
                }
            }
            conn.getConection().commit();
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GeneraCalendario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
