package persistencia.testMemoData;

import domain.Actividad.*;
import domain.EntidadPersistente;
import domain.Organizacion.*;
import domain.Trayecto.Punto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataOrg {
    private static List<Organizacion> orgs = new ArrayList<>();

    public static List<EntidadPersistente> getList(){
        if(orgs.size() == 0) {

            Organizacion org1 = new Organizacion();

            //
            org1.setId(1);
            org1.setRazonSocial("La Cosa S.A.");
            org1.setClasificacion(ClasificacionOrg.Pyme);
            org1.setTipo(TipoOrg.Empresa);

            org1.setSectores(sectores());
            org1.setAgente(agente());
            org1.setDatosActividad(datosActividadList());
            org1.setUbicacion(new Punto(132f,132f));
        }
        return (List<EntidadPersistente>)(List<?>) orgs;
    }

    private static void addAll(Organizacion ... organizaciones){
        Collections.addAll(DataOrg.orgs, organizaciones);}
    private static List<Sector> sectores(){
        List<Sector> sectores = new ArrayList<Sector>();
        Sector sector = new Sector();
        sector.setNombre("Contabilidad");
        sector.agregarMiembro(miembros());
        sector.setDescripcion("Contable");
        sectores.add(sector);
        return sectores;
    }
    private static Miembro miembros(){
        Miembro bruno = new Miembro();
        bruno.setNombre("Bruno");
        bruno.setApellido("Rombola");
        bruno.setNroDocumento(12345678);
        bruno.setTipoDocumento("DNI");
        return bruno;
    }
    private static AgenteSectorial agente(){
        AgenteSectorial agente = new AgenteSectorial();
        agente.setId(1);
        agente.setNombre("Agente1");
        agente.setPeriodo("Anual");
        agente.setEmail("joseagente@gmail.com");
        agente.setSectorTerritorial(new SectorTerritorial("Mendoza", TipoSectorTerritorial.provincia));

        return agente;
    }
    private static List<DatosActividad> datosActividadList(){
        Actividad actividad = new Actividad("Produccion",consumoList(), Alcance.Directa);
        List<DatosActividad> actividades = new ArrayList<>();
        DatosActividad dato = new DatosActividad();
        dato.setId(1);
        dato.setActividad(actividad);
        dato.setMedicion(medicion());
        dato.setPeriodoDeImputacion(LocalDate.of(2022,10,06));
        actividades.add(dato);
        return actividades;
    }
    private static List<TipoConsumo> consumoList (){
        List<TipoConsumo> consumoList = new ArrayList<>();
        TipoConsumo consumo = new TipoConsumo();
        consumo.setNombre("Carbon");
        consumo.setUnidad(Unidad.kg);
        consumo.setId(1);
        consumoList.add(consumo);
        return consumoList;
    }
    private static Medicion medicion() {
        TipoConsumo consumo = new TipoConsumo();
        consumo.setNombre("Carbon");
        consumo.setUnidad(Unidad.kg);
        consumo.setId(1);
        Medicion medicion = new Medicion(consumo, 2f, Periodicidad.Anual);
        return medicion;
    }


}
