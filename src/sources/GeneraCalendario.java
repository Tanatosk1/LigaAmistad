package sources;

import connection.Conn;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
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
    //private int totalPartidosJornada;
    private int totalCamposDisponibles;
    private int totalPartidosMostrados;
    private int jornada;
    private ResultSet campeonato;
    private ResultSet restricciones;
    private ArrayList<oCampeonato> arrCampeonato;
    private ArrayList<oRestricciones> arrRestricciones;
  
    public void generaFechas(String fInicio, String fFin, JTable tabla, JComboBox jornada){
        Conn conn = new Conn();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        this.jornada = (int) jornada.getSelectedItem();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = fInicio;
        String dateFinString = fFin;
        Calendar cInicio = Calendar.getInstance();
        Calendar cFin = Calendar.getInstance();
        //ArrayList fila = new ArrayList(); 
        
        conn.conectar();
        totalCamposDisponibles = conn.totalRegistros("campos", "CONGELADO = 0");
        
        campeonato = conn.getValues("*", "campeonato", "", "JORNADA");
        restricciones = conn.getValues("*", "restricciones", "", "");
        
        
        
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
        conn.desconectar();
        try {
            totalPartidosMostrados = model.getRowCount();
            Date dateIni = formatter.parse(dateInString);
            Date dateFin = formatter.parse(dateFinString);
            cInicio.setTime(dateIni);
            cFin.setTime(dateFin);
            Calendar setDay = Calendar.getInstance();
            int dia;
            int dias=(int) ((dateFin.getTime()- dateIni.getTime())/86400000) + 1;
            int day;
            for(int i = 0; i < totalPartidosMostrados; i++){
                if(this.jornada == (int)model.getValueAt(i, 1)){
                    
                    String fecha = generaFechaAleatoria(formatter, dateIni, dateFin);
                    tabla.setValueAt(fecha, i, 2);
                    setDay.setTime(formatter.parse(fecha));
                    day = setDay.get(Calendar.DAY_OF_WEEK)-1;
                    tabla.setValueAt(strDays[day], i, 3);
                    
                    for(int e = 0; e < arrCampeonato.size(); e++){
                        
                        //int numDia = 0;
                        int local = 0;
                        int visitante = 0;
                        if((int)model.getValueAt(i, 0) == arrCampeonato.get(e).id){
                            local = arrCampeonato.get(e).local;
                            visitante = arrCampeonato.get(e).visitante;
                        }
                        for(int r = 0; r < arrRestricciones.size(); r++){
                            ArrayList<Date> dateLocal = new ArrayList();
                            if(arrRestricciones.get(r).id_equipo == local){
                                
                                if(arrRestricciones.get(r).id_dia != 0){
                                    boolean rest = true;
                                    
                                   do{
                                       
                                        if(model.getValueAt(i, 3).equals(getDia(arrRestricciones.get(r).id_dia))){
                                            
                                            System.out.println("Fecha anterior " + formatter.format(setDay.getTime()));
                                            System.out.println("Local " + arrRestricciones.get(r).id_equipo);
                                            System.out.println("Día que no puede jugar");
                                            dateLocal.add(setDay.getTime());
                                            /** Genero otra fecha aleatoria **/
                                            fecha = generaFechaAleatoria(formatter, dateIni, dateFin);
                                            setDay.setTime(formatter.parse(fecha));                                            
                                            tabla.setValueAt(fecha, i, 2);
                                            System.out.println("Nueva fecha " + formatter.format(setDay.getTime()));
                                            day = setDay.get(Calendar.DAY_OF_WEEK)-1;
                                            tabla.setValueAt(strDays[day], i, 3);
                                            r = 0;                                       
                                        }else{
                                            rest = false;
                                        }
                                    }while(rest);
                                }
                            }
                            if(arrRestricciones.get(r).id_equipo == visitante){
                                
                                if(arrRestricciones.get(r).id_dia != 0){
                                    boolean rest = true;
                                    
                                    do{
                                        if(model.getValueAt(i, 3).equals(getDia(arrRestricciones.get(r).id_dia))){
                                            
                                            System.out.println("Fecha anterior " + formatter.format(setDay.getTime()));
                                            System.out.println("visitante " + arrRestricciones.get(r).id_equipo);
                                            System.out.println("Día que no puede jugar");
                                            
                                            /** Genero otra fecha aleatoria **/
                                            fecha = generaFechaAleatoria(formatter, dateIni, dateFin);
                                            
                                            if(dateLocal.size() > 0){
                                                for(int d = 0; d < dateLocal.size(); d++){
                                                    if(dateLocal.get(d) == formatter.parse(fecha)){
                                                        fecha = generaFechaAleatoria(formatter, dateIni, dateFin);
                                                        d = 0;
                                                    }
                                                }
                                            }
                                            
                                            setDay.setTime(formatter.parse(fecha));
                                            tabla.setValueAt(fecha, i, 2);
                                            day = setDay.get(Calendar.DAY_OF_WEEK)-1;
                                            tabla.setValueAt(strDays[day], i, 3);
                                            System.out.println("Nueva fecha " + formatter.format(setDay.getTime()));
                                            r = 0;                                       
                                        }else{
                                            rest = false;
                                        }
                                    }while(rest);
                                } 
                            }
                        }
                    }
                    //int campo = ThreadLocalRandom.current().nextInt(totalCamposDisponibles)+1;
                    //System.out.println(campo);
                }
            }
        } catch (ParseException e) {
        }
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
    
    private String generaFechaAleatoria(SimpleDateFormat formatter, Date dateIni, Date dateFin){
        Date randomDate = new Date(ThreadLocalRandom.current().nextLong(dateIni.getTime(), dateFin.getTime()));      
        return formatter.format(randomDate);
    }
}
