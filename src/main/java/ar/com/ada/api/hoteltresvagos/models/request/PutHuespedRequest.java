package ar.com.ada.api.hoteltresvagos.models.request;

public class PutHuespedRequest {

    private String nombre;

    private String domicilio;

    private String domicilioAlternativo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDomicilioAlternativo() {
        return domicilioAlternativo;
    }

    public void setDomicilioAlternativo(String domicilioAlternativo) {
        this.domicilioAlternativo = domicilioAlternativo;
    }

    
}