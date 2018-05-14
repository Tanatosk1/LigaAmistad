/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import connection.Conn;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
            JOptionPane.showMessageDialog(null, "Se ha limpiado la tabla", "Información", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException ex){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al vaciar los datos\n"+ex.getMessage(), "Error", JOptionPane.QUESTION_MESSAGE, icon);
        }
    }
}
