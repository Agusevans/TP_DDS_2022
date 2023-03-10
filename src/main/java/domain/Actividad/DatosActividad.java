package domain.Actividad;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DatosActividad")
public class DatosActividad implements Medible {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Expose
    @ManyToOne
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    private Actividad actividad;

    @Expose
    @OneToOne(cascade = CascadeType.ALL)
    private Medicion medicion;

    @Expose
    @Column
    private LocalDate periodoDeImputacion;

    public DatosActividad(){};

    public DatosActividad(Actividad actividad, Medicion medicion, LocalDate periodoDeImputacion) {
        this.actividad = actividad;
        this.periodoDeImputacion = periodoDeImputacion;
        this.medicion = medicion;
    }

    @Override
    public Float calcularHU()
    {
        return this.medicion.calcularHUMedicion();
    }

    @Override
    public String getUnidad() {
        return this.medicion.getTipoConsumo().getUnidad().name();
    }

    @Override
    public Float getValor() {
        return this.medicion.getValor();
    }

    @Override
    public String getCategoria() {
        return this.medicion.getTipoConsumo().getNombre();
    }

    public int getId() { return id;}

    public void setId(int id) {
        this.id = id;
    }

    public void setMedicion(Medicion medicion) {
        this.medicion = medicion;
    }

    public LocalDate getPeriodoDeImputacion() {
        return periodoDeImputacion;
    }

    public void setPeriodoDeImputacion(LocalDate periodoDeImputacion) {
        this.periodoDeImputacion = periodoDeImputacion;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Medicion getMedicion() {
        return medicion;
    }
}