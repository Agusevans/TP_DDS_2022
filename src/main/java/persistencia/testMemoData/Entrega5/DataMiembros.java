package persistencia.testMemoData.Entrega5;

import domain.EntidadPersistente;
import domain.Organizacion.Miembro;
import domain.Trayecto.Parada;
import domain.Trayecto.Punto;
import persistencia.testMemoData.Data;

import java.util.ArrayList;
import java.util.List;

public class DataMiembros {
    DataOrganizaciones dataOrganizaciones = new DataOrganizaciones();
    List<Miembro> miembrosList = new ArrayList<Miembro>();

    public List<EntidadPersistente> getMiembrosList() {
        miembrosList.add(dataOrganizaciones.setMiembros1().get(0));
        miembrosList.add(dataOrganizaciones.setMiembros1().get(1));
        miembrosList.add(dataOrganizaciones.setMiembros1().get(2));
        miembrosList.add(dataOrganizaciones.setMiembros2().get(0));
        miembrosList.add(dataOrganizaciones.setMiembros2().get(1));
        miembrosList.add(dataOrganizaciones.setMiembros2().get(2));
        miembrosList.add(dataOrganizaciones.setMiembros3().get(0));
        miembrosList.add(dataOrganizaciones.setMiembros3().get(1));
        miembrosList.add(dataOrganizaciones.setMiembros3().get(2));
        miembrosList.get(0).agregarOrganizacion(dataOrganizaciones.getOrg1());
        miembrosList.get(1).agregarOrganizacion(dataOrganizaciones.getOrg1());
        miembrosList.get(2).agregarOrganizacion(dataOrganizaciones.getOrg1());
        miembrosList.get(3).agregarOrganizacion(dataOrganizaciones.getOrg2());
        miembrosList.get(4).agregarOrganizacion(dataOrganizaciones.getOrg2());
        miembrosList.get(5).agregarOrganizacion(dataOrganizaciones.getOrg2());
        miembrosList.get(6).agregarOrganizacion(dataOrganizaciones.getOrg3());
        miembrosList.get(7).agregarOrganizacion(dataOrganizaciones.getOrg3());
        miembrosList.get(8).agregarOrganizacion(dataOrganizaciones.getOrg3());
        return (List<EntidadPersistente>) (List<?>) miembrosList;

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
