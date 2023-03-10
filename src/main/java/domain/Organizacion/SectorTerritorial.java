package domain.Organizacion;


import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "SectorTerritorial")
public class SectorTerritorial extends EntidadPersistente {

    @Expose
    @Column
    private String nombre;

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private TipoSectorTerritorial tipoSectorTerritorial;

    public SectorTerritorial(){};

    public SectorTerritorial(String nombre, TipoSectorTerritorial tipoSectorTerritorial) {
        this.nombre = nombre;
        this.tipoSectorTerritorial = tipoSectorTerritorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoSectorTerritorial getTipoSectorTerritorial() {
        return tipoSectorTerritorial;
    }

    public void setTipoSectorTerritorial(TipoSectorTerritorial tipoSectorTerritorial) {
        this.tipoSectorTerritorial = tipoSectorTerritorial;
    }
}
