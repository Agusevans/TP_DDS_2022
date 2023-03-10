package persistencia.repositorio;

import domain.Organizacion.*;
import persistencia.daos.DAO;
import java.util.List;

public class RepoOrganizacion extends Repositorio<Organizacion> {

    public RepoOrganizacion(DAO<Organizacion> dao) {
        super(dao);
    }

    public void agregarOrg(Organizacion org){
        for (Miembro miembro : org.obtenerMiembros()) {
            miembro.agregarOrganizacion(org);
        }
        this.agregar(org);
    }

    public void actualizarOrg(Organizacion orgVieja, Organizacion orgNueva){
        if(orgNueva.getRazonSocial() != null)
            orgVieja.setRazonSocial(orgNueva.getRazonSocial());
        if(orgNueva.getTipo() != null)
            orgVieja.setTipo(orgNueva.getTipo());
        if(orgNueva.getClasificacion() != null)
            orgVieja.setClasificacion(orgNueva.getClasificacion());

        this.actualizar(orgVieja);
    }

    public void borrarOrg(Organizacion org){
        for (Sector sector : org.getSectores()) {
            for (Miembro miembro : sector.getMiembros()) {
                miembro.removerOrganizacion(org);
            }
            sector.removerMiembros();
        }

        this.borrar(org);
    }

    public void agregarMiembroASector(Miembro miembro, Sector sector, Organizacion organizacion) throws Exception {
        organizacion.aceptarMiembro(miembro, sector);
        this.actualizar(organizacion);
    }

}
