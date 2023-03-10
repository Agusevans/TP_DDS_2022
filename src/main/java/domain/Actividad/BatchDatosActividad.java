package domain.Actividad;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;
import domain.Organizacion.Organizacion;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BatchDatosActividad")
public class BatchDatosActividad extends EntidadPersistente {

    @Expose
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "batch_id")
    private List<DatosActividad> datosAct;

    @Expose
    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    private Organizacion organizacion;

    public BatchDatosActividad(){
        this.datosAct = new ArrayList<>();
    };

    public BatchDatosActividad(List<DatosActividad> datos, Organizacion org){
        this.datosAct = datos;
        this.organizacion = org;
    }

    public List<DatosActividad> getDatosAct() {
        return datosAct;
    }

    public void setDatosAct(List<DatosActividad> datosAct) {
        this.datosAct = datosAct;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

}
