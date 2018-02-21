package sources;

/**
 *
 * @author santy
 */
public class ORestriccion {
    private final Object dia;
    private final Object hora;
    private final Object campo;
    private final Object noCoincidir;
    private final Object idArbitro;
    
    public ORestriccion(Object dia, Object hora, Object campo, Object noCoincidir, Object idArbitro){
        this.dia = dia;
        this.hora = hora;
        this.campo = campo;
        this.noCoincidir = noCoincidir;
        this.idArbitro = idArbitro;
    }
    
    public Object getDia(){
        return this.dia;
    }
    
    public Object getHora(){
        return this.hora;
    }
    
    public Object getCampo(){
        return this.campo;
    }
    
    public Object getNoCoincidir(){
        return this.noCoincidir;
    }
    
    public Object getIdArbitro(){
        return this.idArbitro;
    }
}
