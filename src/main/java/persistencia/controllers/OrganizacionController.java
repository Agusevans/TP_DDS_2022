package persistencia.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Organizacion.*;
import domain.Trayecto.Trayecto;
import persistencia.factories.*;
import persistencia.repositorio.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizacionController {

    private final RepoOrganizacion repoOrganizacion;
    private final RepoSector repoSector;
    private final RepoMiembro repoMiembro;
    private final RepoUsuario repoUsuario;
    private final RepoBatch repoBatch;
    private final RepoAgenteSectorial repoAgente;
    Gson gson;

    public OrganizacionController(){
        this.repoSector = FactoryRepoSector.get();
        this.repoOrganizacion = FactoryRepoOrganizacion.get();
        this.repoMiembro = FactoryRepoMiembro.get();
        this.repoUsuario = FactoryRepoUsuario.get();
        this.repoBatch = FactoryRepoBatchs.get();
        this.repoAgente = FactoryRepoAgente.get();
        this.gson = new GsonBuilder().setDateFormat("dd-mm-yyyy").excludeFieldsWithoutExposeAnnotation().create();
    }

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));
            if( usuario != null){
                parametros.put("usuario", usuario);
            }
        }
    }

    public String createOrg(Request request, Response response) {
        Organizacion organizacion = gson.fromJson(request.body(),Organizacion.class);

        response.type("application/json");

        try{
            repoOrganizacion.agregarOrg(organizacion);
        } catch (Exception e) {
            response.status(400);
            return "ERROR: " + e.getMessage();
        }

        return gson.toJson(organizacion);
    }

    public String readOrg(Request request, Response response) {
        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));

        response.type("application/json");
        if(organizacion == null){
            response.status(400);
            return "ERROR: No existe la organizacion con id =" + request.params("id_o");
        }

        return gson.toJson(organizacion);
    }

    public String updateOrg(Request request, Response response) { //solo contempla atributos basicos

        Organizacion organizacionNueva = gson.fromJson(request.body(),Organizacion.class);
        Organizacion organizacionVieja = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));
        response.type("application/json");

        if(organizacionVieja == null){
            response.status(400);
            return "ERROR: No existe la organizacion con id =" + request.params("id_o");
        }
        else {
            this.repoOrganizacion.actualizarOrg(organizacionVieja, organizacionNueva);
        }

        return gson.toJson(organizacionVieja);
    }

    public String deleteOrg(Request request, Response response) {
        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));

        response.type("application/json");
        if( organizacion != null){

            this.repoBatch.borrarBatchsDeOrg(organizacion);
            this.repoUsuario.borrarUsuariosDeOrg(organizacion);

            if(organizacion.getAgente() != null){
                this.repoAgente.quitarOrganizacion(organizacion, organizacion.getAgente());
            }

            this.repoOrganizacion.borrarOrg(organizacion);

        }
        else{
            response.status(400);
            return "ERROR: No existe la organizacion con id =" + request.params("id_o");
        }

        return "Se borro la organizacion";
    }

    public String readSect(Request request, Response response) {
        Sector sector = repoSector.buscar(Integer.parseInt(request.params("id_s")));
        response.type("application/json");

        if(sector == null){
            response.status(400);
            return "ERROR: No existe el sector con id =" + request.params("id_s");
        }

        return gson.toJson(sector);
    }

    /*
    public Response updateSect(Request request, Response response) {

        if (this.repoSector.buscar(Integer.parseInt(request.params("id_s"))) != null){
            Sector sector = gson.fromJson(request.body(),Sector.class);
            sector.setId(Integer.parseInt(request.params("id_s")));
            Organizacion org = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));
            this.agregarOrgAMiembros(org);
            this.repoSector.actualizar(sector);
        }

        return response;

    }

    public Response deleteSect(Request request, Response response) {
        Sector sector = repoSector.buscar(Integer.parseInt(request.params("id_s")));
        if( sector != null){
            this.repoSector.borrar(sector);
        }
        return response;
    }
    */

    public ModelAndView readMiembros (Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));

        List<Miembro> miembros = this.repoMiembro.buscarMiembrosDeOrg(usuario.getOrganizacion().getId());
        parametros.put("miembros", miembros);
        return new ModelAndView(parametros, "miembros.hbs");
    }

    public ModelAndView readMiembro (Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);

        Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));
        Miembro miembro = this.repoMiembro.buscar(Integer.parseInt(request.params("id_m")));
        Organizacion organizacion = this.repoOrganizacion.buscar(usuario.getOrganizacion().getId());

        List<Trayecto> trayectos = miembro.trayectosDeLaOrg(organizacion);

        parametros.put("miembro", miembro);
        parametros.put("trayectos", trayectos);
        return new ModelAndView(parametros, "miembro.hbs");
    }

    public String updateSectorMiembro(Request request, Response response) {

        Miembro miembro = this.repoMiembro.buscar(Integer.parseInt(request.params("id_m")));
        Sector sector = this.repoSector.buscar(Integer.parseInt(request.params("id_s")));
        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));

        response.type("application/json");

        if(organizacion == null || sector == null || miembro == null){
            response.status(400);
            return "ERROR: No existe la organizacion o el sector o el miembro";
        }

        try {
            this.repoOrganizacion.agregarMiembroASector(miembro, sector, organizacion);
        } catch (Exception e) {
            response.status(400);
            return "ERROR: " + e.getMessage();
        }

        return gson.toJson(organizacion);
    }

    public String agregarMiembro(Request request, Response response) {

        Miembro miembro = gson.fromJson(request.body(), Miembro.class);
        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));
        Sector sector = this.repoSector.buscar(Integer.parseInt(request.params("id_s")));

        response.type("application/json");

        if(organizacion == null || sector == null){
            response.status(400);
            return "ERROR: No existe la organizacion o el sector";
        }

        try {
            this.repoOrganizacion.agregarMiembroASector(miembro, sector, organizacion);
        } catch (Exception e) {
            response.status(400);
            return "ERROR: " + e.getMessage();
        }

        return gson.toJson(organizacion);
    }

}
