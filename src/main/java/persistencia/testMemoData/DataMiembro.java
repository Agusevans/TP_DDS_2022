package persistencia.testMemoData;

import domain.EntidadPersistente;
import domain.Organizacion.Miembro;
import domain.Trayecto.Punto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataMiembro {
    private static List<Miembro> miembros = new ArrayList<>();

    public static List<EntidadPersistente> getList(){
        if(miembros.size() == 0) {

            Miembro bruno = new Miembro();
            bruno.setNombre("Bruno");
            bruno.setApellido("Rombola");
            bruno.setNroDocumento(12345678);
            bruno.setTipoDocumento("DNI");
            addAll(bruno);
        }
        return (List<EntidadPersistente>)(List<?>) miembros;
    }

    private static void addAll(Miembro ... miembros){Collections.addAll(DataMiembro.miembros, miembros);}

}
