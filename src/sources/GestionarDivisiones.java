/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import connection.Conn;
import javax.swing.ImageIcon;

/**
 *
 * @author rob_a
 */
public class GestionarDivisiones {
    
    private final Conn conn = new Conn();
    
    public GestionarDivisiones(){
        
    }
    
    public void guardarDivision(String division){
        try {
            conn.conectar();
            conn.insertData("division", "null,'" + division + "'" );
            conn.getConection().commit();
            conn.desconectar();
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/aceptar.png"));
            JOptionPane.showMessageDialog(null, "División guardada con éxito", "Información", JOptionPane.QUESTION_MESSAGE, icon);
        } catch (SQLException ex) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al guardar los datos\n"+ex.getMessage(), "Error", JOptionPane.QUESTION_MESSAGE, icon);
        }
    }
    
    public void editarDivision(String anterior, String nuevo){
        try{
            conn.conectar();
            conn.updateData("division", "DIVISION = '" + nuevo + "'", "DIVISION LIKE '" + anterior+ "'");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al editar los datos\n"+ex.getMessage(), "Error", JOptionPane.QUESTION_MESSAGE, icon);
        }
    }
    
    public void eliminarDivision(String division){
        try{
            conn.conectar();
            conn.deleteData("division", "DIVISION like '" + division + "'");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al borrar los datos\n"+ex.getMessage(), "Error", JOptionPane.QUESTION_MESSAGE, icon);
        }
    }
    public void vaciarDivision(){
        try{
            conn.conectar();
            conn.deleteTable("division");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al vaciar los datos\n"+ex.getMessage(), "Error", JOptionPane.QUESTION_MESSAGE, icon);
         }
    }
    
}
