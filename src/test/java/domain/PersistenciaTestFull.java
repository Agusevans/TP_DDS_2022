package domain;

import domain.Actividad.*;
import domain.Lectores.LectorCSV;
import domain.Organizacion.*;
import domain.Trayecto.*;
import org.junit.jupiter.api.Test;
import persistencia.EntityManagerHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersistenciaTestFull {

    @Test
    public void persistirClases() throws Exception {

        //Miembros: 3 * 3 * 3
        Miembro miembro1 = new Miembro("miembro1","apellido1","DNI",28956475);
        Miembro miembro2 = new Miembro("miembro2","apellido2","DNI",28956476);
        Miembro miembro3 = new Miembro("miembro3","apellido3","DNI",28956477);
        Miembro miembro4 = new Miembro("miembro4","apellido4","DNI",28956478);
        Miembro miembro5 = new Miembro("miembro5","apellido5","DNI",28956479);
        Miembro miembro6 = new Miembro("miembro6","apellido6","DNI",28956480);
        Miembro miembro7 = new Miembro("miembro7","apellido7","DNI",28956481);
        Miembro miembro8 = new Miembro("miembro8","apellido8","DNI",28956482);
        Miembro miembro9 = new Miembro("miembro9","apellido9","DNI",28956483);

        Miembro miembro10 = new Miembro("miembro10","apellido10","DNI",28956484);
        Miembro miembro11 = new Miembro("miembro11","apellido11","DNI",28956485);
        Miembro miembro12 = new Miembro("miembro12","apellido12","DNI",28956486);
        Miembro miembro13 = new Miembro("miembro13","apellido13","DNI",28956487);
        Miembro miembro14 = new Miembro("miembro14","apellido14","DNI",28956488);
        Miembro miembro15 = new Miembro("miembro15","apellido15","DNI",28956489);
        Miembro miembro16 = new Miembro("miembro16","apellido16","DNI",28956490);
        Miembro miembro17 = new Miembro("miembro17","apellido17","DNI",28956491);
        Miembro miembro18 = new Miembro("miembro18","apellido18","DNI",28956492);

        Miembro miembro19 = new Miembro("miembro19","apellido19","DNI",28956493);
        Miembro miembro20 = new Miembro("miembro20","apellido20","DNI",28956494);
        Miembro miembro21 = new Miembro("miembro21","apellido21","DNI",28956495);
        Miembro miembro22 = new Miembro("miembro22","apellido22","DNI",28956496);
        Miembro miembro23 = new Miembro("miembro23","apellido23","DNI",28956497);
        Miembro miembro24 = new Miembro("miembro24","apellido24","DNI",28956498);
        Miembro miembro25 = new Miembro("miembro25","apellido25","DNI",28956499);
        Miembro miembro26 = new Miembro("miembro26","apellido26","DNI",28956500);
        Miembro miembro27 = new Miembro("miembro27","apellido27","DNI",28956501);

        //Sectores: 3 * 3
        Sector sector1 = new Sector("Gerencia", "sector re piola de gerencia");
        Sector sector2 = new Sector("RRHH", "sector re piola");
        Sector sector3 = new Sector("Ventas", "sector re piola");

        Sector sector4 = new Sector("Gerencia", "sector re piola");
        Sector sector5 = new Sector("RRHH", "sector re piola");
        Sector sector6 = new Sector("Ventas", "sector re piola");

        Sector sector7 = new Sector("Gerencia", "sector re piola de gerencia");
        Sector sector8 = new Sector("RRHH", "sector re piola");
        Sector sector9 = new Sector("Ventas", "sector re piola");

        //Agentes y territorios:
        SectorTerritorial territorio = new SectorTerritorial("BSAS", TipoSectorTerritorial.provincia);
        AgenteSectorial agente = new AgenteSectorial("Agente",territorio,"javiquintana99@hotmail.com","mensual");

        //Organizaciones: 3
        Organizacion organizacion1 = new Organizacion("Palmera",TipoOrg.Empresa,ClasificacionOrg.Pyme,new Punto(1f,1f));
        Organizacion organizacion2 = new Organizacion("Arbolito",TipoOrg.Empresa,ClasificacionOrg.Multinacional,new Punto(2f,2f));
        Organizacion organizacion3 = new Organizacion("UTN",TipoOrg.Institucion,ClasificacionOrg.Universidad,new Punto(3f,3f));

        organizacion1.agregarSectores(sector1,sector2,sector3);
        organizacion2.agregarSectores(sector4,sector5,sector6);
        organizacion3.agregarSectores(sector7,sector8,sector9);

        organizacion1.aceptarMiembro(miembro1,sector1);
        organizacion1.aceptarMiembro(miembro2,sector1);
        organizacion1.aceptarMiembro(miembro3,sector1);
        organizacion1.aceptarMiembro(miembro4,sector2);
        organizacion1.aceptarMiembro(miembro5,sector2);
        organizacion1.aceptarMiembro(miembro6,sector2);
        organizacion1.aceptarMiembro(miembro7,sector3);
        organizacion1.aceptarMiembro(miembro8,sector3);
        organizacion1.aceptarMiembro(miembro9,sector3);
        organizacion2.aceptarMiembro(miembro10,sector4);
        organizacion2.aceptarMiembro(miembro11,sector4);
        organizacion2.aceptarMiembro(miembro12,sector4);
        organizacion2.aceptarMiembro(miembro13,sector5);
        organizacion2.aceptarMiembro(miembro14,sector5);
        organizacion2.aceptarMiembro(miembro15,sector5);
        organizacion2.aceptarMiembro(miembro16,sector6);
        organizacion2.aceptarMiembro(miembro17,sector6);
        organizacion2.aceptarMiembro(miembro18,sector6);
        organizacion3.aceptarMiembro(miembro19,sector7);
        organizacion3.aceptarMiembro(miembro20,sector7);
        organizacion3.aceptarMiembro(miembro21,sector7);
        organizacion3.aceptarMiembro(miembro22,sector8);
        organizacion3.aceptarMiembro(miembro23,sector8);
        organizacion3.aceptarMiembro(miembro24,sector8);
        organizacion3.aceptarMiembro(miembro25,sector9);
        organizacion3.aceptarMiembro(miembro26,sector9);
        organizacion3.aceptarMiembro(miembro27,sector9);
        organizacion1.aceptarMiembro(miembro27,sector1); //organizacion compartida
        organizacion1.aceptarMiembro(miembro26,sector2); //organizacion compartida

        agente.agregarOrganizaciones(organizacion1,organizacion2,organizacion3);

        //Usuarios: 3
        Usuario usuario1 = new Usuario("usuario1","AAAAAAAA",organizacion1);
        Usuario usuario2 = new Usuario("usuario2","AAAAAAAA",organizacion2);
        Usuario usuario3 = new Usuario("usuario3","AAAAAAAA",organizacion3);

        //Actividades
        List<TipoConsumo> consumos = new ArrayList<>();
        FactorEmision feTrayectos = new FactorEmision(2f,Unidad.km);
        TipoConsumo tctrayectos = new TipoConsumo("Distancia media recorrida", Unidad.km, feTrayectos);
        consumos.add(tctrayectos);
        Actividad actTrayectos = new Actividad("Traslado de miembros de la Org", consumos, Alcance.NoControladas);

        FactorEmision factor = new FactorEmision(4F, Unidad.m3);
        TipoConsumo tconsumo = new TipoConsumo("Gas natural", Unidad.m3, factor);
        List<TipoConsumo> consumoList = new ArrayList<>();
        consumoList.add(tconsumo);
        Actividad combFija = new Actividad("Combustion fija", consumoList, Alcance.Directa);

        FactorEmision factor2 = new FactorEmision(4F, Unidad.m3);
        TipoConsumo tconsumo2 = new TipoConsumo("Gasoil", Unidad.m3, factor2);
        List<TipoConsumo> consumoList2 = new ArrayList<>();
        consumoList2.add(tconsumo2);
        Actividad combMovil = new Actividad("Combustion movil", consumoList2, Alcance.Directa);

        FactorEmision factor3 = new FactorEmision(4F, Unidad.m3);
        TipoConsumo tconsumo3 = new TipoConsumo("Electricidad", Unidad.m3, factor3);
        List<TipoConsumo> consumoList3 = new ArrayList<>();
        consumoList3.add(tconsumo3);
        Actividad electricidad = new Actividad("Electricidad adquirida y consumida", consumoList3, Alcance.Directa);

        //Transportes
        VehiculoParticular auto = new VehiculoParticular("Auto personal", TipoVehiculo.auto, TipoCombustible.nafta);
        MedioTransporte caminar = new MedioTransporte("Caminar");
        MedioTransporte bici = new MedioTransporte("Bicicleta");

        Parada p1 = new Parada("obelisco", new Punto(10f,10f));
        Parada p2 = new Parada("alem", new Punto(11f,11f));
        Parada p3 = new Parada("9 de julio", new Punto(12f,12f));
        Parada p4 = new Parada("medrano", new Punto(13f,13f));
        List<Parada> paradas1 = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));
        TransportePublico colectivo1 = new TransportePublico("Colectivo 74","74", TipoPublico.colectivo, paradas1);

        Parada p5 = new Parada("obelisco", new Punto(14f,14f));
        Parada p6 = new Parada("alem", new Punto(15f,15f));
        Parada p7 = new Parada("9 de julio", new Punto(16f,16f));
        Parada p8 = new Parada("medrano", new Punto(17f,17f));
        List<Parada> paradas2 = new ArrayList<>(Arrays.asList(p5, p6, p7, p8));
        TransportePublico colectivo2 = new TransportePublico("Colectivo 75","75", TipoPublico.colectivo, paradas2);

        //Trayectos
        Tramo tramo1 = new Tramo(bici, new Punto(10f,10f), new Punto(1f,1f));
        List<Tramo> tramos1 = new ArrayList<>(Arrays.asList(tramo1));
        Trayecto trayecto1 = new Trayecto(tramos1,21);
        miembro1.agregarTrayecto(trayecto1);

        Tramo tramo2 = new Tramo(auto, new Punto(10f,10f), new Punto(1f,1f));
        List<Tramo> tramos2 = new ArrayList<>(Arrays.asList(tramo2));
        Trayecto trayecto2 = new Trayecto(tramos2,21);
        miembro2.agregarTrayecto(trayecto2);

        Tramo tramo3 = new Tramo(colectivo1, new Punto(10f,10f), new Punto(11f,11f));
        Tramo tramo4 = new Tramo(caminar, new Punto(11f,11f), new Punto(1f,1f));
        List<Tramo> tramos3 = new ArrayList<>(Arrays.asList(tramo3,tramo4));
        Trayecto trayecto3 = new Trayecto(tramos3,21);
        miembro3.agregarTrayecto(trayecto3);

        Tramo tramo5 = new Tramo(colectivo2, new Punto(14f,14f), new Punto(15f,15f));
        Tramo tramo6 = new Tramo(auto, new Punto(15f,15f), new Punto(1f,1f));
        List<Tramo> tramos4 = new ArrayList<>(Arrays.asList(tramo5,tramo6));
        Trayecto trayecto4 = new Trayecto(tramos4,21);
        miembro4.agregarTrayecto(trayecto4);
        miembro5.agregarTrayecto(trayecto4);
        miembro6.agregarTrayecto(trayecto4);

        //Batch mediciones
        LectorCSV lector = new LectorCSV("src\\test\\java\\Resources\\csvprueba.csv",",");
        List<Actividad> actividades = new ArrayList<>(Arrays.asList(actTrayectos, combFija, combMovil, electricidad));
        BatchDatosActividad batch = lector.leerBatch(actividades);
        batch.setOrganizacion(organizacion1);

        //Persistencia
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(agente);
        EntityManagerHelper.getEntityManager().persist(organizacion1);
        EntityManagerHelper.getEntityManager().persist(organizacion2);
        EntityManagerHelper.getEntityManager().persist(organizacion3);
        EntityManagerHelper.getEntityManager().persist(usuario1);
        EntityManagerHelper.getEntityManager().persist(usuario2);
        EntityManagerHelper.getEntityManager().persist(usuario3);
        EntityManagerHelper.getEntityManager().persist(actTrayectos);
        EntityManagerHelper.getEntityManager().persist(combFija);
        EntityManagerHelper.getEntityManager().persist(combMovil);
        EntityManagerHelper.getEntityManager().persist(electricidad);
        EntityManagerHelper.getEntityManager().persist(auto);
        EntityManagerHelper.getEntityManager().persist(caminar);
        EntityManagerHelper.getEntityManager().persist(bici);
        EntityManagerHelper.getEntityManager().persist(colectivo1);
        EntityManagerHelper.getEntityManager().persist(colectivo2);
        EntityManagerHelper.getEntityManager().persist(batch);
        EntityManagerHelper.commit();
    }

}
