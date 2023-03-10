package domain.Actividad;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "Medicion")
public class Medicion extends EntidadPersistente {

    @Expose
    @ManyToOne
    @JoinColumn(name = "tipoConsumo_id", referencedColumnName = "id")
    private TipoConsumo tipoConsumo;

    @Expose
    @Column
    private Float valor;

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private Periodicidad periodicidad;

    public Medicion(){};

    public Medicion(TipoConsumo tipoConsumo, Float valor, Periodicidad periodicidad) {
        this.tipoConsumo = tipoConsumo;
        this.valor = valor;
        this.periodicidad = periodicidad;
    }

    public Float calcularHUMedicion()
    {
        FactorEmision fe = tipoConsumo.getFactorEmision();
        return  valor * fe.getValor();
    }

    public TipoConsumo getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(TipoConsumo tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }
}
