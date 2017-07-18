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
     
    public void guardarCategoria(String categoria) throws SQLException{

            conn.conectar();
            conn.insertData("competicion", "null,'" + categoria + "'" );
            conn.getConection().commit();
            conn.desconectar();
    }
    
    public void editarCategoria(String anterior, String nuevo) throws SQLException{
            conn.conectar();
            conn.updateData("competicion", "COMPETICION = '" + nuevo + "'", "COMPETICION LIKE '" + anterior+ "'");
            conn.getConection().commit();
            conn.desconectar();
    }
    
    public void eliminarCategoria(String categoria) throws SQLException{
            conn.conectar();
            conn.deleteData("competicion", "COMPETICION like '" + categoria + "'");
            conn.getConection().commit();
            conn.desconectar();
    }
    
    public void vaciarCategoria() throws SQLException{
            conn.conectar();
            conn.deleteTable("competicion");
            conn.getConection().commit();
            conn.desconectar();
    }
    
}
