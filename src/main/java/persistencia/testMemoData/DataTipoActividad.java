package persistencia.testMemoData;

import domain.Actividad.*;
import domain.EntidadPersistente;
import domain.Organizacion.Miembro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataTipoActividad {
    private static List<Actividad> actividades = new ArrayList<Actividad>();

    public static List<EntidadPersistente> getList(){
        if(actividades.size() == 0) {

            Actividad actividad = new Actividad("Produccion",consumoList(),Alcance.Directa);
            actividad.setId(1);
            addAll(actividad);
        }
        return (List<EntidadPersistente>)(List<?>) actividades;
    }

    private static void addAll(Actividad ... actividades){
        Collections.addAll(DataTipoActividad.actividades, actividades);}

    private static List<TipoConsumo> consumoList (){
        List<TipoConsumo> consumoList = new ArrayList<>();
        TipoConsumo consumo = new TipoConsumo();
        consumo.setNombre("Carbon");
        consumo.setUnidad(Unidad.kg);
        consumo.setId(1);
        consumoList.add(consumo);
        return consumoList;
    }

}
