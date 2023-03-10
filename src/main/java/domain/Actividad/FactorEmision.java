package domain.Actividad;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "FactorEmision")
public class FactorEmision extends EntidadPersistente {

    @Expose
    @Column
    private Float valor;

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private Unidad unidad;

    public FactorEmision(){};

    public FactorEmision(Float valor, Unidad unidad) {
        this.valor = valor;
        this.unidad = unidad;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
}
