package domain.Actividad;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Actividad")
public class Actividad extends EntidadPersistente {

    @Expose
    @Column(unique = true)
    private String nombre;

    @Expose
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "actividad_id")
    private List<TipoConsumo> tiposConsumo;

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private Alcance alcance;

    public Actividad(){
        this.tiposConsumo = new ArrayList<>();
    };

    public Actividad(String nombre, List<TipoConsumo> tiposConsumo, Alcance alcance){
        this.nombre = nombre;
        this.tiposConsumo = tiposConsumo;
        this.alcance = alcance;
    }

    public TipoConsumo getTipoConsumo(String nombre){
        String nombreUpper = nombre.toUpperCase();
        return this.tiposConsumo.stream().filter(tipoConsumo ->
                tipoConsumo.getNombre().toUpperCase().equals(nombreUpper)).findFirst().get();
    }

    public void setAlcance(Alcance alcance) {
        this.alcance = alcance;
    }

    public void setTiposConsumo(List<TipoConsumo> tiposConsumo) {
        this.tiposConsumo = tiposConsumo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public List<TipoConsumo> getTiposConsumo() {
        return tiposConsumo;
    }
}
