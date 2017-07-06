/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import connection.Conn;
import java.sql.SQLException;
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
            JOptionPane.showMessageDialog(null, "Categoría guardada con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void editarCategoria(String anterior, String nuevo){
        try{
            conn.conectar();
            conn.updateData("competicion", "COMPETICION = '" + nuevo + "'", "COMPETICION LIKE '" + anterior+ "'");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al editar los datos\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarCategoria(String categoria){
        try{
            conn.conectar();
            conn.deleteData("competicion", "COMPETICION like '" + categoria + "'");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al borrar los datos\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void vaciarCategoria(){
        try{
            conn.conectar();
            conn.deleteTable("competicion");
            conn.getConection().commit();
            conn.desconectar();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al vaciar los datos\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
