package persistencia.testMemoData;

import domain.Actividad.FactorEmision;
import domain.Actividad.Medicion;
import domain.Actividad.Periodicidad;
import domain.Actividad.Unidad;
import domain.EntidadPersistente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataFactorEmision {
    private static List<FactorEmision> FE = new ArrayList<>();
    public static List<EntidadPersistente> getList(){
        if(FE.size() == 0) {
            FactorEmision emision = new FactorEmision();
            emision.setId(1);
            emision.setUnidad(Unidad.kg);
            emision.setValor(1f);
            addAll(emision);
        }
        return (List<EntidadPersistente>)(List<?>) FE;
    }

    private static void addAll(FactorEmision ... emisiones){
        Collections.addAll(DataFactorEmision.FE, emisiones);}

}
