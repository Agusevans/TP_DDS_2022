package persistencia.testMemoData;

import domain.Actividad.Actividad;
import domain.Actividad.Alcance;
import domain.EntidadPersistente;
import domain.Organizacion.Sector;
import domain.Organizacion.SectorTerritorial;
import domain.Organizacion.TipoSectorTerritorial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataSectorTerritorial {
    private static List<SectorTerritorial> sectoresTerritoriales = new ArrayList<SectorTerritorial>();

    public static List<EntidadPersistente> getList(){
        if(sectoresTerritoriales.size() == 0) {
            SectorTerritorial sectorTerritorial = new SectorTerritorial("Mendoza",TipoSectorTerritorial.provincia);
            sectorTerritorial.setId(1);
            addAll(sectorTerritorial);
        }
        return (List<EntidadPersistente>)(List<?>) sectoresTerritoriales;
    }

    private static void addAll(SectorTerritorial... sectorTerritorial){
        Collections.addAll(DataSectorTerritorial.sectoresTerritoriales, sectorTerritorial);}

}
