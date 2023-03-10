package persistencia.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Organizacion.*;
import persistencia.factories.FactoryRepoAgente;
import persistencia.factories.FactoryRepositorio;
import persistencia.repositorio.RepoAgenteSectorial;
import persistencia.repositorio.Repositorio;
import spark.Request;
import spark.Response;


public class AgenteSectorialController {

    RepoAgenteSectorial repoAgenteSectorial;
    Repositorio<Organizacion> repoOrganizacion;
    Gson gson;

    public AgenteSectorialController(){
        this.repoAgenteSectorial = FactoryRepoAgente.get();
        this.repoOrganizacion = FactoryRepositorio.get(Organizacion.class);
        this.gson = new GsonBuilder().setDateFormat("dd-mm-yyyy").excludeFieldsWithoutExposeAnnotation().create();
    }

    public String create(Request request, Response response) {

        AgenteSectorial agenteSectorial = gson.fromJson(request.body(),AgenteSectorial.class);
        this.repoAgenteSectorial.agregar(agenteSectorial);

        response.type("application/json");

        return gson.toJson(agenteSectorial);
    }

    public String read(Request request, Response response) {

        AgenteSectorial agenteSectorial = this.repoAgenteSectorial.buscar(Integer.parseInt(request.params("id")));
        response.type("application/json");

        if(agenteSectorial == null){
            response.status(404);
            return "ERROR: Agente Sectorial no encontrado";
        }

        return gson.toJson(agenteSectorial);
    }

    public String update(Request request, Response response) { //solo contempla atributos basicos

        response.type("application/json");

        AgenteSectorial agenteSectorialViejo = this.repoAgenteSectorial.buscar(Integer.parseInt(request.params("id")));
        AgenteSectorial agenteSectorialNuevo = gson.fromJson(request.body(),AgenteSectorial.class);

        if (agenteSectorialViejo != null){
            this.repoAgenteSectorial.actualizarAgente(agenteSectorialViejo, agenteSectorialNuevo);
        }
        else {
            response.status(400);
            return "ERROR: No existe el agente sectorial con id =" + request.params("id");
        }

        return gson.toJson(agenteSectorialViejo);
    }

    public String delete(Request request, Response response) {

        AgenteSectorial agenteSectorial = this.repoAgenteSectorial.buscar(Integer.parseInt(request.params("id")));

        response.type("application/json");

        if( agenteSectorial == null) {
            response.status(400);
            return  "ERROR: No existe el agente sectorial";
        }

        for(Organizacion org : agenteSectorial.getOrganizaciones()){
            org.removerAgente();
            this.repoOrganizacion.actualizar(org);
        }

        try {
            this.repoAgenteSectorial.borrar(agenteSectorial);
        }
        catch (Exception e){
            response.status(400);
            return "ERROR: No se pudo borrar el agente sectorial: " + e.getMessage();
        }

        return "Se elimino el agente sectorial con id = " + request.params("id");
    }

    public String asignarAgenteAOrganizacion(Request request, Response response) {
        AgenteSectorial agenteSectorial = this.repoAgenteSectorial.buscar(Integer.parseInt(request.params("id_a")));
        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));

        response.type("application/json");

        if(agenteSectorial == null){
            response.status(400);
            return "ERROR: No existe el agente sectorial con id =" + request.params("id");
        }
        else if(organizacion == null){
            response.status(400);
            return "ERROR: No existe la organizacion con id =" + request.params("id_o");
        }
        else {
            organizacion.setAgente(agenteSectorial);
            agenteSectorial.agregarOrganizacion(organizacion);
            this.repoOrganizacion.actualizar(organizacion);
            this.repoAgenteSectorial.actualizar(agenteSectorial);
        }

        return "Se asigno correctamente al agente";
    }
}
