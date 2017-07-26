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
                "ID_ARBITRO int(11) DEFAULT NULL"};
            conn.createTable(nombre, parametros);
            
            String[] parametrosKeys = {"ADD PRIMARY KEY('ID'), ",
                                "ADD KEY 'LOCAL' ('ID_LOCAL'), ",
                                "ADD KEY 'VISITANTE' ('ID_VISITANTE'), ",
                                "ADD KEY 'CAMPO' ('ID_CAMPO'), ",
                                "ADD KEY 'ID_ARBITRO' ('ID_ARBITRO')"};
            
            
            conn.alterTable(nombre, parametrosKeys);
            
            String[] parametrosFiltros = {"ADD CONSTRAINT 'campeonato_ibfk_4' FOREIGN KEY ('ID_ARBITRO') REFERENCES 'arbitros' ('ID'), ",
                                    "ADD CONSTRAINT 'campeonato_ibfk_1' FOREIGN KEY ('ID_LOCAL') REFERENCES 'equipos' ('ID'), ",
                                    "ADD CONSTRAINT 'campeonato_ibfk_2' FOREIGN KEY ('ID_VISITANTE') REFERENCES 'equipos' ('ID'), ",
                                    "ADD CONSTRAINT 'campeonato_ibfk_3' FOREIGN KEY ('ID_CAMPO') REFERENCES 'campos' ('ID'), ",};
            
            conn.alterTable(nombre, parametrosFiltros);
            //conn.getConection().commit();
            
            
        //} catch (SQLException ex) {
        //    Logger.getLogger(GestionarBBDD.class.getName()).log(Level.SEVERE, null, ex);
        //}finally{
            conn.desconectar();
        //}
    }
}
