package persistencia.testMemoData;

import domain.Actividad.Medicion;
import domain.Actividad.Periodicidad;
import domain.Actividad.TipoConsumo;
import domain.Actividad.Unidad;
import domain.EntidadPersistente;
import domain.Organizacion.Miembro;
import domain.Trayecto.Punto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataMedicion {
    private static List<Medicion> mediciones = new ArrayList<>();

    public static List<EntidadPersistente> getList(){
        if(mediciones.size() == 0) {
            Medicion medicion = new Medicion(consumo(),2f, Periodicidad.Anual);
            medicion.setId(1);
            addAll(medicion);
        }
        return (List<EntidadPersistente>)(List<?>) mediciones;
    }

    private static void addAll(Medicion ... mediciones){
        Collections.addAll(DataMedicion.mediciones, mediciones);}

    private static  TipoConsumo consumo(){
        TipoConsumo consumo = new TipoConsumo();
        consumo.setNombre("Carbon");
        consumo.setUnidad(Unidad.kg);
        consumo.setId(1);
        return consumo;
    }

}
