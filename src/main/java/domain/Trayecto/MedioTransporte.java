package domain.Trayecto;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;
import domain.Trayecto.Distancia.Distancia;
import domain.Trayecto.Distancia.DistanciaAPI;

import javax.persistence.*;
import java.io.IOException;

@Table
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MedioTransporte extends EntidadPersistente {

    @Expose
    @Column
    public String nombre;

    @Expose
    @Column
    public boolean usaCombustible;

    public MedioTransporte(){
        this.usaCombustible = false;
    };

    public MedioTransporte(String nombre){
        this.nombre = nombre;
        this.usaCombustible = false;
    }

    public float calcularDistancia(Punto puntoInicio, Punto puntoFin) throws IOException {
        Distancia distancia = DistanciaAPI.getInstancia().distancia(1, "a", 1, 1, "a", 1);
        return Float.parseFloat(distancia.valor);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean usaCombustible(){
        return this.usaCombustible;
    }

}
