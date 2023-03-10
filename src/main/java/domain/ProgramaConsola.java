package domain;

import com.google.gson.Gson;
import domain.Actividad.*;
import domain.Lectores.LectorJSON_String;
import domain.Organizacion.Miembro;
import domain.Organizacion.Organizacion;
import domain.Organizacion.Sector;
import domain.Trayecto.Punto;
import domain.Trayecto.Tramo;
import domain.Trayecto.Trayecto;
import domain.Trayecto.VehiculoParticular;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//la idea de esta clase es mostrar por pantalla el output de los calculos e ir verificando
public class ProgramaConsola {

    public static void main(String[] args) throws Exception {

        FactorEmision feTrayectos;
        Actividad actividadTrayectos;
        TipoConsumo tipoConsumoTrayectos;
        List<TipoConsumo> tcList;

        Trayecto trayecto1;

        ArrayList<Tramo> tramos1;

        Tramo tramo1;
        Tramo tramo2;

        VehiculoParticular vehiculoParticular;

        Miembro miembro1;
        Miembro miembro2;

        Organizacion organizacion;
        Punto ubicacionOrg;
        Sector sector;

        //Actividad trayectos
        feTrayectos = new FactorEmision(2f, Unidad.km);
        tipoConsumoTrayectos = new TipoConsumo("Distancia media recorrida", Unidad.km, feTrayectos);
        tcList = new ArrayList<>();
        tcList.add(tipoConsumoTrayectos);
        actividadTrayectos = new Actividad("Traslado de miembros de la Org", tcList, Alcance.NoControladas);

        //Miembros
        Punto direccionM1 = new Punto(1f,2f);
        miembro1 = new Miembro("Juan", "Perez", "DNI", 123);
        Punto direccionM2 = new Punto(2f,3f);
        miembro2 = new Miembro("Pepe", "Pipi", "DNI", 123);

        //Organizacion
        organizacion = new Organizacion();
        sector = new Sector("RRHH","El mejor sector de la organizacion");
        organizacion.agregarSector(sector);
        organizacion.aceptarMiembro(miembro1, sector);
        organizacion.aceptarMiembro(miembro2, sector);
        organizacion.setRazonSocial("Coca Cola");

        ubicacionOrg = new Punto(4f, 5f);
        organizacion.setUbicacion(ubicacionOrg);

        organizacion.setActividadTrayectos(actividadTrayectos);

        //Actividades
        FactorEmision factor = new FactorEmision();
        factor.setUnidad(Unidad.m3);
        factor.setValor(4F);

        TipoConsumo tconsumo = new TipoConsumo("Gas Natural", Unidad.m3, factor);
        ArrayList<TipoConsumo> consumoList = new ArrayList<TipoConsumo>();
        consumoList.add(tconsumo);

        Actividad act = new Actividad("Combustion fija", consumoList, Alcance.Directa);

        Medicion medicion = new Medicion(tconsumo,5F,Periodicidad.Mensual);
        Medicion medicion2 = new Medicion(tconsumo,6F,Periodicidad.Anual);

        DatosActividad datosActividad = new DatosActividad(act, medicion, LocalDate.of(2022,10,06));
        DatosActividad datosActividad2 = new DatosActividad(act, medicion2, LocalDate.of(2022,10,06));
        ArrayList<DatosActividad> datosList = new ArrayList<DatosActividad>();
        datosList.add(datosActividad);
        datosList.add(datosActividad2);
        organizacion.setDatosActividad(datosList);

        //tramos
        vehiculoParticular = new VehiculoParticular();
        tramos1 = new ArrayList<>();
        tramo1 = new Tramo(vehiculoParticular, direccionM1, direccionM2);
        tramo2 = new Tramo(vehiculoParticular, direccionM2, ubicacionOrg);
        tramos1.add(tramo1);
        tramos1.add(tramo2);
        trayecto1 = new Trayecto(tramos1, 21);
        miembro1.agregarTrayecto(trayecto1);
        miembro2.agregarTrayecto(trayecto1);

        organizacion.mostrarReporteHU();
    }

}
