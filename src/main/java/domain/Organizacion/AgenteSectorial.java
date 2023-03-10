package domain.Organizacion;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "AgenteSectorial")
public class AgenteSectorial extends EntidadPersistente {

    @Expose
    @Column
    String nombre;

    @Expose
    @OneToOne(cascade = CascadeType.ALL)
    SectorTerritorial sectorTerritorial;

    @OneToMany(mappedBy = "agente", fetch = FetchType.EAGER)
    List<Organizacion> organizaciones;

    @Expose
    @Column
    String email;

    @Expose
    @Column
    String periodo;

    public AgenteSectorial(){
        this.organizaciones = new ArrayList<>();
    }

    public AgenteSectorial(String nombre, SectorTerritorial sectorTerritorial, String email, String periodo) {
        this.nombre = nombre;
        this.sectorTerritorial = sectorTerritorial;
        this.organizaciones = new ArrayList<>();
        this.email = email;
        this.periodo = periodo;
    }

    public void agregarOrganizaciones(Organizacion ... organizaciones){
        Collections.addAll(this.organizaciones, organizaciones);
        for (Organizacion org : organizaciones) {
            org.setAgente(this);
        }
    }

    public void quitarOrganizacion(Organizacion organizacion){
        this.organizaciones.remove(organizacion);
        organizacion.setAgente(null);
    }

    public void obtenerHCTerritorial() throws IOException {
        for (Organizacion org : organizaciones) {
            org.notificarAgente();
        }
    };

    public void agregarOrganizacion(Organizacion organizacion){
        this.organizaciones.add(organizacion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SectorTerritorial getSectorTerritorial() {
        return sectorTerritorial;
    }

    public void setSectorTerritorial(SectorTerritorial tipoTerritorio) {
        this.sectorTerritorial = tipoTerritorio;
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
