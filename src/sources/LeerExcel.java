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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author santy
 */

public class LeerExcel implements Runnable{
    public static int max;
    public static boolean terminar;
    private final File file;
    private static JTable tCalendario;
    private static JComboBox cJornada;
    private static JComboBox cGeneraJornada;
    
    public LeerExcel(File file, JTable tCalendario, JComboBox cJornadas, JComboBox cGeneraJornada) {               
        this.file = file;
        LeerExcel.tCalendario = tCalendario;
        LeerExcel.cJornada = cJornadas;
        LeerExcel.cGeneraJornada = cGeneraJornada;
        terminar  = false;
        max = 0;
    }
    
    @Override
    public void run() {
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
            showExelData(sheetData);
        } catch (SQLException ex) {
            Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    @SuppressWarnings("empty-statement")
    private static void showExelData(List sheetData) throws SQLException { 
        max = sheetData.size();
        new Thread(new jcThread(max)).start();
        //
        // Iteramos con los datos, extraemos los ID de los equipos y guardamos en la BBDD.
        //
        // Creamos un objeto de la clase Conexión
        Conn conn = new Conn();
        //Establecemos la conexión con la BBDD
        conn.conectar();
        for (int i = 1; i < sheetData.size(); i++) {
            //Creamos un ArrayList para guardar los datos del excel
            List list = (List) sheetData.get(i);
            //Buscamos en la tabla de equipos para obtener el ID de cada equipo, local y visitante
            ResultSet idEquipoLocal = conn.getValues("ID", "Equipos", "NOMBRE like \"" + String.valueOf(list.get(4)).replace("\"", "'")+"\"", "");
            ResultSet idEquipoVisitante = conn.getValues("ID", "Equipos", "NOMBRE like \"" + String.valueOf(list.get(6)).replace("\"", "'")+"\"", "");
            //Sustituimos en el ArrayList el nombre del equipo por su ID
            while(idEquipoLocal.next()){
                list.set(4, idEquipoLocal.getInt("ID"));
            }
            while(idEquipoVisitante.next()){
                list.set(6, idEquipoVisitante.getInt("ID"));
            }
            //Convertimos el dato de la jornada de DOUBLE a INT, ya que de excel viene como DOUBLE
            Cell cell = (Cell) list.get(1);
            int jornada = 0;
            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                jornada = (int)cell.getNumericCellValue();
            }
            //Enviamos la sentencia SQL para insertar los datos
            conn.insertData("Campeonato", "ID, JORNADA, ID_LOCAL, ID_VISITANTE", "NULL,"+jornada+","+list.get(4)+","+list.get(6));  
            if(LeerExcel.terminar){
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

        //Si no hay problema realizamos commit a la BBDD para guardar los datos
        if(!LeerExcel.terminar){
            conn.getConection().commit();
        }else{
        //Si se encuentran problemas realizamos un rollbak para no guardar nada en la BDD
            conn.getConection().rollback();
        }
        //Desconectamos de la BBDD
        conn.desconectar();
        //Si no hay problemas, mostramos mensaje
        if(!LeerExcel.terminar){
            JOptionPane.showMessageDialog(null, "Datos importados con exito");
            MostrarDatos md = new MostrarDatos();
            md.llenarTCalendario(tCalendario);
            md.llenarComboJornadas(cJornada);
            md.llenarComboJornadas(cGeneraJornada);
        }
    }
}