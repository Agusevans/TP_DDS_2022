package persistencia.testMemoData.Entrega5;

import domain.Actividad.*;
import domain.EntidadPersistente;
import domain.Organizacion.*;
import domain.Trayecto.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataOrganizaciones {
    static Organizacion org1 = new Organizacion();
    static Organizacion org2 = new Organizacion();
    static Organizacion org3 = new Organizacion();
    //static DatosSectores sectores = new DatosSectores();
    static ArrayList<Punto> datosUbicacion = UbicacionList();
    static List<Organizacion> organizaciones = new ArrayList<>();

/*---------------------------ORGANIZACIONES------------------------*/

    public static void setOrg1() {
        ArrayList<Sector> sectores = setSectores1();
        org1.setId(1);
        org1.setTipo(TipoOrg.Empresa);
        org1.setAgente(setAgente1());
        org1.setUbicacion(datosUbicacion.get(0));
        org1.setRazonSocial("Pepito S.A.");
        org1.setClasificacion(ClasificacionOrg.Multinacional);
        org1.setDatosActividad(setDatoActividad1());
        org1.setSectores(sectores);
    }

    static public void setOrg2() {
        ArrayList<Sector> sectores = setSectores2();
        org2.setId(2);
        org2.setTipo(TipoOrg.Gubernamental);
        org2.setAgente(setAgente2());
        org2.setUbicacion(datosUbicacion.get(1));
        org2.setRazonSocial("La Casa del papel");
        org2.setClasificacion(ClasificacionOrg.Pyme);
        org2.setDatosActividad(setDatoActividad2());
        org2.setSectores(sectores);
    }

    static public void setOrg3() {
        ArrayList<Sector> sectores = setSectores3();
        org3.setId(3);
        org3.setTipo(TipoOrg.Empresa);
        org3.setAgente(setAgente3());
        org3.setUbicacion(datosUbicacion.get(2));
        org3.setRazonSocial("Sanitarios Sanos");
        org3.setClasificacion(ClasificacionOrg.Pyme);
        org3.setDatosActividad(setDatoActividad3());
        org3.setSectores(sectores);
    }

    public DataOrganizaciones() {
        setOrg1();
        setOrg2();
        setOrg3();
    }

    public static Organizacion getOrg1() {
        return org1;
    }

    public static Organizacion getOrg2() {
        return org2;
    }

    public static Organizacion getOrg3() {
        return org3;
    }

    public static List<EntidadPersistente> getList() {
        setOrg1();
        setOrg2();
        setOrg3();
        org1.getSectores().forEach(sector -> {
            sector.getMiembros().forEach(miembro -> {
                miembro.agregarOrganizacion(org1);
            });
        });
        org2.getSectores().forEach(sector -> {
            sector.getMiembros().forEach(miembro -> {
                miembro.agregarOrganizacion(org2);
            });
        });

        org3.getSectores().forEach(sector -> {
            sector.getMiembros().forEach(miembro -> {
                miembro.agregarOrganizacion(org3);
            });
        });
        if (organizaciones.size() == 0) {
            organizaciones.add(org1);
            organizaciones.add(org2);
            organizaciones.add(org3);
        }
        return (List<EntidadPersistente>) (List<?>) organizaciones;
    }

    static ArrayList<Sector> sectores1 = new ArrayList<Sector>();
    static ArrayList<Sector> sectores2 = new ArrayList<Sector>();
    static ArrayList<Sector> sectores3 = new ArrayList<Sector>();

    static public ArrayList<Sector> setSectores1() {
        Sector sector = new Sector();
        Sector sector2 = new Sector();
        Sector sector3 = new Sector();
        ArrayList<Miembro> miembros = setMiembros1();

        sector.setId(1);
        sector.agregarMiembro(miembros.get(0));
        sector.setNombre("Ventas");
        sector.setDescripcion("Ventas minoristas y mayoristas");
        sector2.setId(2);
        sector2.agregarMiembro(miembros.get(1));
        sector2.setNombre("Pagos");
        sector2.setDescripcion("Contacto con Proovedores");
        sector3.setId(3);
        sector3.agregarMiembro(miembros.get(2));
        sector3.setNombre("Gestion de Marketing");
        sector3.setDescripcion("Campa;as publicitarias");
        sectores1.add(sector);
        sectores1.add(sector2);
        sectores1.add(sector3);
        return sectores1;
    }

    static public ArrayList<Sector> setSectores2() {
        Sector sector = new Sector();
        Sector sector2 = new Sector();
        Sector sector3 = new Sector();
        ArrayList<Miembro> miembros = setMiembros2();

        sector.setId(1);
        sector.agregarMiembro(miembros.get(0));
        sector.setNombre("Ventas");
        sector.setDescripcion("Ventas minoristas y mayoristas");
        sector2.setId(2);
        sector2.agregarMiembro(miembros.get(1));
        sector2.setNombre("Pagos");
        sector2.setDescripcion("Contacto con Proovedores");
        sector3.setId(3);
        sector3.agregarMiembro(miembros.get(2));
        sector3.setNombre("Gestion de Marketing");
        sector3.setDescripcion("Campa;as publicitarias");
        sectores2.add(sector);
        sectores2.add(sector2);
        sectores2.add(sector3);
        return sectores2;
    }

    static public ArrayList<Sector> setSectores3() {
        Sector sector = new Sector();
        Sector sector2 = new Sector();
        Sector sector3 = new Sector();
        ArrayList<Miembro> miembros = setMiembros3();

        sector.setId(1);
        sector.agregarMiembro(miembros.get(0));
        sector.setNombre("Ventas");
        sector.setDescripcion("Ventas minoristas y mayoristas");
        sector2.setId(2);
        sector2.agregarMiembro(miembros.get(1));
        sector2.setNombre("Pagos");
        sector2.setDescripcion("Contacto con Proovedores");
        sector3.setId(3);
        sector3.agregarMiembro(miembros.get(2));
        sector3.setNombre("Gestion de Marketing");
        sector3.setDescripcion("Campa;as publicitarias");
        sectores3.add(sector);
        sectores3.add(sector2);
        sectores3.add(sector3);
        return sectores3;
    }

    /*---------------------------------------------MIEMBROS--------------------------*/

    static ArrayList<Miembro> miembros1 = new ArrayList<Miembro>();
    static ArrayList<Miembro> miembros2 = new ArrayList<Miembro>();
    static ArrayList<Miembro> miembros3 = new ArrayList<Miembro>();

    static DataOrganizaciones organizacions = new DataOrganizaciones();

    static ArrayList<Miembro> setMiembros1() {
        Miembro miembro1 = new Miembro();
        Miembro miembro2 = new Miembro();
        Miembro miembro3 = new Miembro();

        miembro1.setId(1);
        miembro1.setNombre("Juan");
        miembro1.setApellido("Perez");
        //miembro1.agregarTrayecto(setTrayectos1().get(0));
        //miembro1.agregarOrganizacion(null);
        miembro1.setNroDocumento(363636363);

        //
        miembro2.setId(2);
        miembro2.setNombre("Juana");
        miembro2.setApellido("Perezala");
        //miembro2.agregarTrayecto(setTrayectos2().get(0));
        //miembro2.agregarOrganizacion(null);
        miembro2.setNroDocumento(367070703);

        miembro3.setId(1);
        miembro3.setNombre("Marta");
        miembro3.setApellido("Mientres");
        //miembro3.agregarTrayecto(null);
        //miembro3.agregarOrganizacion(null);
        miembro3.setNroDocumento(33002022);

        miembros1.add(miembro1);
        miembros1.add(miembro2);
        miembros1.add(miembro3);
        return miembros1;
    }

    static ArrayList<Miembro> setMiembros2() {

        Miembro miembro1 = new Miembro();
        Miembro miembro2 = new Miembro();
        Miembro miembro3 = new Miembro();

        miembro1.setId(4);
        miembro1.setNombre("Juan");
        miembro1.setApellido("Perez");
       // miembro1.agregarTrayecto(setTrayectos3().get(0));
        //miembro1.agregarOrganizacion(null);
        miembro1.setNroDocumento(363636363);

        //
        miembro2.setId(5);
        miembro2.setNombre("Juana");
        miembro2.setApellido("Perezala");
     //   miembro2.agregarTrayecto(setTrayectos4().get(0));
        //miembro2.agregarOrganizacion(null);
        miembro2.setNroDocumento(367070703);

        miembro3.setId(6);
        miembro3.setNombre("Marta");
        miembro3.setApellido("Mientres");
        //miembro3.agregarTrayecto(null);
        //miembro3.agregarOrganizacion(null);
        miembro3.setNroDocumento(33002022);

        miembros2.add(miembro1);
        miembros2.add(miembro2);
        miembros2.add(miembro3);
        return miembros2;
    }

    static ArrayList<Miembro> setMiembros3() {

        Miembro miembro1 = new Miembro();
        Miembro miembro2 = new Miembro();
        Miembro miembro3 = new Miembro();

        miembro3.setId(7);
        miembro3.setNombre("Juan");
        miembro3.setApellido("Perez");
 //       miembro3.agregarTrayecto(setTrayectos5().get(0));
        //miembro3.agregarOrganizacion(null);
        miembro3.setNroDocumento(363636363);

        //
        miembro3.setId(8);
        miembro3.setNombre("Juana");
        miembro3.setApellido("Perezala");
   //     miembro3.agregarTrayecto(setTrayectos6().get(0));
        //miembro3.agregarOrganizacion(null);
        miembro3.setNroDocumento(367070703);

        miembro3.setId(9);
        miembro3.setNombre("Marta");
        miembro3.setApellido("Mientres");
        //miembro3.agregarTrayecto(null);
        //miembro3.agregarOrganizacion(null);
        miembro3.setNroDocumento(33002022);

        miembros3.add(miembro1);
        miembros3.add(miembro2);
        miembros3.add(miembro3);
        return miembros3;
    }
    /*---------------------------------------------UBICACIONES--------------------------*/
    public static ArrayList<Punto> UbicacionList() {
        ArrayList<Punto> ubicaciones = new ArrayList<>();
        ubicaciones.add(new Punto(100f, 100f));
        ubicaciones.add(new Punto(-100f, 120f));
        ubicaciones.add(new Punto(-1200f, -1100f));
        return ubicaciones;
    }
    /*------------------------------------------DatosActividad-------------------------*/

    static public ArrayList<DatosActividad> setDatoActividad1() {
        ArrayList<DatosActividad> datosActividad1 = new ArrayList<DatosActividad>();

        DatosActividad datosActividad = new DatosActividad();
        datosActividad.setId(1);
        datosActividad.setActividad(setActividad1());
        datosActividad.setMedicion(setMedicion1());
        datosActividad.setPeriodoDeImputacion(LocalDate.of(2022,02,02));
        datosActividad.setPeriodoDeImputacion(LocalDate.of(2022,10,06));

        datosActividad.setPeriodoDeImputacion(LocalDate.of(2022,10,06));

        datosActividad1.add(datosActividad);
        return datosActividad1;
    }

    static public ArrayList<DatosActividad> setDatoActividad2() {
        ArrayList<DatosActividad> datosActividad2 = new ArrayList<DatosActividad>();

        DatosActividad datosActividad = new DatosActividad();
        datosActividad.setId(2);
        datosActividad.setActividad(setActividad2());
        datosActividad.setMedicion(setMedicion2());
        datosActividad.setPeriodoDeImputacion(LocalDate.of(2022,02,02));
        datosActividad.setPeriodoDeImputacion(LocalDate.of(2022,10,06));
        datosActividad2.add(datosActividad);
        return datosActividad2;
    }

    static public ArrayList<DatosActividad> setDatoActividad3() {
        ArrayList<DatosActividad> datosActividad3 = new ArrayList<DatosActividad>();

        DatosActividad datosActividad = new DatosActividad();
        datosActividad.setId(3);
        datosActividad.setActividad(setActividad3());
        datosActividad.setMedicion(setMedicion3());
        datosActividad.setPeriodoDeImputacion(LocalDate.of(2022,02,02));
        datosActividad.setPeriodoDeImputacion(LocalDate.of(2022,10,06));
        datosActividad3.add(datosActividad);
        return datosActividad3;
    }
    /*----------------------DataConsumo-------------------*/
    static ArrayList<TipoConsumo> consumo3 = new ArrayList<TipoConsumo>();
    static List<TipoConsumo> consumos = new ArrayList<>();
    static ArrayList<TipoConsumo> setConsumo1(){
        ArrayList<TipoConsumo> consumo = new ArrayList<TipoConsumo>();
        consumo.add(new TipoConsumo("Carbon", Unidad.kg,new FactorEmision(1f,Unidad.kg)));
        return consumo;
    }

    static ArrayList<TipoConsumo> setConsumo2(){
        ArrayList<TipoConsumo> consumo = new ArrayList<TipoConsumo>();
        consumo.add(new TipoConsumo("Electricidad", Unidad.Kwh,new FactorEmision(1f,Unidad.Kwh)));
        return consumo;
    }
    static ArrayList<TipoConsumo> setConsumo3(){
        ArrayList<TipoConsumo> consumo = new ArrayList<TipoConsumo>();
        consumo.add(new TipoConsumo("Carbon", Unidad.kg,new FactorEmision(1f,Unidad.kg)));
        return consumo;
    }
    /*-------------------------------DataActividad----------------------------------*/

    static public Actividad setActividad1() {

        Actividad actividad1 = new Actividad();
        actividad1.setId(1);
        actividad1.setAlcance(Alcance.Directa);
        actividad1.setNombre("A1");
        actividad1.setTiposConsumo(setConsumo1());
        return actividad1;
    }

    static public Actividad setActividad2() {
        Actividad actividad2 = new Actividad();

        actividad2.setId(2);
        actividad2.setAlcance(Alcance.NoControladas);
        actividad2.setNombre("A2");
        actividad2.setTiposConsumo(setConsumo2());
        return actividad2;
    }

    static public Actividad setActividad3() {

        Actividad actividad3 = new Actividad();
        actividad3.setId(3);
        actividad3.setAlcance(Alcance.Indirecta);
        actividad3.setNombre("A3");
        actividad3.setTiposConsumo(setConsumo3());
        return actividad3;
    }

    /*------------------------Data Sector Territorial ----------------------*/
    static public  SectorTerritorial setTerritorio1() {
        SectorTerritorial territorio1 = new SectorTerritorial();

        territorio1.setId(1);
        territorio1.setNombre("Corrientes");
        territorio1.setTipoSectorTerritorial(TipoSectorTerritorial.municipio);
        return territorio1;
    }
    static public SectorTerritorial setTerritorio2() {

        SectorTerritorial territorio2 = new SectorTerritorial();
        territorio2.setId(2);
        territorio2.setNombre("Neuquen");
        territorio2.setTipoSectorTerritorial(TipoSectorTerritorial.provincia);
        return territorio2;
    }
    static public SectorTerritorial setTerritorio3() {
        SectorTerritorial territorio3 = new SectorTerritorial();
        territorio3.setId(3);
        territorio3.setNombre("CABA");
        territorio3.setTipoSectorTerritorial(TipoSectorTerritorial.municipio);
        return territorio3;
    }
    /*------------------Paradas------------------*/
    static public ArrayList<Parada> setParada110() {
        ArrayList<Parada> parada110 = new ArrayList<Parada>();

        Parada parada = new Parada();
        for (int i = 1; i < 5; i++) {
            parada.setId(i);
            parada.setPunto(new Punto(110f+i*12,200f-i*12));
            parada.setNombre("Olavarria"+ i);
            parada110.add(parada);
        }
        return parada110;
    }

    static public ArrayList<Parada> setParada124() {
        ArrayList<Parada> parada124 = new ArrayList<Parada>();
        Parada parada = new Parada();
        for (int i = 5;i < 10; i++) {
            parada.setId(i);
            parada.setPunto(new Punto(210f - i * 12, 400f - i * 12));
            parada.setNombre("Jesus Maria" +( i-4));
            parada124.add(parada);
        }
        return parada124;
    }
    static public ArrayList<Parada> setParadaSubteB() {

        ArrayList<Parada> paradaSubteB = new ArrayList<>();

        Parada parada = new Parada();
        for (int i = 10;i < 15; i++) {
            parada.setId(i);
            parada.setPunto(new Punto(1110f - i * 1, 200f + i * 2));
            parada.setNombre("Ezeiza" +( i-14));
            paradaSubteB.add(parada);
        }
        return paradaSubteB;
    }
    static public ArrayList<Parada> setParadaSubteC() {

        ArrayList<Parada> paradaSubteC = new ArrayList<>();

        Parada parada = new Parada();
        for (int i = 15;i < 20; i++) {
            parada.setId(i);
            parada.setPunto(new Punto(110f - i * 1, -200f + i * 2));
            parada.setNombre("Ezeiza" +( i-14));
            paradaSubteC.add(parada);
        }
        return paradaSubteC;
    }
    /*--------------Trayectos---------------*/
    static public ArrayList<Trayecto> setTrayectos1() throws IOException {
        ArrayList<Miembro> miembros = setMiembros1();

        ArrayList<Trayecto> trayectos1 = new ArrayList<Trayecto>();
        Trayecto trayecto = new Trayecto();
        trayecto.agregarTramo(new Tramo(new MedioTransporte("a pie"),new Punto(1f,1f),setParada110().get(1).getPunto()));
        trayecto.agregarTramo(new Tramo(new TransportePublico("Colectivo","110",TipoPublico.colectivo,setParada110()),setParada110().get(1).getPunto(),setParada110().get(4).getPunto()));
        trayectos1.add(trayecto);
        return trayectos1;
    }

    static public ArrayList<Trayecto> setTrayectos2() throws IOException {

        ArrayList<Miembro> miembros = setMiembros1();
        ArrayList<Trayecto> trayectos2 = new ArrayList<Trayecto>();
        Trayecto trayecto = new Trayecto();
        trayecto.agregarTramo(new Tramo(new TransportePublico("Subte","Linea B",TipoPublico.subte,setParadaSubteB()),setParadaSubteB().get(1).getPunto(),setParadaSubteB().get(1).getPunto()));
        trayectos2.add(trayecto);
        return trayectos2;
    }

    static public ArrayList<Trayecto> setTrayectos3() throws IOException {
        ArrayList<Miembro> miembros = setMiembros2();
        ArrayList<Trayecto> trayectos3 = new ArrayList<Trayecto>();

        Trayecto trayecto = new Trayecto();
        trayecto.agregarTramo(new Tramo(new TransportePublico("Subte","Linea C",TipoPublico.subte,setParadaSubteC()),setParadaSubteC().get(1).getPunto(),setParadaSubteC().get(4).getPunto()));
        trayectos3.add(trayecto);
        return trayectos3;
    }

    static public ArrayList<Trayecto> setTrayectos4() throws IOException {
        ArrayList<Miembro> miembros = setMiembros2();
        ArrayList<Trayecto> trayectos4 = new ArrayList<Trayecto>();

        Trayecto trayecto = new Trayecto();
        trayecto.agregarTramo(new Tramo(new TransporteContratado(TipoContratado.taxi),new Punto(1f,1f),UbicacionList().get(1)));
        trayectos4.add(trayecto);
        return trayectos4;
    }

    static public ArrayList<Trayecto> setTrayectos5() throws IOException {
        ArrayList<Miembro> miembros = setMiembros3();
        ArrayList<Trayecto> trayectos5 = new ArrayList<Trayecto>();

        Trayecto trayecto = new Trayecto();
        trayecto.agregarTramo(new Tramo(new TransporteContratado(TipoContratado.remis),new Punto(1f,1f),UbicacionList().get(2)));
        trayectos5.add(trayecto);
        return trayectos5;
    }

    static public ArrayList<Trayecto> setTrayectos6() throws IOException {
        ArrayList<Miembro> miembros = setMiembros3();
        ArrayList<Trayecto> trayectos6 = new ArrayList<Trayecto>();

        Trayecto trayecto = new Trayecto();
        trayecto.agregarTramo(new Tramo(new MedioTransporte("bicicleta"),new Punto(1f,1f),UbicacionList().get(2)));
        trayectos6.add(trayecto);
        return trayectos6;
    }
    /*-----------------------------------Agente-----------------------*/
    static public AgenteSectorial setAgente1() {
        AgenteSectorial agente1 = new AgenteSectorial();
        agente1.setId(1);
        agente1.setNombre("Pepito Corrientes S.A.");
        agente1.setSectorTerritorial(setTerritorio1());
        agente1.setPeriodo("20221011");
        agente1.setEmail("corrientes@pepitosa.com");
        return agente1;
    }

    static public AgenteSectorial setAgente2() {
        AgenteSectorial agente2 = new AgenteSectorial();
        agente2.setId(2);
        agente2.setNombre("Papel Neuquen S.A.");
        agente2.setSectorTerritorial(setTerritorio2());
        agente2.setPeriodo("20211010");
        agente2.setEmail("neuquen@cdp.com");
        return agente2;
    }

    static public AgenteSectorial setAgente3() {
        AgenteSectorial agente3 = new AgenteSectorial();
        agente3.setId(3);
        agente3.setNombre("Sanitarios CABA");
        agente3.setSectorTerritorial(setTerritorio3());
        agente3.setPeriodo("2200121");
        agente3.setEmail("CABA@sanasano.com");
        return agente3;
    }
/*----MEDICIONES------*/
    static public Medicion setMedicion1() {
        Medicion medicion1 = new Medicion();
        medicion1.setId(1);
        medicion1.setValor(10f);
        medicion1.setPeriodicidad(Periodicidad.Anual);
        medicion1.setTipoConsumo(new TipoConsumo("Carbon", Unidad.kg, new FactorEmision(10f, Unidad.kg)));
        return medicion1;
    }

    static public Medicion setMedicion2() {
        Medicion medicion2 = new Medicion();
        medicion2.setId(2);
        medicion2.setValor(100f);
        medicion2.setPeriodicidad(Periodicidad.Anual);
        medicion2.setTipoConsumo(new TipoConsumo("Electricidad", Unidad.Kwh, new FactorEmision(150f, Unidad.kg)));
        return medicion2;
    }

    static public Medicion setMedicion3() {
        Medicion medicion3 = new Medicion();
        medicion3.setId(1);
        medicion3.setValor(10f);
        medicion3.setPeriodicidad(Periodicidad.Anual);
        medicion3.setTipoConsumo(new TipoConsumo("Carbon", Unidad.kg, new FactorEmision(10f, Unidad.kg)));
        return medicion3;
    }
}