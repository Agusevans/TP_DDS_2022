package domain.Organizacion;

import com.google.gson.annotations.Expose;
import domain.Actividad.Actividad;
import domain.Actividad.TipoConsumo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteHU {

    @Expose
    float huTrayectos;
    @Expose
    float huMediciones;
    @Expose
    float huTotal;

    @Expose
    List<DesgloseSector> desgloseSectores;

    @Expose
    List<DesgloseActividad> desgloseActividades;

    public ReporteHU(){
        huTrayectos = 0;
        huMediciones = 0;
        huTotal = 0;
        this.desgloseSectores = new ArrayList<>();
        this.desgloseActividades = new ArrayList<>();
    }

    public void agregarHUMedicion(Actividad actividad, TipoConsumo tipoConsumo, float hu){
        huMediciones += hu;

        DesgloseActividad desgloseActividad = null;
        for(DesgloseActividad desglose : desgloseActividades){
            if(desglose.getActividad().getNombre().equals(actividad.getNombre())){
                desgloseActividad = desglose;
                break;
            }
        }

        if(desgloseActividad == null){
            desgloseActividad = new DesgloseActividad(actividad);
            desgloseActividades.add(desgloseActividad);
        }

        Float huTC = desgloseActividad.mapDesgloseTipoConsumo.get(tipoConsumo);
        if(huTC == null)
            desgloseActividad.mapDesgloseTipoConsumo.put(tipoConsumo, hu);
        else
            desgloseActividad.mapDesgloseTipoConsumo.put(tipoConsumo, hu + huTC);

    }

    public void agregarHUTrayecto(Sector sector, Miembro miembro, float hu){
        huTrayectos += hu;

        DesgloseSector desgloseSector = null;
        for (DesgloseSector desglose : desgloseSectores){
            if(desglose.getSector().getNombre().equals(sector.getNombre())){
                desgloseSector = desglose;
                break;
            }
        }

        if(desgloseSector == null){
            desgloseSector = new DesgloseSector(sector);
            desgloseSectores.add(desgloseSector);
        }

        Float huMiembro = desgloseSector.mapDesgloseMiembro.get(miembro);
        if(huMiembro == null)
            desgloseSector.mapDesgloseMiembro.put(miembro, hu);
        else
            desgloseSector.mapDesgloseMiembro.put(miembro, hu + huMiembro);
    }

    public void sumarTotal(){
        huTotal = huTrayectos + huMediciones;
    }

    public float getTotalHu(){
        return huTotal;
    }

    public void mostrarReporte(){
        System.out.println("-HU por sector: ");
        desgloseSectores.forEach(desgloseSector -> {
            System.out.println("    Sector: " + desgloseSector.sector.getNombre());
            desgloseSector.mapDesgloseMiembro.forEach((miembro, hu) -> {
                System.out.println("        Miembro: " + miembro.getNombre() + " - HU: " + hu + " - %: " + (hu/huTrayectos)*100);
            });
        });
        System.out.println("-HU por actividad: ");
        desgloseActividades.forEach(desgloseActividad -> {
            System.out.println("    Actividad: " + desgloseActividad.actividad.getNombre());
            desgloseActividad.mapDesgloseTipoConsumo.forEach((tipoConsumo, hu) -> {
                System.out.println("        Tipo de consumo: " + tipoConsumo.getNombre() + " - HU: " + hu + " - %: " + (hu/huMediciones)*100);
            });
        });
        System.out.println("-HU por mediciones: " + huMediciones);
        System.out.println("-HU por trayectos: " + huTrayectos);
        System.out.println("HU total: " + (huTrayectos + huMediciones));
    }

    public String reporteAString(){
        String reporte = "";
        reporte += "-HU por sector: ";
        reporte += System.lineSeparator();
        for(DesgloseSector desgloseSector : desgloseSectores){
            reporte += "    Sector: " + desgloseSector.sector.getNombre();
            reporte += System.lineSeparator();
            for(Map.Entry<Miembro, Float> entry : desgloseSector.mapDesgloseMiembro.entrySet()){
                reporte += "        Miembro: " + entry.getKey().getNombre() + " - HU: " + entry.getValue() + " - %: " + (entry.getValue()/huTrayectos)*100;
                reporte += System.lineSeparator();
            }
        }
        reporte += "-HU por actividad: ";
        reporte += System.lineSeparator();
        for(DesgloseActividad desgloseActividad : desgloseActividades){
            reporte += "    Actividad: " + desgloseActividad.actividad.getNombre();
            reporte += System.lineSeparator();
            for(Map.Entry<TipoConsumo, Float> entry : desgloseActividad.mapDesgloseTipoConsumo.entrySet()){
                reporte += "        Tipo de consumo: " + entry.getKey().getNombre() + " - HU: " + entry.getValue() + " - %: " + (entry.getValue()/huMediciones)*100;
                reporte += System.lineSeparator();
            }
        }
        reporte += "-HU por mediciones: " + huMediciones;
        reporte += System.lineSeparator();
        reporte += "-HU por trayectos: " + huTrayectos;
        reporte += System.lineSeparator();
        reporte += "HU total: " + (huTrayectos + huMediciones);
        reporte += System.lineSeparator();
        return reporte;
    }

    private class DesgloseSector{
        @Expose
        Sector sector;
        @Expose
        Map<Miembro, Float> mapDesgloseMiembro;

        public DesgloseSector(Sector sector) {
            this.sector = sector;
            this.mapDesgloseMiembro = new HashMap<>();
        }

        public Sector getSector() {
            return sector;
        }

        public void setSector(Sector sector) {
            this.sector = sector;
        }

        public Map<Miembro, Float> getMapDesgloseMiembro() {
            return mapDesgloseMiembro;
        }

        public void setMapDesgloseMiembro(Map<Miembro, Float> mapDesgloseMiembro) {
            this.mapDesgloseMiembro = mapDesgloseMiembro;
        }
    }

    private class DesgloseActividad{
        @Expose
        Actividad actividad;
        @Expose
        Map<TipoConsumo, Float> mapDesgloseTipoConsumo;

        public DesgloseActividad(Actividad actividad) {
            this.actividad = actividad;
            this.mapDesgloseTipoConsumo = new HashMap<>();
        }

        public Actividad getActividad() {
            return actividad;
        }

        public void setActividad(Actividad actividad) {
            this.actividad = actividad;
        }

        public Map<TipoConsumo, Float> getMapDesgloseTipoConsumo() {
            return mapDesgloseTipoConsumo;
        }

        public void setMapDesgloseTipoConsumo(Map<TipoConsumo, Float> mapDesgloseTipoConsumo) {
            this.mapDesgloseTipoConsumo = mapDesgloseTipoConsumo;
        }
    }

    public float getHuTrayectos() {
        return huTrayectos;
    }

    public void setHuTrayectos(float huTrayectos) {
        this.huTrayectos = huTrayectos;
    }

    public float getHuMediciones() {
        return huMediciones;
    }

    public void setHuMediciones(float huMediciones) {
        this.huMediciones = huMediciones;
    }

    public float getHuTotal() {
        return huTotal;
    }

    public void setHuTotal(float huTotal) {
        this.huTotal = huTotal;
    }

    public List<DesgloseSector> getDesgloseSectores() {
        return desgloseSectores;
    }

    public void setDesgloseSectores(List<DesgloseSector> desgloseSectores) {
        this.desgloseSectores = desgloseSectores;
    }

    public List<DesgloseActividad> getDesgloseActividades() {
        return desgloseActividades;
    }

    public void setDesgloseActividades(List<DesgloseActividad> desgloseActividades) {
        this.desgloseActividades = desgloseActividades;
    }
}


