package domain.Trayecto;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Punto")
public class Punto extends EntidadPersistente {

    @Expose
    @Column
    private Float latitud;

    @Expose
    @Column
    private Float longitud;

    public Punto(){};

    public Punto(String string){
        String[] aux = string.split(",");
        this.latitud = Float.valueOf(aux[0]);
        this.longitud = Float.valueOf(aux[1]);
    }

    public Punto(Float latitud, Float longitud){
        this.latitud =latitud;
        this.longitud = longitud;
    }

    public float distanciaAPunto(Punto puntoFin){
        float distancia = (float) Math.sqrt((Math.pow(puntoFin.getLongitud() - this.longitud, 2) + Math.pow(puntoFin.getLatitud() - this.latitud, 2)));
        return distancia;
    }

    public boolean coincideCon(Punto punto){
        return this.latitud.equals(punto.getLatitud()) && this.longitud.equals(punto.getLongitud());
    }

    public Float getLatitud() { return latitud;}
    public void setLatitud(Float latitud) { this.latitud = latitud;}
    public Float getLongitud() { return longitud;}
    public void setLongitud(Float longitud) { this.longitud = longitud;}
}
