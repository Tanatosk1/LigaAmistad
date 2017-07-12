package sources;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author santy
 */
public class CrearDocumentos {
    
    public void crearExcel(String ruta, JTable tabla){
    try{    
        String[] headers = {"ID","JORNADA", "FECHA", "DÍA", "HORA", "LOCAL", "VISITANTE", "CAMPO", "COMPETICION"};

        //Creamos un libro excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        //Creamos una hoja de excel
        XSSFSheet sheet = workbook.createSheet("Liga La Amistad");
        //Asignamos tamaño a las columnas
        sheet.setColumnWidth(2, 4096);
        sheet.setColumnWidth(3, 4096);
        sheet.setColumnWidth(5, 10350);
        sheet.setColumnWidth(6, 10350);
        sheet.setColumnWidth(7, 6154);
        sheet.setColumnWidth(8, 8202);
        //XSSFSheet sheet1 = workbook.createSheet("Otra hoja");
        
        //Creamos estilo para los encabezados
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        
        //Creamos estilo para los datos
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        
        // Crea la fila para los encabezados
        XSSFRow headerRow = sheet.createRow(0);
        
        // Crea las celdas y agrega el texto para cada encabezado
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }
        
        //Obtenemos los valores que se muestran en la tabla
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        //Numero de filas
        int filas = model.getRowCount();
        //Numero de columnas
        int columnas = model.getColumnCount();
        //Creamos una matriz para guardar los objetos de la tabla
        Object[][] datos = new Object[filas][columnas];
        //Llenamos la matriz con los datos de la tabla
        for(int fil = 0; fil < filas; fil++){
            for(int col = 0; col < columnas; col++){
                datos[fil][col] = model.getValueAt(fil, col);
            }
        }
        
        //Creamos una fila por cada registro y asignamos los textos a cada celda
        for(int i = 0; i < datos.length; ++i){
            //Se crea una fila
            XSSFRow dataRow = sheet.createRow(i+1);
            
            //Extraemos los datos de la matriz
            Object[] d = datos[i];
            int id = (int) d[0];
            int jornada = (int) d[1];
            String fecha = (String) d[2];
            String dia = (String) d[3];
            String hora = (String) d[4];
            String local = (String) d[5];
            String visitante = (String) d[6];
            String campo = (String) d[7];
            String competicion = (String) d[8];
            
            //Agregamos el texto a cada celda del excel y centramos el contenido
            dataRow.createCell(0).setCellValue(id);
            dataRow.getCell(0).setCellStyle(dataStyle);
            dataRow.createCell(1).setCellValue(jornada);
            dataRow.getCell(1).setCellStyle(dataStyle);
            dataRow.createCell(2).setCellValue(fecha);
            dataRow.getCell(2).setCellStyle(dataStyle);
            dataRow.createCell(3).setCellValue(dia);
            dataRow.getCell(3).setCellStyle(dataStyle);
            dataRow.createCell(4).setCellValue(hora);
            dataRow.getCell(4).setCellStyle(dataStyle);
            dataRow.createCell(5).setCellValue(local);
            dataRow.getCell(5).setCellStyle(dataStyle);
            dataRow.createCell(6).setCellValue(visitante);
            dataRow.getCell(6).setCellStyle(dataStyle);
            dataRow.createCell(7).setCellValue(campo);
            dataRow.getCell(7).setCellStyle(dataStyle);
            dataRow.createCell(8).setCellValue(competicion); 
            dataRow.getCell(8).setCellStyle(dataStyle);
        }

        //Guardamos el fichero
        FileOutputStream fichero = new FileOutputStream(ruta);
        workbook.write(fichero);
        fichero.close();
        // Se crea bien el fichero, muestra mensaje
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/aceptar.png"));
        JOptionPane.showMessageDialog(null, "Fichero creado correctamente", "Exportar Excel", JOptionPane.QUESTION_MESSAGE, icon);
    } catch (IOException | java.lang.ClassCastException e) {
        //Error al crear el fichero, muestra mensaje
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
        JOptionPane.showMessageDialog(null, "No se ha creado el fichero\n" + e.getMessage(), "Exportar Excel", JOptionPane.QUESTION_MESSAGE, icon);
        
    }
    }
}
