package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import views.Restricciones;

/**
 *
 * @author santy
 */
public class GestionarEquipos {
    private final Conn conn = new Conn();
    private final Restricciones r;
    
    public GestionarEquipos(Restricciones rest){
        this.r = rest;
    }
    
    public void mostrarRestricciones(){
        try {
            limpiarCampos();
            int idEquipo = r.cbEquipos.getSelectedIndex();
            conn.conectar();
            ResultSet equi = conn.getValues("r.*, d.dia",
                    "restricciones r INNER JOIN dias d ON r.ID_DIA = d.ID",
                    "r.ID_EQUIPO = " + idEquipo, "");
            while(equi.next()){
                switch (equi.getString("dia")){
                    case "Lunes":
                        r.ckLunesEquipos.setSelected(true);
                        r.cbLunes.setSelectedIndex(equi.getInt("ID_HORA"));
                        break;
                    case "Martes":
                        r.ckMartesEquipos.setSelected(true);
                        r.cbMartes.setSelectedIndex(equi.getInt("ID_HORA"));
                        break;
                    case "Miércoles":
                        r.ckMiercolesEquipos.setSelected(true);
                        r.cbMiercoles.setSelectedIndex(equi.getInt("ID_HORA"));
                        break;
                    case "Jueves":
                        r.ckJuevesEquipos.setSelected(true);
                        r.cbJueves.setSelectedIndex(equi.getInt("ID_HORA"));
                        break;
                    case "Viernes":
                        r.ckViernesEquipos.setSelected(true);
                        r.cbViernes.setSelectedIndex(equi.getInt("ID_HORA"));
                        break;
                    case "Sábado":
                        r.ckSabadoEquipos.setSelected(true);
                        r.cbSabado.setSelectedIndex(equi.getInt("ID_HORA"));
                        break;
                    case "Domingo":
                        r.ckDomingoEquipos.setSelected(true);
                        r.cbDomingo.setSelectedIndex(equi.getInt("ID_HORA"));
                        break;
                }
                
            }
            
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionarEquipos.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    private void limpiarCampos(){
        r.ckLunesEquipos.setSelected(false);
        r.ckMartesEquipos.setSelected(false);
        r.ckMiercolesEquipos.setSelected(false);
        r.ckJuevesEquipos.setSelected(false);
        r.ckViernesEquipos.setSelected(false);
        r.ckSabadoEquipos.setSelected(false);
        r.ckDomingoEquipos.setSelected(false);
        r.ckCongelarEquipo.setSelected(false);
        r.cbLunes.setSelectedIndex(0);
        r.cbMartes.setSelectedIndex(0);
        r.cbMiercoles.setSelectedIndex(0);
        r.cbJueves.setSelectedIndex(0);
        r.cbViernes.setSelectedIndex(0);
        r.cbSabado.setSelectedIndex(0);
        r.cbDomingo.setSelectedIndex(0);
        r.cbNoCoincidir.setSelectedIndex(0);
        
    }
}
