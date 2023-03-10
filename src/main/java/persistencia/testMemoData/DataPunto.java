package persistencia.testMemoData;

import domain.EntidadPersistente;
import domain.Trayecto.Punto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataPunto {
    private static List<Punto> puntos = new ArrayList<>();

    public static List<EntidadPersistente> getList(){
        if(puntos.size() == 0) {
            Punto puntoOrg = new Punto(132f,132f);
            puntoOrg.setId(1);
            Punto puntoMiembro = new Punto(123f,123f);
            puntoMiembro.setId(2);
            addAll(puntoOrg,puntoMiembro);
        }
        return (List<EntidadPersistente>)(List<?>) puntos;
    }

    private static void addAll(Punto ... puntos){
        Collections.addAll(DataPunto.puntos, puntos);}

}
