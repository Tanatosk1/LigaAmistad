package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author santy
 */
public class GeneraCalendario {
           
    private final String[] strDays = new String[]{"Domingo", "Lunes", "Martes", "Miércoles",
        			    "Jueves", "Viernes", "Sábado"};
    private int totalHorariosDisponibles;
    private int partidosPorJornada = 0;
    private int totalPartidosMostrados;
    private int jornada;
    private DefaultTableModel model;
    private ResultSet rs;
    private ArrayList<OPartido> camposDis = new ArrayList();
    private final ArrayList idcampos = new ArrayList();

    public void generaFechas(String fInicio, String fFin, JTable tabla, JComboBox jornada){
        Conn conn = new Conn();
        conn.conectar();
        conn.updateData("cam_horarios", "ASIGNADO = 0");
        try {
            conn.getConection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(MostrarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        model = (DefaultTableModel) tabla.getModel();
        this.jornada = (int) jornada.getSelectedItem();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        String dateInString = fInicio;
        String dateFinString = fFin; 
        
        
        totalHorariosDisponibles = conn.totalRegistros("campos c INNER JOIN cam_horarios ch ON c.ID = ch.ID_CAMPO", "c.CONGELADO = 0");
        rs = conn.getValues("c.ID, c.CAMPO, ch.ID_DIA, ch.ID_HORA, ch.ID as ID_CAM_HORA, h.HORA", "campos c INNER JOIN cam_horarios ch ON c.ID = ch.ID_CAMPO INNER JOIN hora h ON ch.ID_HORA = h.ID", "CONGELADO = 0", "c.ID");
        crearAleatorio(rs);
               
        /** Reparto de campos **/
        totalPartidosMostrados = model.getRowCount();
        partidosPorJornada = 0;
        for(int pa = 0; pa < totalPartidosMostrados; pa++){
            if(this.jornada == (int)model.getValueAt(pa, 1)){
                partidosPorJornada++;
            }
        }
        boolean restriccionLocalCampo;
        boolean restriccionVisitanteCampo;
        boolean restriccionLocalDia;
        boolean restriccionVisitanteDia;
        Date dateIni = null;
        Date dateFin = null;
        ArrayList camposUsados = new ArrayList();
        bucle:
        for(int d = 0; d < totalPartidosMostrados; d++){
            try {
                dateIni = formatter.parse(dateInString);
                dateFin = formatter.parse(dateFinString);
            } catch (ParseException ex) {
                Logger.getLogger(GeneraCalendario.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar setDay = Calendar.getInstance();
            setDay.setTime(dateIni);

            try {
                if(this.jornada == (int)model.getValueAt(d, 1)){
                    if(totalHorariosDisponibles >= partidosPorJornada){
//                        if(camposDis.isLast() | camposDis.isAfterLast()){
//                            camposDis.beforeFirst();
//                        }
                        int f = 0;
//                        System.out.println("Tamaño arraylist campos " + campos.size());
                        cambioCampo:
                        for(int l = 0; l < camposDis.size(); l++){
                        //while(camposDis.next()){
                            restriccionLocalCampo = verificaRestriccionesCampos(d, tabla, this.jornada, (String)tabla.getValueAt(d, 5), camposDis.get(l).getIdCampo());
                            //restriccionLocalCampo = verificaRestriccionesCampos(d, tabla, this.jornada, (String)tabla.getValueAt(d, 5), camposDis.getInt("ID"));
//                            System.out.println("restriccionLocalCampo " + restriccionLocalCampo);
                            if(!restriccionLocalCampo){
                                restriccionVisitanteCampo = verificaRestriccionesCampos(d, tabla, this.jornada, (String)tabla.getValueAt(d, 6), camposDis.get(l).getIdCampo());
//                                restriccionVisitanteCampo = verificaRestriccionesCampos(d, tabla, this.jornada, (String)tabla.getValueAt(d, 6), camposDis.getInt("ID"));
                                if(!restriccionVisitanteCampo){
                                    restriccionLocalDia = verificaRestriccionesDias(d, tabla, this.jornada, (String)tabla.getValueAt(d,5), camposDis.get(l).getIdDia(), camposDis.get(l).getIdHora());
//                                    restriccionLocalDia = verificaRestriccionesDias(d, tabla, this.jornada, (String)tabla.getValueAt(d,5), camposDis.getInt("ID_DIA"), camposDis.getInt("ID_HORA"));
                                    if(!restriccionLocalDia){
                                        restriccionVisitanteDia = verificaRestriccionesDias(d, tabla, this.jornada, (String)tabla.getValueAt(d,6), camposDis.get(l).getIdDia(), camposDis.get(l).getIdHora());
//                                        restriccionVisitanteDia = verificaRestriccionesDias(d, tabla, this.jornada, (String)tabla.getValueAt(d,6), camposDis.getInt("ID_DIA"), camposDis.getInt("ID_HORA"));
                                        if(!restriccionVisitanteDia){
                                            for(int it = 0; it < camposUsados.size(); it++){
                                                if((int)camposUsados.get(it) == camposDis.get(l).getIdCamHora()){
                                                    continue cambioCampo;
                                                }
                                            }
                                            tabla.setValueAt(camposDis.get(l).getCampo(), d, 7);
//                                            tabla.setValueAt(camposDis.getString("CAMPO"), d, 7);
                                            idcampos.add(camposDis.get(l).getIdCampo());
//                                            idcampos.add(camposDis.getInt("ID"));
                                            String diaSemana = getDia(camposDis.get(l).getIdDia());
//                                            String diaSemana = getDia(camposDis.getInt("ID_DIA"));
                                            tabla.setValueAt(diaSemana, d, 3);
                                            pintarFecha(d, dateIni, dateFin, tabla);
                                            tabla.setValueAt(camposDis.get(l).getHora(), d, 4);
//                                            tabla.setValueAt(camposDis.getString("HORA"), d, 4);
                                            camposUsados.add(camposDis.get(l).getIdCamHora());
//                                            camposUsados.add(camposDis.getRow());
                                            conn.conectar();
                                            conn.updateData("cam_horarios", "ASIGNADO = 1", "ID = " + camposDis.get(l).getIdCamHora());
                                            conn.getConection().commit();
                                            continue bucle;
                                        }
                                    }
                                }
                            }
                        }
                    }else{
                        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
                        JOptionPane.showMessageDialog(null, "NO hay suficientes horarios para los partidos mostrados", "Insuficientes horarios", JOptionPane.QUESTION_MESSAGE, icon);
                        break;
                    }
                }
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
            }
        }       
        conn.desconectar();
        /** Fin reparto de campos **/
    }
    
    private void crearAleatorio(ResultSet camposDisponibles){
        try {
            while(camposDisponibles.next()){
                
                camposDis.add(new OPartido(camposDisponibles.getInt("ID"), camposDisponibles.getString("CAMPO"), camposDisponibles.getInt("ID_DIA"),
                camposDisponibles.getInt("ID_HORA"), camposDisponibles.getInt("ID_CAM_HORA"), camposDisponibles.getString("HORA")));
            }
            Collections.shuffle(camposDis);
        } catch (SQLException ex) {
            Logger.getLogger(GeneraCalendario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getDia(int dia){
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
            int aplazado = 0;
            conn.conectar();
            for(int i = 0; i < tabla.getRowCount(); i++){
                if(tabla.getValueAt(i, 2) != null){
                    boolean estado = true;
                    if(tabla.getValueAt(i, 9) == null || (boolean)tabla.getValueAt(i, 9) == false){
                        estado = false;
                    }
                    if(estado){
                        aplazado = 1;
                    }else{
                        aplazado = 0;
                    }
                    
                    if(idcampos.isEmpty()){
                        conn.updateData("campeonato", "APLAZADO = "+aplazado, "ID = "+tabla.getValueAt(i, 0));
                    }else{
                        conn.updateData("campeonato", "fecha = '"+tabla.getValueAt(i, 2)+"', hora = '"+tabla.getValueAt(i, 4)+"', ID_CAMPO = "+idcampos.get(i)+", APLAZADO = "+aplazado, "ID = "+tabla.getValueAt(i, 0));
                    }
                }
            }
            System.out.println("----------------------");
            conn.getConection().commit();
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GeneraCalendario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void pintarFecha(int r, Date dateIni, Date dateFin, JTable tabla){
        SimpleDateFormat formatterShow = new SimpleDateFormat("yyyy-MM-dd");
        if(this.jornada == (int)model.getValueAt(r, 1)){
            while(dateIni.getTime() <= dateFin.getTime()){
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateIni);
                if(getDia(cal.get(Calendar.DAY_OF_WEEK)-1).equalsIgnoreCase((String)tabla.getValueAt(r, 3))){
                    tabla.setValueAt(formatterShow.format(cal.getTime()), r, 2);
                }
                cal.add(Calendar.DAY_OF_YEAR, 1);
                dateIni = cal.getTime();
            }
        }
    }
    
    private boolean verificaRestriccionesCampos(int partido, JTable tabla, int jornada, String local, int campo){
        Conn conn = new Conn();
        ResultSet restricciones;
        
        conn.conectar();
        restricciones = conn.getValues("*", "restricciones r INNER JOIN equipos e ON r.ID_EQUIPO = e.ID", "", "");
        
        try {
            if((int)tabla.getValueAt(partido, 1) == jornada){
                while(restricciones.next()){
                    if(local.equals(restricciones.getString("NOMBRE"))){
                        if(campo == restricciones.getInt("ID_CAMPO")){
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GeneraCalendario.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.desconectar();
        return false;
    }
    
    private boolean verificaRestriccionesDias(int partido, JTable tabla, int jornada, String local, int dias, int hora){
        Conn conn = new Conn();
        ResultSet restricciones;
        conn.conectar();
        restricciones = conn.getValues("*", "restricciones r INNER JOIN equipos e ON r.ID_EQUIPO = e.ID", "", "");
        
        try {
            if((int)tabla.getValueAt(partido, 1) == jornada){
                while(restricciones.next()){
                    if(local.equals(restricciones.getString("NOMBRE"))){
                        if(dias == restricciones.getInt("ID_DIA")){
                            if(hora == restricciones.getInt("HORA")){
                                System.out.println("Equipo "+restricciones.getString("NOMBRE")+" DIA "+restricciones.getInt("ID_DIA")+" Hora "+restricciones.getInt("HORA"));
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GeneraCalendario.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.desconectar();
        return false;
    }
    
//    private boolean verificaRestriccionesHora(int partido, JTable tabla, int jornada, String local, int hora){
//        Conn conn = new Conn();
//        ResultSet restricciones;
//        
//        conn.conectar();
//        restricciones = conn.getValues("*", "restricciones r INNER JOIN equipos e ON r.ID_EQUIPO = e.ID", "", "");
//        
//        try {
//            if((int)tabla.getValueAt(partido, 1) == jornada){
//                while(restricciones.next()){
//                    if(local.equals(restricciones.getString("NOMBRE"))){
//                        if(hora == restricciones.getInt("HORA")){
//                            return true;
//                        }
//                    }
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(GeneraCalendario.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        conn.desconectar();
//        return false;
//    }
}
