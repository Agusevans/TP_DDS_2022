package persistencia.testMemoData.Entrega5;

import domain.Organizacion.Miembro;
import domain.Organizacion.Sector;
import domain.Trayecto.*;

import java.io.IOException;
import java.util.ArrayList;

public class DataSectores {

    static ArrayList<Sector> sectores1 = new ArrayList<Sector>();
    static ArrayList<Sector> sectores2 = new ArrayList<Sector>();
    static ArrayList<Sector> sectores3 = new ArrayList<Sector>();

    static public ArrayList<Sector> setSectores1() {
        Sector sector = new Sector();
        Sector sector2 = new Sector();
        Sector sector3 = new Sector();
        ArrayList<Miembro> miembros = setMiembros1();

        sector.agregarMiembro(miembros.get(0));
        sector.setNombre("Ventas");
        sector.setDescripcion("Ventas minoristas y mayoristas");
        sector2.agregarMiembro(miembros.get(1));
        sector2.setNombre("Pagos");
        sector2.setDescripcion("Contacto con Proovedores");
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

        sector.agregarMiembro(miembros.get(0));
        sector.setNombre("Ventas");
        sector.setDescripcion("Ventas minoristas y mayoristas");
        sector2.agregarMiembro(miembros.get(1));
        sector2.setNombre("Pagos");
        sector2.setDescripcion("Contacto con Proovedores");
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

        sector.agregarMiembro(miembros.get(0));
        sector.setNombre("Ventas");
        sector.setDescripcion("Ventas minoristas y mayoristas");
        sector2.agregarMiembro(miembros.get(1));
        sector2.setNombre("Pagos");
        sector2.setDescripcion("Contacto con Proovedores");
        sector3.agregarMiembro(miembros.get(2));
        sector3.setNombre("Gestion de Marketing");
        sector3.setDescripcion("Campa;as publicitarias");
        sectores3.add(sector);
        sectores3.add(sector2);
        sectores3.add(sector3);
        return sectores3;
    }



/*----------------------------Miembros----------------------------*/

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
        miembro1.agregarOrganizacion(null);
        miembro1.setNroDocumento(363636363);

        //
        miembro2.setId(2);
        miembro2.setNombre("Juana");
        miembro2.setApellido("Perezala");
        //miembro2.agregarTrayecto(setTrayectos2().get(0));
        miembro2.agregarOrganizacion(null);
        miembro2.setNroDocumento(367070703);

        miembro3.setId(1);
        miembro3.setNombre("Marta");
        miembro3.setApellido("Mientres");
        miembro3.agregarTrayecto(null);
        miembro3.agregarOrganizacion(null);
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
        miembro1.agregarOrganizacion(null);
        miembro1.setNroDocumento(363636363);

        //
        miembro2.setId(5);
        miembro2.setNombre("Juana");
        miembro2.setApellido("Perezala");
        //   miembro2.agregarTrayecto(setTrayectos4().get(0));
        miembro2.agregarOrganizacion(null);
        miembro2.setNroDocumento(367070703);

        miembro3.setId(6);
        miembro3.setNombre("Marta");
        miembro3.setApellido("Mientres");
        miembro3.agregarTrayecto(null);
        miembro3.agregarOrganizacion(null);
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
        miembro3.agregarOrganizacion(null);
        miembro3.setNroDocumento(363636363);

        miembro3.setId(8);
        miembro3.setNombre("Juana");
        miembro3.setApellido("Perezala");
        miembro3.agregarOrganizacion(null);
        miembro3.setNroDocumento(367070703);

        miembro3.setId(9);
        miembro3.setNombre("Marta");
        miembro3.setApellido("Mientres");
        miembro3.agregarTrayecto(null);
        miembro3.agregarOrganizacion(null);
        miembro3.setNroDocumento(33002022);

        miembros3.add(miembro1);
        miembros3.add(miembro2);
        miembros3.add(miembro3);
        return miembros3;
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

    /*---------------------------------------------UBICACIONES--------------------------*/
    public static ArrayList<Punto> UbicacionList() {
        ArrayList<Punto> ubicaciones = new ArrayList<>();
        ubicaciones.add(new Punto(100f, 100f));
        ubicaciones.add(new Punto(-100f, 120f));
        ubicaciones.add(new Punto(-1200f, -1100f));
        return ubicaciones;
    }
}
