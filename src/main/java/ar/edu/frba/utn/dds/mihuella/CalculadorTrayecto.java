package ar.edu.frba.utn.dds.mihuella;

import domain.Actividad.*;
import domain.Lectores.LectorCSV;
import domain.Lectores.LectorJSON_String;
import domain.Organizacion.Organizacion;
import domain.Trayecto.MedioTransporte;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalculadorTrayecto {

    public static void main(String[] args) throws IOException {

        ArgumentParser parser = ArgumentParsers.newFor("Checksum").build()
                .defaultHelp(true)
                .description("Calculate checksum of given files.");
        parser.addArgument("-m", "--datosOrg").required(true)
                .help("Archivo de datos organizaciones");
        parser.addArgument("-p", "--trayectos").required(true)
                .help("Archivo con trayectos");
        parser.addArgument("-q", "--transportes").required(true)
                .help("Archivo con transportes y paradas");
        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        System.out.println("Archivo de datos organizaciones " + ns.get("datosOrg"));
        System.out.println("Archivo con trayectos: " + ns.get("trayectos"));
        System.out.println("Archivo con transportes y paradas: " + ns.get("transportes"));

        // calculo HU por trayectos:
        LectorJSON_String lectorJson = new LectorJSON_String();
        LectorCSV lectorCSV = new LectorCSV(ns.get("trayectos"),",");

        Organizacion org = lectorJson.leerOrganizacion(ns.get("datosOrg"));
        List<MedioTransporte> transportes = lectorJson.leerMediosTransporte(ns.get("transportes"));
        lectorCSV.leerYAgregarTrayectos(org.obtenerMiembros(),transportes);

        //se deberia sacar de un JSON, pero para probar sirve
        TipoConsumo consumo = new TipoConsumo("trayectos", Unidad.km,new FactorEmision(2f,Unidad.km));
        List<TipoConsumo> consumos =new ArrayList<>();
        consumos.add(consumo);
        Actividad actTrayectos = new Actividad("trayectos", consumos, Alcance.NoControladas);

        org.setActividadTrayectos(actTrayectos);
        org.mostrarReporteHU();
    }

}
