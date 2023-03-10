package persistencia.testMemoData;

import domain.Actividad.Actividad;
import domain.EntidadPersistente;
import domain.Organizacion.Miembro;
import domain.Organizacion.Sector;
import domain.Trayecto.Punto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataSector {
    private static List<Sector> sectores = new ArrayList<>();

    public static List<EntidadPersistente> getList(){
        if(sectores.size() == 0) {
            Sector sector = new Sector();
            sector.setNombre("Contabilidad");
            sector.agregarMiembro(bruno());
            sector.setDescripcion("Contable");
            addAll(sector);
        }
        return (List<EntidadPersistente>)(List<?>) sectores;
    }

    private static void addAll(Sector ... sectores){
        Collections.addAll(DataSector.sectores, sectores);}

    private static Miembro bruno(){
        Miembro bruno = new Miembro();
        bruno.setNombre("Bruno");
        bruno.setApellido("Rombola");
        bruno.setNroDocumento(12345678);
        bruno.setTipoDocumento("DNI");
        return bruno;
    }
}
