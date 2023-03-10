package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.Actividad.*;
import domain.Organizacion.Organizacion;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class CalculoHUMedicionTest {

    @Test
    void calculoHCMediciones(){

        Organizacion org = new Organizacion();

        FactorEmision fe1 = new FactorEmision(10f, Unidad.lt);

        TipoConsumo tipoConsumo1 = new TipoConsumo("Gas natural", Unidad.lt, fe1);
        List<TipoConsumo> tiposConsumos1 = new ArrayList<TipoConsumo>();
        tiposConsumos1.add(tipoConsumo1);

        Medicion medicion1 = new Medicion(tipoConsumo1, 10f, Periodicidad.Mensual);

        Actividad actividad1 = new Actividad("Combustion fija", tiposConsumos1, Alcance.Directa);
        Medible datosActividad1 = new DatosActividad(actividad1, medicion1, LocalDate.of(2022,10,06));

        List<Medible> mediciones = new ArrayList<>();
        mediciones.add(datosActividad1);

        Float hu = 100f;
        Assert.assertEquals(hu, org.obtenerHUMedible(mediciones));
    }

}
