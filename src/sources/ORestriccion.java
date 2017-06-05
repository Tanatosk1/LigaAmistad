package sources;

/**
 *
 * @author santy
 */
public class ORestriccion {
    private final int dia;
    private final int hora;
    private final int campo;
    private final int noCoincidir;
    
    public ORestriccion(int dia, int hora, int campo, int noCoincidir){
        this.dia = dia;
        this.hora = hora;
        this.campo = campo;
        this.noCoincidir = noCoincidir;
    }
    
    public int getDia(){
        return this.dia;
    }
    
    public int getHora(){
        return this.hora;
    }
    
    public int getCampo(){
        return this.campo;
    }
    
    public int getNoCoincidir(){
        return this.noCoincidir;
    }
}
