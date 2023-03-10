package domain;

import domain.Actividad.*;
import domain.Organizacion.Miembro;
import domain.Organizacion.Organizacion;
import domain.Organizacion.Sector;
import domain.Trayecto.*;
import domain.Trayecto.Distancia.DistanciaAPI;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CalculoHUTrayectoTest {

    FactorEmision feTrayectos;
    Actividad actividadTrayectos;
    TipoConsumo tipoConsumoTrayectos;
    List<TipoConsumo> tcList;

    Trayecto trayecto1;

    List<Tramo> tramos1;

    Tramo tramo1;
    Tramo tramo2;

    VehiculoParticular vehiculoParticular;

    Miembro miembro1;
    Miembro miembro2;

    Organizacion organizacion;
    Punto ubicacionOrg;
    Sector sector;

    @BeforeEach
    public void init () throws Exception {

        //Actividad trayectos
        feTrayectos = new FactorEmision(2f, Unidad.km);
        tipoConsumoTrayectos = new TipoConsumo("Distancia media recorrida", Unidad.km, feTrayectos);
        tcList = new ArrayList<>();
        tcList.add(tipoConsumoTrayectos);
        actividadTrayectos = new Actividad("Traslado miembros Org", tcList, Alcance.NoControladas);

        //Miembros
        Punto direccionM1 = new Punto(1f,2f);
        miembro1 = new Miembro("Juan", "Perez", "DNI", 123);
        Punto direccionM2 = new Punto(2f,3f);
        miembro2 = new Miembro("Pepe", "Pipi", "DNI", 123);

        //Organizacion
        organizacion = new Organizacion();
        sector = new Sector();
        organizacion.agregarSector(sector);
        organizacion.aceptarMiembro(miembro1, sector);
        organizacion.aceptarMiembro(miembro2, sector);

        ubicacionOrg = new Punto(4f, 5f);
        organizacion.setUbicacion(ubicacionOrg);

        organizacion.setActividadTrayectos(actividadTrayectos);

        //tramos
        vehiculoParticular = new VehiculoParticular();
        tramos1 = new ArrayList<Tramo>();
        tramo1 = new Tramo(vehiculoParticular, direccionM1, direccionM2);
        tramo2 = new Tramo(vehiculoParticular, direccionM2, ubicacionOrg);
        tramos1.add(tramo1);
        tramos1.add(tramo2);
        trayecto1 = new Trayecto(tramos1, 21);
        miembro1.agregarTrayecto(trayecto1);

    }

    @Test
    void calculoHUMiembro() throws IOException {
        float huEncontrada = organizacion.obtenerHUMiembro(miembro1);
        org.junit.jupiter.api.Assertions.assertEquals(178,Math.floor(huEncontrada));
    }

    @Test
    void calculoHUCompartido() throws IOException {
        miembro2.agregarTrayecto(trayecto1);

        float huM1 = organizacion.obtenerHUMiembro(miembro1);
        float huM2 = organizacion.obtenerHUMiembro(miembro2);
        org.junit.jupiter.api.Assertions.assertEquals(89,Math.floor(huM1));
        org.junit.jupiter.api.Assertions.assertEquals(89,Math.floor(huM2));

    }

}
