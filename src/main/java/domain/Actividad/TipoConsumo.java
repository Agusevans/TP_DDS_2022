package domain.Actividad;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "TipoConsumo")
public class TipoConsumo extends EntidadPersistente {

    @Expose
    @Column
    private String nombre;

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private Unidad unidad;

    @Expose
    @OneToOne(cascade = CascadeType.ALL)
    private FactorEmision factorEmision;

    public TipoConsumo(){};

    public TipoConsumo(String nombre, Unidad unidad, FactorEmision fe) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.factorEmision = fe;
    }

    public FactorEmision getFactorEmision(){
        return this.factorEmision;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

}