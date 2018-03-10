package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import views.Restricciones;

/**
 *
 * @author Santy
 */
public class GestionarArbitros {
    private final Conn conn = new Conn();
    private Restricciones res = null;
    private ResultSet rs;
    private ResultSet rsNivel;
    private ArrayList<OArbitros> arbitros;
    
    public GestionarArbitros(Restricciones res){
        this.res = res;
    }
    
    public GestionarArbitros(){
        this.arbitros = new ArrayList<>();
    }
    
    public void guardarArbitro( String nombre, String apellido, int nivel ) throws SQLException{
        
            conn.conectar();

            conn.insertData("arbitros", "null,'" + nombre + "', '" + apellido + "','" + nivel + "', 0");
            conn.getConection().commit();
            conn.desconectar();      
    }
    
    public void editarArbitro(String[] arbitro, String nombre, String apellido, int nivel) throws SQLException{
            conn.conectar();
            if(arbitro.length > 1){
                conn.updateData("arbitros", "NOMBRE = '" + nombre + "', APELLIDOS = '" +apellido + "',  NIVEL = '" + nivel + "'",  "NOMBRE LIKE '" + arbitro[0]+ "' and APELLIDOS like '"+ arbitro[1] +"'");
            }else{
                conn.updateData("arbitros", "NOMBRE = '" + nombre + "', APELLIDOS = '" +apellido + "',  NIVEL = '" + nivel + "'",  "NOMBRE LIKE '" + arbitro[0]+ "'");
            }
            conn.getConection().commit();
            conn.desconectar();
        
    }
        
    public void eliminarArbitro(String nombre, String apellido) throws SQLException{

            conn.conectar();
            conn.deleteData("arbitros", "NOMBRE like '" + nombre + "' and APELLIDOS like '" + apellido + "'");
            conn.getConection().commit();
            conn.desconectar();

    }
        
    public void vaciarArbitros() throws SQLException{
            conn.conectar();
            conn.deleteTable("arbitros");
            conn.getConection().commit();
            conn.desconectar();

    }
    
    public void congelarArbitro(int idArbitro, boolean estado) throws SQLException{
            conn.conectar();
            conn.updateData("arbitros", "congelado = " + estado, "ID = " + idArbitro);
            conn.getConection().commit();
            conn.desconectar();

    }
    
    public boolean getNivel(String nombre, String apellido) throws SQLException{
        boolean nivel = false;
        conn.conectar();
        ResultSet rsNivel = conn.getValues("nivel", "arbitros", "NOMBRE like '"+nombre+"' and APELLIDOS like '"+apellido+"' LIMIT 1", "");
        while(rsNivel.next()){
            if(rsNivel.getInt("NIVEL") == 1){
                nivel = true;
                return nivel;
            }
        }
        conn.desconectar();
        return nivel;
    }
    
    public void gestionarArbitro(){
        try {
            int id = res.cbArbitros.getSelectedIndex();
            conn.conectar();
            conn.updateData("arbitros", "LUNES = " + res.ckLunesArbitro.isSelected() +
                                        ", MARTES = " + res.ckMartesArbitro.isSelected() +
                                        ", MIERCOLES = " + res.ckMiercolesArbitro.isSelected() +
                                        ", JUEVES = " + res.ckJuevesArbitro.isSelected() +
                                        ", VIERNES = " + res.ckViernesArbitro.isSelected() +
                                        ", SABADO = " + res.ckSabadoArbitro.isSelected() + 
                                        ", DOMINGO = " + res.ckDomingoArbitro.isSelected() +
                                        ", IDCAMPO = " + res.cbNoCoincidirArbitro.getSelectedIndex(), "ID = " + id);
            conn.getConection().commit();
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionarArbitros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarDatos(){
        try {
            limpiarSeleccion();
            int idArbitro = res.cbArbitros.getSelectedIndex();
            conn.conectar();
            ResultSet arb = conn.getValues("*", "arbitros", "ID = "+idArbitro, "");
            while(arb.next()){
                if(arb.getInt("LUNES") == 1){
                    res.ckLunesArbitro.setSelected(true);
                }
                if(arb.getInt("MARTES") == 1){
                    res.ckMartesArbitro.setSelected(true);
                }
                if(arb.getInt("MIERCOLES") == 1){
                    res.ckMiercolesArbitro.setSelected(true);
                }
                if(arb.getInt("JUEVES") == 1){
                    res.ckJuevesArbitro.setSelected(true);
                }
                if(arb.getInt("VIERNES") == 1){
                    res.ckViernesArbitro.setSelected(true);
                }
                if(arb.getInt("SABADO") == 1){
                    res.ckSabadoArbitro.setSelected(true);
                }
                if(arb.getInt("DOMINGO") == 1){
                    res.ckDomingoArbitro.setSelected(true);
                }
                res.cbNoCoincidirArbitro.setSelectedIndex(arb.getInt("IDCAMPO"));
            }
            arb.close();
            ResultSet frozen = conn.getValues("CONGELADO", "arbitros", "ID = " + idArbitro,"");
            while (frozen.next()){
                if(frozen.getInt("CONGELADO")==1){
                    res.ckCongelarArbitro.setSelected(true);
                }
            }
            frozen.close();
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionarCampos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void asignarArbitros(JTable tabla, JComboBox jornada){
        String dia;
        String nombre;
        int id = 0;
        conn.conectar();
        rsNivel = conn.getValues("*", "arbitros", "CONGELADO = 0 AND NIVEL = 1", "");
        crearAleatorio(rsNivel);
        nuevaFila:
        for(int t = 0; t < tabla.getRowCount(); t++){
            if(tabla.getValueAt(t, 11) != null){
                if((boolean)tabla.getValueAt(t, 11) == true){                    
                    if(tabla.getValueAt(t, 5) == null){
                        for(int arN = 0; arN < arbitros.size(); arN++){
                            nombre = arbitros.get(arN).nombre + " " + arbitros.get(arN).apellidos;
                            if(arbitros.get(arN).lunes == 1){
                                if(tabla.getValueAt(t, 3).equals("Lunes")){
                                    try {
                                        tabla.setValueAt(nombre, t, 5);
                                        arbitros.get(arN).setLunes(0);
                                        mismoCampo(tabla, t, tabla.getValueAt(t, 6).toString(), tabla.getValueAt(t, 3).toString(), arbitros.get(arN).id, nombre);
                                        conn.updateData("campeonato", "ID_ARBITRO = " + arbitros.get(arN).id, "ID = " + tabla.getValueAt(t, 0));
                                        conn.updateData("arbitros", "LUNES = 0", "ID = " + arbitros.get(arN).id);
                                        conn.getConection().commit();  
                                        continue nuevaFila;
                                    } catch (SQLException ex) {
                                        Logger.getLogger(GestionarArbitros.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            if(arbitros.get(arN).martes == 1){
                                if(tabla.getValueAt(t, 3).equals(("Martes"))){
                                    try {
                                        tabla.setValueAt(nombre, t, 5);
                                        arbitros.get(arN).setMartes(0);
                                        mismoCampo(tabla, t, tabla.getValueAt(t, 6).toString(), tabla.getValueAt(t, 3).toString(), arbitros.get(arN).id, nombre);
                                        conn.updateData("campeonato", "ID_ARBITRO = " + arbitros.get(arN).id, "ID = " + tabla.getValueAt(t, 0));
                                        conn.updateData("arbitros", "MARTES = 0", "ID = " + arbitros.get(arN).id);
                                        conn.getConection().commit();
                                        continue nuevaFila;
                                    } catch (SQLException ex) {
                                        Logger.getLogger(GestionarArbitros.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            if(arbitros.get(arN).miercoles == 1){
                                if(tabla.getValueAt(t, 3).equals(("Miércoles"))){
                                    try {
                                        tabla.setValueAt(nombre, t, 5);
                                        arbitros.get(arN).setMiercoles(0);
                                        mismoCampo(tabla, t, tabla.getValueAt(t, 6).toString(), tabla.getValueAt(t, 3).toString(), arbitros.get(arN).id, nombre);
                                        conn.updateData("campeonato", "ID_ARBITRO = " + arbitros.get(arN).id, "ID = " + tabla.getValueAt(t, 0));
                                        conn.updateData("arbitros", "MIERCOLES = 0", "ID = " + arbitros.get(arN).id);
                                        conn.getConection().commit();
                                        continue nuevaFila;
                                    } catch (SQLException ex) {
                                        Logger.getLogger(GestionarArbitros.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            if(arbitros.get(arN).jueves == 1){
                                if(tabla.getValueAt(t, 3).equals(("Jueves"))){
                                    try {
                                        tabla.setValueAt(nombre, t, 5);
                                        arbitros.get(arN).setJueves(0);
                                        mismoCampo(tabla, t, tabla.getValueAt(t, 6).toString(), tabla.getValueAt(t, 3).toString(), arbitros.get(arN).id, nombre);
                                        conn.updateData("campeonato", "ID_ARBITRO = " + arbitros.get(arN).id, "ID = " + tabla.getValueAt(t, 0));
                                        conn.updateData("arbitros", "JUEVES = 0", "ID = " + arbitros.get(arN).id);
                                        conn.getConection().commit();
                                        continue nuevaFila;
                                    } catch (SQLException ex) {
                                        Logger.getLogger(GestionarArbitros.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        arbitros.clear();
        rs = conn.getValues("*", "arbitros", "CONGELADO = 0", "");
        crearAleatorio(rs);
        
        
        //int nDias = 0;
        for(int i = 0; i < arbitros.size(); i++){
            nombre = arbitros.get(i).nombre + " " + arbitros.get(i).apellidos;
            id = arbitros.get(i).id;
            if(arbitros.get(i).lunes == 1){
                dia = "Lunes";
                buscarPartido(tabla, jornada, dia, nombre, id, i);
                //nDias++;
            }
            if(arbitros.get(i).martes == 1){
                dia = "Martes";
                buscarPartido(tabla, jornada, dia, nombre, id, i);
                //nDias++;
            }
            if(arbitros.get(i).miercoles == 1){
                dia = "Miércoles";
                buscarPartido(tabla, jornada, dia, nombre, id, i);
                //nDias++;
            }
            if(arbitros.get(i).jueves == 1){
                dia = "Jueves";
                buscarPartido(tabla, jornada, dia, nombre, id, i);
                //nDias++;
            }
            if(arbitros.get(i).viernes == 1){
                dia = "Viernes";
                buscarPartido(tabla, jornada, dia, nombre, id, i);
                //nDias++;
            }
            if(arbitros.get(i).sabado == 1){
                dia = "Sábado";
                buscarPartido(tabla, jornada, dia, nombre, id, i);
                //nDias++;
            }
            if(arbitros.get(i).domingo == 1){
                dia = "Domingo";
                buscarPartido(tabla, jornada, dia, nombre, id, i);
                //nDias++;
            }
        }

        arbitros.clear();

        conn.desconectar();       
    }
    
    private void mismoCampo(JTable tabla, int fila, String campo, String dia, int idArbitro, String nombre){
        for(int mc = fila; mc < tabla.getRowCount(); mc++){
            if(tabla.getValueAt(mc, 6).equals(campo)){
                if(tabla.getValueAt(mc, 3).equals(dia)){
                    tabla.setValueAt(nombre, mc, 5);
                    //conn.updateData("campeonato", "ID_ARBITRO = " + idArbitro, "ID = " + tabla.getValueAt(mc, 0));
                }
            }
        }
    }
    
    private void buscarPartido(JTable tabla, JComboBox jornada, String dia, String nombre, int id, int indice){
        try{
        
        /** Asignamos el resto de arbitros **/
        for(int i = 0; i < tabla.getRowCount(); i++){    
            if(jornada.getSelectedItem() == tabla.getValueAt(i, 1)){
                if(tabla.getValueAt(i, 3) != null){
                    if(tabla.getValueAt(i, 3).equals(dia)){
                        if(tabla.getValueAt(i, 5) != null){
                            continue;
                        }else{
                            if(verificarCampo(indice, tabla, i)){
                                continue;
                            }else{
                                if(verificarEquipos(indice, tabla, i)){
                                    continue;
                                }else{
                                    tabla.setValueAt(nombre.trim(), i, 5);
                                    conn.updateData("campeonato", "ID_ARBITRO = " + id, "ID = " + tabla.getValueAt(i, 0));
                                    conn.getConection().commit();
                                }
                            }
                        }
                        String campo = tabla.getValueAt(i, 6).toString();
                        for(int j = i; j < tabla.getRowCount(); j++){
                            if(jornada.getSelectedItem() == tabla.getValueAt(j, 1)){
                                if(tabla.getValueAt(j, 6) == null){
                                    continue;
                                }else if(tabla.getValueAt(j, 6).equals(campo)){
                                    if(tabla.getValueAt(j, 3).equals(dia)){
                                        tabla.setValueAt(nombre.trim(), j, 5);
                                        conn.updateData("campeonato", "ID_ARBITRO = " + id, "ID = " + tabla.getValueAt(j, 0));
                                        conn.getConection().commit();
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        }catch(SQLException e){}
    }
    
    private boolean verificarCampo(int indice, JTable tabla, int fila){
        ResultSet rsCampos = conn.getValues("id", "campos", "CAMPO like \""+tabla.getValueAt(fila, 6)+"\"", "");
        try {
            while (rsCampos.next()){
                if(arbitros.get(indice).noCoincidir == rsCampos.getInt("ID")){
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionarArbitros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private boolean verificarEquipos(int indice, JTable tabla, int fila){
        try {
            ResultSet rsEquiposLocal = conn.getValues("ID", "equipos", "NOMBRE like \"" + tabla.getValueAt(fila, 7) + "\" LIMIT 1", "");
            ResultSet rsEquipoVisitante = conn.getValues("ID", "equipos", "NOMBRE like \"" + tabla.getValueAt(fila, 8) + "\" LIMIT 1", "");
            int idLocal = 0;
            int idVisitante = 0;
            while(rsEquiposLocal.next()){
                idLocal = rsEquiposLocal.getInt("ID");
            }            
            while(rsEquipoVisitante.next()){
                idVisitante = rsEquipoVisitante.getInt("ID");
            }
            
            ResultSet rsRestriccion = conn.getValues("ID_ARBITRO", "restricciones", "ID_EQUIPO = "+idLocal+" or ID_EQUIPO = "+idVisitante, "");
            while(rsRestriccion.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionarArbitros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private void crearAleatorio(ResultSet arbitrosDisponibles){
        try {
            while(arbitrosDisponibles.next()){
                arbitros.add(new OArbitros(arbitrosDisponibles.getInt("ID"),
                                           arbitrosDisponibles.getString("NOMBRE"),
                                           arbitrosDisponibles.getString("APELLIDOS"),
                                           arbitrosDisponibles.getInt("LUNES"),
                                           arbitrosDisponibles.getInt("MARTES"),
                                           arbitrosDisponibles.getInt("MIERCOLES"),
                                           arbitrosDisponibles.getInt("JUEVES"),
                                           arbitrosDisponibles.getInt("VIERNES"),
                                           arbitrosDisponibles.getInt("SABADO"),
                                           arbitrosDisponibles.getInt("DOMINGO"),
                                           arbitrosDisponibles.getInt("NIVEL"),
                                           arbitrosDisponibles.getInt("IDCAMPO")));
            }
            Collections.shuffle(arbitros);
        } catch (SQLException ex) {
            Logger.getLogger(GestionarArbitros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void contarHorarios(JLabel lblTotal){
        conn.conectar();
        int total = conn.totalRegistros("(SELECT LUNES dia from arbitros WHERE LUNES = 1 AND CONGELADO = 0 UNION ALL select MARTES dia from arbitros WHERE MARTES = 1 AND CONGELADO = 0 UNION ALL select MIERCOLES dia from arbitros WHERE MIERCOLES = 1 AND CONGELADO = 0 UNION ALL select JUEVES dia from arbitros WHERE JUEVES = 1 AND CONGELADO = 0 UNION ALL select VIERNES dia from arbitros WHERE VIERNES = 1 AND CONGELADO = 0 UNION ALL select SABADO dia from arbitros WHERE SABADO = 1 AND CONGELADO = 0 UNION ALL select DOMINGO dia from arbitros WHERE DOMINGO = 1 AND CONGELADO = 0) T");
        lblTotal.setText(String.valueOf(total));
        conn.desconectar();
    }
    
    private void limpiarSeleccion(){
        res.ckLunesArbitro.setSelected(false);
        res.ckMartesArbitro.setSelected(false);
        res.ckMiercolesArbitro.setSelected(false);
        res.ckJuevesArbitro.setSelected(false);
        res.ckViernesArbitro.setSelected(false);
        res.ckSabadoArbitro.setSelected(false);
        res.ckDomingoArbitro.setSelected(false);
        res.ckCongelarArbitro.setSelected(false);
        res.cbNoCoincidirArbitro.setSelectedIndex(0);
    }
    
}
