package persistencia.testMemoData;

import domain.Actividad.*;
import domain.EntidadPersistente;
import domain.Organizacion.Sector;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataDatosActividad {
    private static List<DatosActividad> datosActividad = new ArrayList<>();
    private static Actividad actividad = new Actividad("Produccion",consumoList(), Alcance.Directa);

    public static List<EntidadPersistente> getList(){
        if(datosActividad.size() == 0) {
            DatosActividad dato = new DatosActividad();
            dato.setId(1);
            dato.setActividad(actividad);
            dato.setMedicion(medicion());
            dato.setPeriodoDeImputacion(LocalDate.of(2022,10,06));
            addAll(dato);
        }
        return (List<EntidadPersistente>)(List<?>) datosActividad;
    }

    private static void addAll(DatosActividad ... datos){
        Collections.addAll(DataDatosActividad.datosActividad, datos);}

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
