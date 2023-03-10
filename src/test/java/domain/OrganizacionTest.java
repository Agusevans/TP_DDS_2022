package domain;

import domain.Organizacion.*;
import domain.Trayecto.Punto;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OrganizacionTest {

    //Tests de la parte "organizativa/organigrama (miembro-sector-org)" de una Organizacion

    Organizacion orgEmpresa;
    Organizacion orgONG;
    Organizacion orgSinMiembros;
    Sector dev;
    Sector callcenter;
    Sector sectorSinMiembros;
    Miembro pablo;
    Miembro jose;

    @BeforeEach
    public void init() {
        pablo = new Miembro("Pablo", "Gomez", "DNI", 12345678);
        jose = new Miembro("Jose", "Paz", "DNI", 87654321);
        dev = new Sector("Dev", "Desarrollo apps");
        dev.agregarMiembro(pablo);
        dev.agregarMiembro(jose);

        callcenter = new Sector("Callcenter", "Llamadas");
        callcenter.agregarMiembro(pablo);

        orgEmpresa = new Organizacion("pepito s.a.", TipoOrg.Empresa,ClasificacionOrg.Multinacional, new Punto(1.0f,2.0f));
        orgEmpresa.agregarSector(dev);

        orgONG = new Organizacion("ONG", TipoOrg.ONG,ClasificacionOrg.Multinacional, new Punto(2f,5f));
        orgONG.agregarSector(callcenter);

        orgSinMiembros = new Organizacion("org sin miembros", TipoOrg.Empresa,ClasificacionOrg.Multinacional, new Punto(4f,-5f));
        sectorSinMiembros = new Sector("Sector vacio", "por asignar miembros");
        orgSinMiembros.agregarSector(sectorSinMiembros);

        pablo.agregarOrganizacion(orgEmpresa);
        pablo.agregarOrganizacion(orgONG);
        jose.agregarOrganizacion(orgEmpresa);
    }

    //Testeo de funcion obtenerMiembros()
    @Test
    void obtenerMiembrosDeUnaOrganizacion() {
        List<Miembro> miembros = new ArrayList<>();
        miembros.add(pablo);
        miembros.add(jose);
        Assert.assertEquals(miembros, orgEmpresa.obtenerMiembros());
    }

    //Testeo de funcion obtenerSectores()
    @Test
    void obtenerSectoresDeMiembro(){
        List<Sector> sectores = new ArrayList<>();
        sectores.add(dev);
        sectores.add(callcenter);
        Assert.assertEquals(sectores, pablo.obtenerSectores());
    }

}