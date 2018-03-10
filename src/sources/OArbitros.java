package sources;

/**
 *
 * @author Santy
 */
public class OArbitros {
    public int id;
    public String nombre;
    public String apellidos;
    public int lunes;
    public int martes;
    public int miercoles;
    public int jueves;
    public int viernes;
    public int sabado;
    public int domingo;
    public int nivel;
    public int noCoincidir;
    
    public OArbitros(int id, String nombre, String apellidos, int lunes, int martes, int miercoles,
            int jueves, int viernes, int sabado, int domingo, int nivel, int noCoincidir){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
        this.nivel = nivel;
        this.noCoincidir = noCoincidir;
    }
    
    public void setLunes(int lunes){
        this.lunes = lunes;
    }
    
    public void setMartes(int martes){
        this.martes = martes;
    }
    
    public void setMiercoles(int miercoles){
        this.miercoles = miercoles;
    }
    
    public void setJueves(int jueves){
        this.jueves = jueves;
    }
    
    public void setViernes(int viernes){
        this.viernes = viernes;
    }
    
    public void setSabado(int sabado){
        this.sabado = sabado;
    }
    
    public void setDomingo(int domingo){
        this.domingo = domingo;
    }
}
