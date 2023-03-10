package ar.edu.frba.utn.dds.mihuella;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.Actividad.Actividad;
import domain.ImplementadorFachadaOrg;
import domain.Lectores.LectorCSV;
import domain.Lectores.LectorJSON_String;
import domain.Organizacion.Organizacion;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.util.Collection;
import java.util.List;

public class CalculadorHU {

    public static void main(String[] args) {

        ArgumentParser parser = ArgumentParsers.newFor("Checksum").build()
                .defaultHelp(true)
                .description("Calculate checksum of given files.");
        parser.addArgument("-m", "--mediciones").required(true)
                .help("Archivo de mediciones");
        parser.addArgument("-p", "--params").required(true)
                .help("Archivo con parámetros de configuración");
        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        System.out.println("Archivo de mediciones: " + ns.get("mediciones"));
        System.out.println("Archivo de parametros: " + ns.get("params"));

        // calcular huella de las actividades y el total
        LectorCSV lectorCSV = new LectorCSV(ns.get("mediciones"),",");
        LectorJSON_String lectorJson = new LectorJSON_String();
        ImplementadorFachadaOrg fachadaOrg = new ImplementadorFachadaOrg(new Organizacion());

        List<Actividad> actividades = lectorJson.leerActividades(ns.get("params"));
        Collection<Medible> mediciones = lectorCSV.leerMediciones(actividades);
        Float huellaCarbono = fachadaOrg.obtenerHU(mediciones);

        System.out.println("Huella de carbono: " + huellaCarbono);
    }

}
