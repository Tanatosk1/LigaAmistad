/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import java.sql.SQLException;
import connection.Conn;

/**
 *
 * @author rob_a
 */
public class GestionarDivisiones {
    
    private final Conn conn = new Conn();
    
    public GestionarDivisiones(){
        
    }
    
    public void guardarDivision(String division) throws SQLException{

            conn.conectar();
            conn.insertData("division", "null,'" + division + "'" );
            conn.getConection().commit();
            conn.desconectar();
    }
    
    public void editarDivision(String anterior, String nuevo) throws SQLException{
            conn.conectar();
            conn.updateData("division", "DIVISION = '" + nuevo + "'", "DIVISION LIKE '" + anterior+ "'");
            conn.getConection().commit();
            conn.desconectar();
    }
    
    public void eliminarDivision(String division) throws SQLException{
            conn.conectar();
            conn.deleteData("division", "DIVISION like '" + division + "'");
            conn.getConection().commit();
            conn.desconectar();
    }
    public void vaciarDivision() throws SQLException{
            conn.conectar();
            conn.deleteTable("division");
            conn.getConection().commit();
            conn.desconectar();
    }
    
}
