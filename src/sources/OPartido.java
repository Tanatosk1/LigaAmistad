package sources;

/**
 *
 * @author Santy
 */
public class OPartido {
    private int idCampo;
    private String campo;
    private int idDia;
    private int idHora;
    private int idCamHora;
    private String hora;
    
    public OPartido(int idCampo, String campo, int idDia, int idHora, int idCamHora, String hora){
        this.idCampo = idCampo;
        this.campo = campo;
        this.idDia = idDia;
        this.idHora = idHora;
        this.idCamHora = idCamHora;
        this.hora = hora;
    }

    public int getIdCampo() {
        return idCampo;
    }

    public String getCampo() {
        return campo;
    }

    public int getIdDia() {
        return idDia;
    }

    public int getIdHora() {
        return idHora;
    }

    public int getIdCamHora() {
        return idCamHora;
    }

    public String getHora() {
        return hora;
    }

    public void setIdCampo(int idCampo) {
        this.idCampo = idCampo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public void setIdHora(int idHora) {
        this.idHora = idHora;
    }

    public void setIdCamHora(int idCamHora) {
        this.idCamHora = idCamHora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
