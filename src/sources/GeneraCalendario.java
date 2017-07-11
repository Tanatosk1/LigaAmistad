package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author santy
 */
public class GeneraCalendario {
    class oCampeonato{
        int id;
        int jornada;
        String fecha;
        String hora;
        int local;
        int visitante;
        int jugado;
        public oCampeonato(Object[] datos){
            this.id = (int) datos[0];
            this.jornada = (int) datos[1];
            this.fecha = (String) datos[2];
            this.hora = (String) datos[3];
            this.local = (int) datos[4];
            this.visitante = (int) datos[5];
            this.jugado = (int) datos[6];
        }
    }
    
    class oRestricciones{
        int id;
        int id_equipo;
        int id_dia;
        int hora;
        int id_campo;
        int id_coindice;
        public oRestricciones(Object[] datos){
            this.id = (int)datos[0];
            this.id_equipo = (int)datos[1];
            this.id_dia = (int)datos[2];
            this.hora = (int)datos[3];
            this.id_campo = (int)datos[4];
            this.id_coindice = (int)datos[5];
        }
    }
       
    private final String[] strDays = new String[]{"Domingo", "Lunes", "Martes", "Miércoles",
        			    "Jueves", "Viernes", "Sábado"};
    private int camposDisponibles;
    private int totalHorariosDisponibles;
    private int partidosPorJornada = 0;
    private int totalPartidosMostrados;
    private int jornada;
    private DefaultTableModel model;
    private ResultSet campeonato;
    private ResultSet restricciones;
    private ResultSet camposDis;
    private ArrayList<oCampeonato> arrCampeonato;
    private ArrayList<oRestricciones> arrRestricciones;
  
    public void generaFechas(String fInicio, String fFin, JTable tabla, JComboBox jornada){
        Conn conn = new Conn();
        model = (DefaultTableModel) tabla.getModel();
        this.jornada = (int) jornada.getSelectedItem();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        String dateInString = fInicio;
        String dateFinString = fFin; 
        
        conn.conectar();
        totalHorariosDisponibles = conn.totalRegistros("campos INNER JOIN cam_horarios ON ID = ID_CAMPO", "CONGELADO = 0");
        campeonato = conn.getValues("*", "campeonato", "", "JORNADA");
        restricciones = conn.getValues("*", "restricciones", "", "");
        camposDis = conn.getValues("*", "campos c INNER JOIN cam_horarios ch ON c.ID = ch.ID_CAMPO INNER JOIN hora h ON ch.ID_HORA = h.ID", "CONGELADO = 0", "c.ID");
        
        try{
            Object[] fCampeonato = new Object[8];
            arrCampeonato = new ArrayList();
            while(campeonato.next()){
                fCampeonato[0] = campeonato.getInt("ID");
                fCampeonato[1] = campeonato.getInt("JORNADA");
                fCampeonato[2] = campeonato.getDate("FECHA");
                fCampeonato[3] = campeonato.getTime("HORA");
                fCampeonato[4] = campeonato.getInt("ID_LOCAL");
                fCampeonato[5] = campeonato.getInt("ID_VISITANTE");
                fCampeonato[6] = campeonato.getInt("ID_CAMPO");
                fCampeonato[7] = campeonato.getBoolean("JUGADO");
                
                arrCampeonato.add(new oCampeonato(fCampeonato));
            }
            Object[] fRestricciones = new Object[6];
            arrRestricciones = new ArrayList();
            while(restricciones.next()){
                fRestricciones[0] = restricciones.getInt("ID");
                fRestricciones[1] = restricciones.getInt("ID_EQUIPO");
                fRestricciones[2] = restricciones.getInt("ID_DIA");
                fRestricciones[3] = restricciones.getInt("HORA");
                fRestricciones[4] = restricciones.getInt("ID_CAMPO");
                fRestricciones[5] = restricciones.getInt("ID_COINCIDE");
                
                arrRestricciones.add(new oRestricciones(fRestricciones));
            }
            
        }   catch (SQLException ex) {
            Logger.getLogger(GeneraCalendario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /** Reparto de campos **/
        totalPartidosMostrados = model.getRowCount();
        partidosPorJornada = 0;
        camposDisponibles = 0;
        for(int pa = 0; pa < totalPartidosMostrados; pa++){
            if(this.jornada == (int)model.getValueAt(pa, 1)){
                partidosPorJornada++;
            }
        }
        boolean jornadaCorrecta = false;
        for(int d = 0; d < totalPartidosMostrados; d++){
            if(this.jornada == (int)model.getValueAt(d, 1)){
                jornadaCorrecta = true;
                if(totalHorariosDisponibles >= partidosPorJornada){
                    try{
                        camposDis.next();
                        tabla.setValueAt(camposDis.getString("CAMPO"), d, 7);
                        tabla.setValueAt(getDia(camposDis.getInt("ID_DIA")), d, 3);
                        tabla.setValueAt(camposDis.getString("HORA"), d, 4);
                    }catch(SQLException ex){
                    }
                }else{
                    jornadaCorrecta = false;
                    JOptionPane.showMessageDialog(null, "NO hay suficientes horarios para los partidos mostrados", "Insuficientes horarios", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
        conn.desconectar();
        /** Fin reparto de campos **/
        
        /** Generación de fechas según campo asignado **/
        if(jornadaCorrecta){
            try{
                Date dateIni = formatter.parse(dateInString);
                System.out.println("formato " + dateIni);
                Date dateFin = formatter.parse(dateFinString);
                Calendar setDay = Calendar.getInstance();
                int dias=(int) ((dateFin.getTime()- dateIni.getTime())/86400000) + 1;


                setDay.setTime(dateIni);
                String dayOfWeek = strDays[setDay.get(Calendar.DAY_OF_WEEK)-1];
                pintarFecha(dayOfWeek, dateIni, tabla);
                for(int d = 0; d < dias-1; d++){  
                    setDay.add(Calendar.DAY_OF_YEAR, 1);
                    String newDay = strDays[setDay.get(Calendar.DAY_OF_WEEK)-1];
                    pintarFecha(newDay, setDay.getTime(), tabla);
                }

            }catch(ParseException ex){
                System.err.println(ex.getCause());
            }
        }
        /** Fin Generación de fechas **/
    }
    
    public String getDia(int dia){
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
    
    public void guardarCalendario(JTable tabla, JComboBox jornada){
        try {
            Conn conn = new Conn();
            model = (DefaultTableModel) tabla.getModel();
            this.jornada = (int) jornada.getSelectedItem();
            
            conn.conectar();
            for(int i = 0; i < tabla.getRowCount(); i++){
                if(model.getValueAt(i, 2) != null){
                    conn.updateData("campeonato", "fecha = '" + tabla.getValueAt(i, 2) + "', hora = '" + tabla.getValueAt(i, 4) +"'", "ID = " + model.getValueAt(i, 0));
                }
            }
            conn.getConection().commit();
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GeneraCalendario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void pintarFecha(String dayOfWeek, Date dateIni, JTable tabla){
            SimpleDateFormat formatterShow = new SimpleDateFormat("yyyy-MM-dd");
            for(int q = 0; q < totalPartidosMostrados; q++){
                if(this.jornada == (int)model.getValueAt(q, 1)){
                    if(dayOfWeek.equalsIgnoreCase(tabla.getValueAt(q,3).toString())){
                        tabla.setValueAt(formatterShow.format(dateIni), q, 2);
                    }
                }
            }
    }
    
    /*private void generaNumerosAleatorios(int valores){
        int i=0, cantidad=valores, rango=valores;
        int arreglo[] = new int[cantidad];

        arreglo[i]=(int)(Math.random()*rango);
        for(i=1; i<cantidad; i++){
            arreglo[i]=(int)(Math.random()*rango);
            for(int j=0; j<i; j++){
                if(arreglo[i]==arreglo[j]){
                    i--;
                }
            }
        }

        for(int k=0; k<cantidad; k++){
            System.out.print((k+1)+".- "+(arreglo[k]+1)+"\n");
        } 
    }*/
    
    /*private String generaFechaAleatoria(SimpleDateFormat formatter, Date dateIni, Date dateFin){
        Date randomDate = new Date(ThreadLocalRandom.current().nextLong(dateIni.getTime(), dateFin.getTime()));      
        return formatter.format(randomDate);
    }*/
}
