package sources;

import connection.Conn;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        //Object[] fila = new Object[10];
        this.jornada = (int) jornada.getSelectedItem();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = fInicio;
        String dateFinString = fFin;
        Calendar cInicio = Calendar.getInstance();
        Calendar cFin = Calendar.getInstance();
        ArrayList fila = new ArrayList(); 
        
        conn.conectar();
        //totalPartidosJornada = conn.totalRegistros("campeonato", "JORNADA = " + jornada.getSelectedItem());
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
            //System.out.println(arrCampeonato.get(1).jornada);
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
            //System.out.println(arrRestricciones.size());
            
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
            int dias=(int) ((dateFin.getTime()- dateIni.getTime())/86400000) + 1;
            int day = 0;
            for(int i = 0; i < totalPartidosMostrados; i++){
                if(this.jornada == (int)model.getValueAt(i, 1)){
                    int dia = ThreadLocalRandom.current().nextInt(cInicio.get(Calendar.DATE), cFin.get(Calendar.DATE)+1); 
                    for(int j = 0; j < dias; j++){
                        if(dia == cInicio.get(Calendar.DATE)+j){
                            setDay.set(cInicio.get(Calendar.YEAR), cInicio.get(Calendar.MONTH),dia);
                            tabla.setValueAt(formatter.format(setDay.getTime()), i, 2);
                            day = setDay.get(Calendar.DAY_OF_WEEK)-1;
                            tabla.setValueAt(strDays[day], i, 3);
                            //tabla.setValueAt(campo, i, 7);
                        }
                    }
                    for(int e = 0; e < arrCampeonato.size(); e++){
                        int numDia = 0;
                        int local = 0;
                        int visitante = 0;
                        if((int)model.getValueAt(i, 0) == arrCampeonato.get(e).id){
                            local = arrCampeonato.get(e).local;
                            visitante = arrCampeonato.get(e).visitante;
                        }
                        for(int r = 0; r < arrRestricciones.size(); r++){
                            if(arrRestricciones.get(r).id_equipo == local){
                                
                                if(arrRestricciones.get(r).id_dia != 0){
                                    //System.out.println("local " + arrRestricciones.get(r).id_equipo);
                                    //System.out.println(arrRestricciones.get(r).id_dia);
                                    tabla.setRowSelectionInterval(i, i);
                                    tabla.setSelectionBackground(Color.red);
                                }
                            }
                            if(arrRestricciones.get(r).id_equipo == visitante){
                                
                                if(arrRestricciones.get(r).id_dia != 0){
                                    
                                    //System.out.println(getDia(arrRestricciones.get(r).id_dia));
                                    if(model.getValueAt(i, 3).equals(getDia(arrRestricciones.get(r).id_dia))){
                                        //System.out.println("visitante " + arrRestricciones.get(r).id_equipo);
                                        //System.out.println("Día que no puede jugar");
                                        //JOptionPane.showMessageDialog(null, "El equipo " + arrRestricciones.get(r).id_equipo + "\nNo puede jugar ese día", "Restricción encontrada", JOptionPane.ERROR_MESSAGE);
                                        tabla.setRowSelectionInterval(i, i);
                                        tabla.setSelectionBackground(Color.red);                                       
                                    }
                                } 
                            }
                        }
                    }
                    int campo = ThreadLocalRandom.current().nextInt(totalCamposDisponibles)+1;
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
}
