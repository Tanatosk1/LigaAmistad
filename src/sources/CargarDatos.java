package sources;

import connection.Conn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static sources.LeerExcel.max;

/**
 *
 * @author Santy
 */
public class CargarDatos {
    
    public void cargarEquipos(File file){
        try {
            //
            // Crea un Arrayist para guardar los datos leidos desde el excel
            //
            List sheetData = new ArrayList();
            FileInputStream fis = null;
            try {
                //
                // Crea un FileInputStream que usaremos para leer el fichero excel
                //
                fis = new FileInputStream(file);
                //
                // Crea un libro de trabajo para el fichero
                //
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                //
                // Obtenemos la primera hoja del fichero excel
                //
                XSSFSheet sheet = workbook.getSheetAt(0);
                //
                // Cuando tenemos el objeto sheet, podemos interatuar con
                // cada fila de la hoja y sobre cada celda de la fila. Guardamos
                // los datos leidos en un ArrayList, así podemos imprimir el
                // contenido del excel en la consola
                //
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    XSSFRow row = (XSSFRow) rows.next();
                    Iterator cells = row.cellIterator();
                    List data = new ArrayList();
                    while (cells.hasNext()) {
                        XSSFCell cell = (XSSFCell) cells.next();
                        data.add(cell);
                    }
                    sheetData.add(data);
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ex) {
                        Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            showExelDataEquipos(sheetData);
        } catch (SQLException ex) {
            Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    private static void showExelDataEquipos(List sheetData) throws SQLException { 
        max = sheetData.size();
        // Iteramos con los datos, extraemos los ID de los equipos y guardamos en la BBDD.
        // Creamos un objeto de la clase Conexión
        Conn conn = new Conn();
        //Establecemos la conexión con la BBDD
        conn.conectar();
        for (int i = 1; i < sheetData.size(); i++) {
            //Creamos un ArrayList para guardar los datos del excel
            List list = (List) sheetData.get(i);
            //Buscamos en la tabla de equipos para obtener el ID de cada equipo, local y visitante
            ResultSet idCompeticion = conn.getValues("ID", "competicion", "COMPETICION like \"" + String.valueOf(list.get(1)).replace("\"", "")+"\"", "");
            ResultSet idDivision = conn.getValues("ID", "division", "DIVISION like \"" + String.valueOf(list.get(2)).replace("\"", "")+"\"", "");
            //Sustituimos en el ArrayList el nombre de la competición por su ID
            while(idCompeticion.next()){
                list.set(1, idCompeticion.getInt("ID"));
            }
            //Sustituimos en el ArrayList el nombre de la división por su ID
            while(idDivision.next()){
                list.set(2, idDivision.getInt("ID"));
            }
            //Enviamos la sentencia SQL para insertar los datos
            conn.insertData("equipos", "ID, NOMBRE, ID_COMPETICION, ID_DIVISION, CONGELADO", "NULL,\""+String.valueOf(list.get(0)).replace("\"", "'")+"\","+list.get(1)+","+list.get(2)+",0");
        }
        conn.getConection().commit();
        conn.desconectar();
    }
    
    public void cargarCampos(File file){
        try {
            //
            // Crea un Arrayist para guardar los datos leidos desde el excel
            //
            List sheetData = new ArrayList();
            FileInputStream fis = null;
            try {
                //
                // Crea un FileInputStream que usaremos para leer el fichero excel
                //
                fis = new FileInputStream(file);
                //
                // Crea un libro de trabajo para el fichero
                //
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                //
                // Obtenemos la primera hoja del fichero excel
                //
                XSSFSheet sheet = workbook.getSheetAt(0);
                //
                // Cuando tenemos el objeto sheet, podemos interatuar con
                // cada fila de la hoja y sobre cada celda de la fila. Guardamos
                // los datos leidos en un ArrayList, así podemos imprimir el
                // contenido del excel en la consola
                //
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    XSSFRow row = (XSSFRow) rows.next();
                    Iterator cells = row.cellIterator();
                    List data = new ArrayList();
                    while (cells.hasNext()) {
                        XSSFCell cell = (XSSFCell) cells.next();
                        data.add(cell);
                    }
                    sheetData.add(data);
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ex) {
                        Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            showExelDataCampos(sheetData);
        } catch (SQLException ex) {
            Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    private static void showExelDataCampos(List sheetData) throws SQLException { 
        max = sheetData.size();
        // Iteramos con los datos, extraemos los ID de los equipos y guardamos en la BBDD.
        // Creamos un objeto de la clase Conexión
        Conn conn = new Conn();
        //Establecemos la conexión con la BBDD
        conn.conectar();
        for (int i = 1; i < sheetData.size(); i++) {
            //Creamos un ArrayList para guardar los datos del excel
            List list = (List) sheetData.get(i);
            //Enviamos la sentencia SQL para insertar los datos
            conn.insertData("campos", "ID, CAMPO, CONGELADO", "NULL,'"+list.get(0)+"',1");
        }
        conn.getConection().commit();
        conn.desconectar();
    }
}
