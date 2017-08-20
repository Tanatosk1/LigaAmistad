/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import connection.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import views.Restricciones;

/**
 *
 * @author rob_a
 */
public class GestionarCampeonato {
    private final Conn conn = new Conn(); 
    
    public GestionarCampeonato (){
    }
    
    public void vaciarCampeonato(){
        try{
            conn.conectar();
            conn.deleteTable("campeonato");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al vaciar los datos\n"+ex.getMessage(), "Error", JOptionPane.QUESTION_MESSAGE, icon);
        }
    }
    
    public void actualizarAplazados (int id, String fecha, String hora, String campo) throws SQLException{
        conn.conectar();
        ResultSet rcampo = conn.getValues("ID", "campos", "CAMPO = '" + campo + "'", "");
            try {
                while(rcampo.next()){
                   campo=(rcampo.getString("ID")); 
                } 
            }catch (SQLException ex) {
                Logger.getLogger(Restricciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        int scampo = Integer.parseInt(campo);
        System.out.println("ID CAMPO " +scampo );
        conn.updateData("campeonato", "FECHA = '" + fecha + "', HORA = '" + hora + "', ID_CAMPO = '" + scampo + "'",  "ID = '" + id + "'");
        conn.getConection().commit();
        conn.desconectar();
    
    }
}
