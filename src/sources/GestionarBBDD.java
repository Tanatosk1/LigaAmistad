package sources;

import connection.Conn;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santy
 */
public class GestionarBBDD {
    private final Conn conn = new Conn();
    
    public void nuevaTemporada(String nombre){
        //try {
            conn.conectar();
            nombre = "db"+nombre;
            String[] parametros = {"ID int(11) NOT NULL AUTO_INCREMENT,",
                "JORNADA int(10) unsigned NOT NULL,",
                "FECHA date DEFAULT NULL,",
                "HORA time DEFAULT NULL,",
                "ID_LOCAL int(11) NOT NULL,",
                "ID_VISITANTE int(11) NOT NULL,",
                "ID_CAMPO int(11) DEFAULT NULL,",
                "JUGADO tinyint(1) NOT NULL DEFAULT '0',",
                "ID_ARBITRO int(11) DEFAULT NULL,",
                "PRIMARY KEY (`ID`),",
                "KEY `LOCAL` (`ID_LOCAL`),",
                "KEY `VISITANTE` (`ID_VISITANTE`),",
                "KEY `CAMPO` (`ID_CAMPO`),",
                "KEY `ID_ARBITRO` (`ID_ARBITRO`)"};
            conn.createTable(nombre, parametros);
            
            /*String[] parametrosFiltros = {"ADD CONSTRAINT `"+nombre+"_ibfk_3` FOREIGN KEY (`ID_CAMPO`) REFERENCES `campos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,",
                "ADD CONSTRAINT `"+nombre+"_ibfk_5` FOREIGN KEY (`ID_LOCAL`) REFERENCES `equipos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,",
                "ADD CONSTRAINT `"+nombre+"_ibfk_6` FOREIGN KEY (`ID_VISITANTE`) REFERENCES `equipos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,",
                "ADD CONSTRAINT `"+nombre+"_ibfk_7` FOREIGN KEY (`ID_ARBITRO`) REFERENCES `arbitros` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE;"};
            
            
            conn.alterTable(nombre, parametrosFiltros);*/
            //conn.getConection().commit();
            
            
        //} catch (SQLException ex) {
        //    Logger.getLogger(GestionarBBDD.class.getName()).log(Level.SEVERE, null, ex);
        //}finally{
            conn.desconectar();
        //}
    }
}
