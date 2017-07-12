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
public class GestionarCategorias {
    
     private final Conn conn = new Conn();

    
    public GestionarCategorias(){
        
    }
    
    public void guardarCategoria(String categoria){
        try {
            conn.conectar();
            conn.insertData("competicion", "null,'" + categoria + "'" );
            conn.getConection().commit();
            conn.desconectar();
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/aceptar.png"));
            JOptionPane.showMessageDialog(null, "Categoría guardada con éxito", "Información", JOptionPane.QUESTION_MESSAGE, icon);
        } catch (SQLException ex) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al guardar los datos\n"+ex.getMessage(), "Error", JOptionPane.QUESTION_MESSAGE, icon);
        }
    }
    
    public void editarCategoria(String anterior, String nuevo){
        try{
            conn.conectar();
            conn.updateData("competicion", "COMPETICION = '" + nuevo + "'", "COMPETICION LIKE '" + anterior+ "'");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al editar los datos\n"+ex.getMessage(), "Error", JOptionPane.QUESTION_MESSAGE, icon);
        }
    }
    
    public void eliminarCategoria(String categoria){
        try{
            conn.conectar();
            conn.deleteData("competicion", "COMPETICION like '" + categoria + "'");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al borrar los datos\n"+ex.getMessage(), "Error", JOptionPane.QUESTION_MESSAGE, icon);
        }
    }
    
    public void vaciarCategoria(){
        try{
            conn.conectar();
            conn.deleteTable("competicion");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(null, "Error al vaciar los datos\n"+ex.getMessage(), "Error", JOptionPane.QUESTION_MESSAGE, icon);
        }
    }
    
}
