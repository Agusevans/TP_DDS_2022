package persistencia.testMemoData;

import domain.Actividad.TipoConsumo;
import domain.Actividad.Unidad;
import domain.EntidadPersistente;
import domain.Organizacion.SectorTerritorial;
import domain.Organizacion.TipoSectorTerritorial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataTipoConsumo {

    private static List<TipoConsumo> tipoConsumoList = new ArrayList<TipoConsumo>();
    public static List<EntidadPersistente> getList(){
        if(tipoConsumoList.size() == 0) {
            TipoConsumo consumo = new TipoConsumo();
            consumo.setNombre("Carbon");
            consumo.setUnidad(Unidad.kg);
            consumo.setId(1);
            addAll(consumo);
        }
        return (List<EntidadPersistente>)(List<?>) tipoConsumoList;
    }

    private static void addAll(TipoConsumo... consumo){
        Collections.addAll(DataTipoConsumo.tipoConsumoList, consumo);}
}
