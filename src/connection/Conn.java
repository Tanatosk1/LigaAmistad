package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sources.LeerExcel;

/**
 *
 * @author santy
 */
public class Conn {
    
    private final String db = "liga";
    private final String url = "jdbc:mysql://localhost/" + db + "?useSSL=false";
    private final String user = "root";
    private final String pass = "root";
    private static Connection link = null;
    
    public Conn(){
    }
    
    public Connection getConection(){
        return Conn.link;
    }
    
    /**
     * Proceso para conectar con la BBDD
     */
    public void conectar(){   
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // Establecemos la conexión con la base de datos.
            this.link = DriverManager.getConnection (this.url,this.user, this.pass);
            this.link.setAutoCommit(false);
            //System.out.println("Conexión establecida");
        } catch (ClassNotFoundException e){
            System.out.println(e);
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Proceso para desconectar de la BBDD
     */    
    public void desconectar(){
        try{
            this.link.close();
            //System.out.println("Conexion cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Proceso para insertar datos en la BBDD
     * @param table. Nombre de la tabla en la que se insertaran los datos
     * @param campos Campos de la tabla que se insertarán
     * @param values Valores que se insertarán
     */
    public void insertData(String table, String campos, String values) {
        try {
            String Query = "INSERT INTO " + table + " (" + campos + ") VALUES(" + values +")";
            Statement st = link.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
            LeerExcel.terminar = true;
            JOptionPane.showMessageDialog(null, "Verfica que la tabla equipo este actualizada" );
        }
    }
    
    /**
     * Proceso para insertar datos en la BBDD
     * @param table. Tabla en la que se insertarán los datos
     * @param values Valores que se insertarán en la tabla
     */
    public void insertData(String table, String values) {
        try {
            String Query = "INSERT INTO " + table + " VALUES(" + values +")";
            Statement st = link.createStatement();
            st.executeUpdate(Query);
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos\n" + ex);
        }
    }
    
    /**
     * Proceso para realizar consultas a la BBDD
     * @param select Datos a buscar en la consulta
     * @param from Tabla en la que realizar la consulta
     * @param where Condición de la búsqueda. Puede ser un String vacío
     * @param order Ordenación de los resultados de la búsqueda. Puede ser un String vacío
     * @return ResulSet con los datos de la consulta.
     */
    public ResultSet getValues(String select, String from, String where, String order) {
        try {
            String Query = "SELECT " + select + " FROM " + from;
            if(!where.equals("")){
                Query += " WHERE " + where;
            }
            if(!order.equals("")){
                Query += " ORDER BY " + order;
            }
            Statement st = link.createStatement();
            ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            return resultSet;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en la adquisición de datos\n"+ex);
        }
        return null;
    }
    
    /**
     * Proceso para actualizar los datos en la BBDD
     * @param table Tabla donde se realizará la actualización
     * @param set Datos a actualizar
     * @param where Condición para la actualización
     */
    public void updateData(String table, String set, String where){
        try {
            String Query = "UPDATE " + table + " SET " + set +" WHERE " + where;
            Statement st = link.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos editados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos\n"+ex);
            
        }
    }
    
    /**
     * Proceso para eliminar datos de la BBDD
     * @param table Tabla donde se realizará la eliminación
     * @param where Condición para eliminar un registro
     */
    public void deleteData(String table, String where){
        try{
            String query = "DELETE FROM " + table + " WHERE " + where;
            Statement st = link.createStatement();
            st.executeUpdate(query);
            //JOptionPane.showMessageDialog(null, "Datos eliminados de forma exitosa");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos\n"+ex);
        }
    }
}
