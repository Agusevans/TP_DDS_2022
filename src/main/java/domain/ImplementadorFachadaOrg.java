package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.FachadaOrg;
import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.Organizacion.Organizacion;

import java.util.Collection;
import java.util.Map;

public class ImplementadorFachadaOrg implements FachadaOrg {

    Organizacion organizacion;

    public ImplementadorFachadaOrg(Organizacion org) {
        this.organizacion = org;
    }

    @Override
    public void cargarParametros(Map<String, Float> parametrosSistema) {
        //asumo (actividad/tipoConsumo, FE), pero al sacarlos del JSON ya no se usa
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    @Override
    public Float obtenerHU(Collection<Medible> mediciones) {
        return organizacion.obtenerHUMedible(mediciones);
    }
}
