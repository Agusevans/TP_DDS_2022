package domain.Organizacion;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;

@Table
@Entity
public class Usuario extends EntidadPersistente {

    @Expose
    @Column(unique = true)
    private String nombreUsuario;

    @Expose
    @Column
    private String contrasenia;

    @Expose
    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    private Organizacion organizacion;

    public Usuario(){};

    public Usuario(String nombreUsuario, String contrasenia, Organizacion organizacion) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.organizacion = organizacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }
}
