package domain;

import domain.Actividad.*;
import domain.Lectores.LectorCSV;
import domain.Organizacion.Organizacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistencia.EntityManagerHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LecturaCSVTest {
    private LectorCSV lector;
    private List<Actividad> actividades;

    @BeforeEach
    public void init(){
        this.lector = new LectorCSV("src\\test\\java\\Resources\\csvprueba.csv",",");

        FactorEmision factor = new FactorEmision(4F, Unidad.m3);
        TipoConsumo tconsumo = new TipoConsumo("Gas natural", Unidad.m3, factor);
        List<TipoConsumo> consumoList = new ArrayList<>();
        consumoList.add(tconsumo);
        Actividad combFija = new Actividad("Combustion fija", consumoList, Alcance.Directa);

        FactorEmision factor2 = new FactorEmision(4F, Unidad.m3);
        TipoConsumo tconsumo2 = new TipoConsumo("Gasoil", Unidad.m3, factor2);
        List<TipoConsumo> consumoList2 = new ArrayList<>();
        consumoList2.add(tconsumo2);
        Actividad combMovil = new Actividad("Combustion movil", consumoList2, Alcance.Directa);

        FactorEmision factor3 = new FactorEmision(4F, Unidad.m3);
        TipoConsumo tconsumo3 = new TipoConsumo("Electricidad", Unidad.m3, factor3);
        List<TipoConsumo> consumoList3 = new ArrayList<>();
        consumoList3.add(tconsumo3);
        Actividad electr = new Actividad("Electricidad adquirida y consumida", consumoList3, Alcance.Directa);

        actividades = new ArrayList<>(Arrays.asList(combFija, combMovil, electr));

        try{
            EntityManagerHelper.getEntityManager().getTransaction().begin();
            EntityManagerHelper.getEntityManager().persist(combFija);
            EntityManagerHelper.getEntityManager().persist(combMovil);
            EntityManagerHelper.getEntityManager().persist(electr);
            EntityManagerHelper.getEntityManager().getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void mostarLecturas(){

        BatchDatosActividad batch = this.lector.leerBatch(actividades);

        System.out.println("Mediciones leidas:");
        for(DatosActividad datos: batch.getDatosAct()){
            System.out.println(
             datos.getActividad().getNombre() + " "
            +datos.getMedicion().getTipoConsumo().getNombre() + " "
            +datos.getMedicion().getValor() + " "
            +datos.getMedicion().getPeriodicidad() + " "
            +datos.getPeriodoDeImputacion());
        }

    }

    @Test
    void persistirBatch(){
        BatchDatosActividad batch = this.lector.leerBatch(actividades);
        Organizacion org = new Organizacion();
        batch.setOrganizacion(org);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(org);
        EntityManagerHelper.getEntityManager().persist(batch);
        EntityManagerHelper.commit();
    }

    @Test
    void MostrarBatchDeCSVString(){
        String csv = "Combustion fija,Gas natural,1,Mensual,2022-02-05\r\n" +
                "Electricidad adquirida y consumida,Electricidad,2000,Mensual,2022-02-05\r\n" +
                "Combustion movil,Gasoil,1000,Anual,2022-02-07";

        BatchDatosActividad batch = lector.leerBatchDeString(csv, actividades);
        System.out.println("Mediciones leidas:");
        for(DatosActividad datos: batch.getDatosAct()){
            System.out.println(
                    datos.getActividad().getNombre() + " "
                            +datos.getMedicion().getTipoConsumo().getNombre() + " "
                            +datos.getMedicion().getValor() + " "
                            +datos.getMedicion().getPeriodicidad() + " "
                            +datos.getPeriodoDeImputacion());
        }

    }

}
