package domain;

import domain.Actividad.*;
import domain.Organizacion.*;
import domain.Trayecto.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import persistencia.EntityManagerHelper;
import persistencia.factories.FactoryRepositorio;
import persistencia.repositorio.Repositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTest {

    @Test
    public void persistirClases() throws Exception {

        Punto ubicOrg = new Punto(1f,1f);
        Organizacion organizacion = new Organizacion("Palmera",TipoOrg.Empresa,ClasificacionOrg.Pyme,ubicOrg);

        Punto ubicMiem = new Punto(2f,2f);
        Miembro miembro = new Miembro("Juan","Perez","DNI",28956475);

        FactorEmision factor = new FactorEmision(4F,Unidad.m3);
        TipoConsumo tconsumo = new TipoConsumo("Gas Natural", Unidad.m3, factor);
        List<TipoConsumo> consumoList = new ArrayList<>();
        consumoList.add(tconsumo);
        Actividad act = new Actividad("Combustion fija", consumoList, Alcance.Directa);

        List<TipoConsumo> consumos = new ArrayList<>();
        FactorEmision feTrayectos = new FactorEmision(2f,Unidad.km);
        TipoConsumo tctrayectos = new TipoConsumo("Distancia media recorrida", Unidad.km, feTrayectos);
        consumos.add(tctrayectos);
        Actividad actTrayectos = new Actividad("Traslado de miembros de la Org", consumos, Alcance.NoControladas);
        organizacion.setActividadTrayectos(actTrayectos);

        Sector sector = new Sector("Gerencia", "sector re piola de gerencia");
        Sector sector2 = new Sector("Otra gerencia", "sector re piola de gerencia");

        SectorTerritorial sectorT = new SectorTerritorial("Patagonico", TipoSectorTerritorial.departamento);
        AgenteSectorial agente = new AgenteSectorial("Roberto",sectorT, "test@gmail.com","022022");

        organizacion.agregarSector(sector);
        organizacion.agregarSector(sector2);
        organizacion.aceptarMiembro(miembro, sector);
        organizacion.setAgente(agente);
        agente.agregarOrganizacion(organizacion);

        Medicion medicion = new Medicion(tconsumo,5F,Periodicidad.Mensual);
        Medicion medicion2 = new Medicion(tconsumo,6F,Periodicidad.Anual);

        DatosActividad datosActividad = new DatosActividad(act, medicion, LocalDate.of(2022,10,6));
        DatosActividad datosActividad2 = new DatosActividad(act, medicion2, LocalDate.of(2022,10,6));
        List<DatosActividad> datosList = new ArrayList<>();
        datosList.add(datosActividad);
        datosList.add(datosActividad2);

        BatchDatosActividad batch = new BatchDatosActividad(datosList, organizacion);

        Parada p1 = new Parada("Constitucion", ubicMiem);
        Parada p2 = new Parada("alem", ubicOrg);
        List<Parada> paradasTren = new ArrayList<>();
        paradasTren.add(p1);
        paradasTren.add(p2);
        TransportePublico tren = new TransportePublico("Tren roca","C", TipoPublico.tren, paradasTren);

        TransporteContratado taxi = new TransporteContratado("Taxi Veloz",TipoContratado.taxi);

        Tramo tramo1 = new Tramo(taxi, p1.getPunto(), ubicOrg);
        Tramo tramo2 = new Tramo(taxi, ubicOrg, p1.getPunto());
        List<Tramo> tramos1 = new ArrayList<>();
        List<Tramo> tramos2 = new ArrayList<>();
        tramos1.add(tramo1);
        tramos2.add(tramo2);
        Trayecto t1 = new Trayecto(tramos1,21);
        Trayecto t2 = new Trayecto(tramos2,21);
        miembro.agregarTrayecto(t1);
        miembro.agregarTrayecto(t2);

        Usuario usuario = new Usuario("AlaPapa","AAAAAAAA",organizacion);

        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.getEntityManager().persist(act);
        EntityManagerHelper.getEntityManager().persist(batch);
        EntityManagerHelper.getEntityManager().persist(tren);
        EntityManagerHelper.getEntityManager().persist(taxi);
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.getEntityManager().persist(actTrayectos);

        EntityManagerHelper.commit();
    }

    @Test
    void recuperarPersistidos(){
        Organizacion org = (Organizacion) EntityManagerHelper.createQuery("from Organizacion where razonSocial = 'Palmera'").getSingleResult();
        Miembro miemb = (Miembro) EntityManagerHelper.createQuery("from Miembro where nroDocumento = 28956475").getSingleResult();
        TransportePublico tren = (TransportePublico) EntityManagerHelper.createQuery("from TransportePublico where tipo = 'tren' and linea = 'C'").getSingleResult();

        Assert.assertEquals("Palmera", org.getRazonSocial());
        Assert.assertEquals(28956475, miemb.getNroDocumento());
        Assert.assertEquals("Tren roca", tren.getNombre());
    }

    @Test
    void persistirYRecuperarUnObjeto(){

        MedioTransporte transporteAPie = new MedioTransporte("A pie");

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(transporteAPie);
        EntityManagerHelper.commit();

        MedioTransporte transporteObtenido = (MedioTransporte) EntityManagerHelper.createQuery("from MedioTransporte where id = " + transporteAPie.getId()).getSingleResult();

        Assert.assertEquals("A pie", transporteObtenido.getNombre());
        Assert.assertFalse(transporteObtenido.usaCombustible());
    }

    @Test
    void otroPersist() throws Exception {
        Punto ubicOrg = new Punto(1f,1f);
        Organizacion organizacion = new Organizacion("Roble",TipoOrg.Empresa,ClasificacionOrg.Pyme,ubicOrg);

        Punto ubicMiem = new Punto(2f,2f);
        Miembro miembro = new Miembro("Jose","Lopez","DNI",28956476);

        Sector sector = new Sector("Gerencia", "sector re piola de gerencia");

        organizacion.agregarSector(sector);
        organizacion.aceptarMiembro(miembro, sector);

        Repositorio<Organizacion> repo = FactoryRepositorio.get(Organizacion.class);
        Organizacion organizacion2 = repo.buscar(1);

        organizacion2.aceptarMiembro(miembro, organizacion2.getSectores().get(0));

        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.getEntityManager().persist(organizacion2);

        EntityManagerHelper.commit();
    }

}
