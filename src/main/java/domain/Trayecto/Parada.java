package domain.Trayecto;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table
public class Parada extends EntidadPersistente {

    @Expose
    @Column
    private String nombre;

    @Expose
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "punto_id", referencedColumnName = "id")
    private Punto punto;

    public Parada(){};

    public Parada(String nombre, Punto punto){
        this.nombre = nombre;
        this.punto = punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Punto getPunto() { return this.punto; }

    float distanciaA(Parada parada) {
        return parada.getPunto().distanciaAPunto(this.punto);
    }

}
