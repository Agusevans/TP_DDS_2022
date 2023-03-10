package persistencia.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Lectores.GsonHelper;
import domain.Lectores.LectorCSV;
import domain.Actividad.*;
import domain.Organizacion.Organizacion;
import domain.Organizacion.ReporteHU;
import domain.Organizacion.Usuario;
import persistencia.factories.*;
import persistencia.repositorio.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.*;

public class MedicionController {

    RepoBatch repoBatch;
    RepoDatosActividad repoDatosActividad;
    RepoActividad repoActividad;
    Repositorio<Medicion> repoMedicion;
    Repositorio<TipoConsumo> repoTipoConsumo;
    RepoUsuario repoUsuario;
    Repositorio<Organizacion> repoOrganizacion;
    Gson gson;

    public MedicionController(){
        this.repoBatch = FactoryRepoBatchs.get();
        this.repoDatosActividad = FactoryRepoDatosActividad.get();
        this.repoActividad = FactoryRepoActividad.get();
        this.repoMedicion = FactoryRepositorio.get(Medicion.class);
        this.repoTipoConsumo = FactoryRepositorio.get(TipoConsumo.class);
        this.repoUsuario = FactoryRepoUsuario.get();
        this.repoOrganizacion = FactoryRepositorio.get(Organizacion.class);
        this.gson = new GsonHelper().newGson();
    }

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));
            if( usuario != null){
                parametros.put("usuario", usuario);
            }
        }
    }

    public ModelAndView reporte(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros, "reporte.hbs");
    }

    public String filterMed(Request request, Response response){
        List<DatosActividad> listMed = repoDatosActividad.filter(request);

        String jsonOrg = gson.toJson(listMed);

        response.type("application/json");
        return jsonOrg;
    }
    public String listMed(Request request, Response response){
        List<DatosActividad> listMed = repoDatosActividad.list(Integer.parseInt(request.queryParams("offset")), Integer.parseInt(request.queryParams("limit")));

        String jsonOrg = gson.toJson(listMed);

        response.type("application/json");
        return jsonOrg;
    }

    //Batch
    public String batchAlta(Request request, Response response) {

        String json;
        response.type("application/json");

        LectorCSV lectorCSV = new LectorCSV();
        List<Actividad> actividades = this.repoActividad.buscarTodos();
        BatchDatosActividad batchDatosActividad = lectorCSV.leerBatchDeString(request.body(), actividades);

        Organizacion org = this.repoOrganizacion.buscar(Integer.parseInt(request.queryParams("OrgId")));

        if(org != null) {
            batchDatosActividad.setOrganizacion(org);
            this.repoBatch.agregar(batchDatosActividad);
            json = "Mensaje: Se cargo correctamente el batch con id: " + batchDatosActividad.getId();
        }
        else {
            response.status(400);
            json = "ERROR: No existe la organizacion";
        }

        return json;
    }
    public String batchBaja(Request request, Response response){
        BatchDatosActividad batchDatosActividad = this.repoBatch.buscar(Integer.parseInt(request.params("id")));

        String json;
        response.type("application/json");

        if( batchDatosActividad != null){
            json = "Mensaje: Se borro correctamente el batch con id: " + batchDatosActividad.getId();
            this.repoBatch.borrar(batchDatosActividad);
        }
        else {
            response.status(400);
            json = "ERROR: No existe el batch";
        }

        return json;
    }

    public String leerBatch(Request request, Response response){
        BatchDatosActividad batchDatosActividad = this.repoBatch.buscar(Integer.parseInt(request.params("id")));

        response.type("application/json");

        if( batchDatosActividad == null) {
            response.status(400);
            return  "ERROR: No existe el batch";
        }

        return gson.toJson(batchDatosActividad.getDatosAct());
    }

    public ModelAndView calculoHU(Request request, Response response) throws IOException {

        int anio = Integer.parseInt(request.queryParams("anio"));
        int mes = Integer.parseInt(request.queryParams("mes"));

        Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));

        Organizacion organizacion = this.repoOrganizacion.buscar(usuario.getOrganizacion().getId());

        Actividad actividadTrayectos;
        try{
            actividadTrayectos = repoActividad.buscarActividadTrayectos();
            organizacion.setActividadTrayectos(actividadTrayectos);
        }
        catch(Exception e){
            response.status(500);
        }

        List<DatosActividad> listMed = repoBatch.filterDAs(anio, mes, organizacion.getId());
        organizacion.setDatosActividad(listMed);

        ReporteHU reporte = organizacion.obtenerReporteHU();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("reporte", reporte);
        parametros.put("usuario", usuario);
        parametros.put("anio", anio);
        parametros.put("mes", mes);

        return new ModelAndView(parametros, "resultado.hbs");
    }

    public String apiCalculoHU(Request request, Response response) throws IOException {

        int anio = Integer.parseInt(request.queryParams("anio"));
        int mes = Integer.parseInt(request.queryParams("mes"));

        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id")));
        if (organizacion == null) {
            response.status(400);
            return "ERROR: No existe la organizacion";
        }

        Actividad actividadTrayectos;
        try{
            actividadTrayectos = repoActividad.buscarActividadTrayectos();
            organizacion.setActividadTrayectos(actividadTrayectos);
        }
        catch(Exception e){
            response.status(500);
            return "ERROR: No se pudo encontrar la actividad de trayectos";
        }

        List<DatosActividad> listMed = repoBatch.filterDAs(anio, mes, organizacion.getId());
        organizacion.setDatosActividad(listMed);

        ReporteHU reporte = organizacion.obtenerReporteHU();

        response.type("application/json");

        return reporte.reporteAString();
    }



}
