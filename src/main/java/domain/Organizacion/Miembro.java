package domain.Organizacion;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;
import domain.Trayecto.Trayecto;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Miembro")
public class Miembro extends EntidadPersistente {

    @Expose
    @Column
    private String nombre;

    @Expose
    @Column
    private String apellido;

    @Expose
    @Column
    private String tipoDocumento;

    @Expose
    @Column(unique = true)
    private int nroDocumento;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "miembro_organizacion",
            joinColumns = @JoinColumn(name = "miembro_id"),
            inverseJoinColumns = @JoinColumn(name = "organizacion_id")
    )
    private List<Organizacion> organizaciones;

    @Expose
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "miembro_trayecto",
            joinColumns = @JoinColumn(name = "miembro_id"),
            inverseJoinColumns = @JoinColumn(name = "trayecto_id")
    )
    private List<Trayecto> trayectos;

    public Miembro(){
        this.organizaciones = new ArrayList<>();
        this.trayectos = new ArrayList<>();
    };

    public Miembro(String nombre, String apellido, String tipoDocumento, int nroDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.organizaciones = new ArrayList<>();
        this.trayectos = new ArrayList<>();
    }

    public List<Sector> obtenerSectores(){

        List<Sector> sectoresList = new ArrayList<>();
        for (Organizacion organizacion : organizaciones) {
            for (Sector sector : organizacion.getSectores()){
                if (sector.esMiembro(this)){
                    sectoresList.add(sector);
                }
            }
        }

        return sectoresList;
    }

    public void agregarOrganizacion(Organizacion organizacion){
        this.organizaciones.add(organizacion);
    }

    public void removerOrganizacion(Organizacion organizacion){
        this.organizaciones.remove(organizacion);
    }

    public void agregarTrayecto(Trayecto trayecto){
        trayectos.add(trayecto);
        trayecto.sumarIntegrante();
    }

    public List<Trayecto> trayectosDeLaOrg(Organizacion org){
        List<Trayecto> trayectosDeLaOrg = new ArrayList<>();
        for (Trayecto trayecto : trayectos) {
            if (trayecto.perteneceALaOrg(org)){
                trayectosDeLaOrg.add(trayecto);
            }
        }
        return trayectosDeLaOrg;
    }

    public void sacarTrayectosDeOrg(Organizacion org){
        List<Trayecto> trayectosOrg = this.trayectosDeLaOrg(org);
        for (Trayecto trayecto : trayectosOrg) {
            if (trayecto.perteneceALaOrg(org)){
                this.borrarTrayecto(trayecto);
            }
        }
    }

    public void borrarTrayecto(Trayecto trayecto){
        trayectos.remove(trayecto);
        trayecto.restarIntegrante();
    }

    public void borrarTrayectos(){
        for (Trayecto trayecto : trayectos) {
            trayecto.restarIntegrante();
        }
        this.trayectos.clear();
    }

    public boolean contieneElTrayecto(Trayecto trayecto){
        return trayectos.contains(trayecto);
    }

    //getters & setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public int getNroDocumento() {
        return nroDocumento;
    }
    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }
    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }
    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public void setTrayectos(List<Trayecto> trayectos) {
        this.trayectos = trayectos;
        for (Trayecto trayecto : trayectos) {
            trayecto.sumarIntegrante();
        }
    }

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }
}
