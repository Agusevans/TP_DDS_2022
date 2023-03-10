package persistencia.controllers;

import com.google.gson.Gson;
import domain.Organizacion.*;
import domain.Trayecto.*;

import org.apache.commons.lang3.StringUtils;
import persistencia.factories.FactoryRepoMiembro;
import persistencia.factories.FactoryRepoTransporte;
import persistencia.factories.FactoryRepoUsuario;
import persistencia.factories.FactoryRepositorio;
import persistencia.repositorio.RepoMiembro;
import persistencia.repositorio.RepoTransporte;
import persistencia.repositorio.RepoUsuario;
import persistencia.repositorio.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrayectoController {

    Repositorio<Trayecto> repoTrayecto;
    RepoMiembro repoMiembro;
    RepoTransporte repoTransporte;
    RepoUsuario repoUsuario;
    Repositorio<Organizacion> repoOrganizacion;
    Gson gson;
    String mensjaeDeError = "";

    public TrayectoController(){
        this.repoTrayecto = FactoryRepositorio.get(Trayecto.class);
        this.repoMiembro = FactoryRepoMiembro.get();
        //this.repoTransporte = FactoryRepositorio.get(MedioTransporte.class);
        this.repoTransporte = FactoryRepoTransporte.get();
        this.repoUsuario = FactoryRepoUsuario.get();
        this.repoOrganizacion = FactoryRepositorio.get(Organizacion.class);
    }

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));
            if( usuario != null){
                parametros.put("usuario", usuario);
            }
        }
    }

    public ModelAndView create(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));

        List<MedioTransporte> transportes = this.repoTransporte.buscarTodos();
        List<TransportePublico> publicos = this.repoTransporte.obtenerPublicos();
        List<MedioTransporte> noPublicos = obtenerNoPublicos(transportes, publicos);
        List<Miembro> miembros = this.repoMiembro.buscarMiembrosDeOrg(usuario.getOrganizacion().getId());

        parametros.put("publicos", publicos);
        parametros.put("noPublicos", noPublicos);
        parametros.put("miembros", miembros);

        return new ModelAndView(parametros, "trayecto.hbs");
    }

    public String save(Request request, Response response) {
        Trayecto trayecto = new Trayecto();
        response.type("application/json");

        try{
            this.asignarAtributosA(trayecto, request);
        }
        catch (Exception e){
            response.status(400);
            this.setMensajeDeError(e.getMessage());
            response.redirect("/error");
            return e.getMessage();
        }

        response.redirect("/success");

        return gson.toJson(trayecto);
    }

    public ModelAndView success(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "success.hbs");
    }

    public ModelAndView error(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("error", this.getMensajeDeError());
        this.vaciarMensajeDeError();
        asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "error.hbs");
    }

    public String delete(Request request, Response response) { //se lo borra a todos los miembros
        Trayecto trayecto = this.repoTrayecto.buscar(Integer.parseInt(request.params("id")));

        List<Miembro> miembrosTrayecto = this.repoMiembro.miebrosDeTrayecto(trayecto);
        for (Miembro miembro : miembrosTrayecto) {
            this.repoMiembro.borrarTrayecto(miembro, trayecto);
        }

        this.repoTrayecto.borrar(trayecto);
        response.type("application/json");

        return "Se borró el trayecto";
    }

    public ModelAndView deleteTrayectoMiembro(Request request, Response response) {
        Trayecto trayecto = this.repoTrayecto.buscar(Integer.parseInt(request.params("id_t")));
        Miembro miembro = this.repoMiembro.buscar(Integer.parseInt(request.params("id_m")));

        this.repoMiembro.borrarTrayecto(miembro, trayecto);

        if(trayecto.getIntegrantes() == 0)
            this.repoTrayecto.borrar(trayecto);

        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "success2.hbs");
    }

    private void asignarAtributosA(Trayecto trayecto,Request request) throws IOException {

        String cant = request.queryParams("cant");
        if(!StringUtils.isNumeric(cant) || cant.isEmpty() || Integer.parseInt(cant) <= 0)
            throw new RuntimeException("Se debe indicar una cantidad valida de veces realizado");
        else
            trayecto.setVecesRealizadoXMes(Integer.parseInt(cant));

        for (int i = 1; request.queryParams("transporte[" + i + "]") != null; i++){
            if(!request.queryParams("transporte[" + i + "]").equals("null")){
                MedioTransporte transporte = this.repoTransporte.buscar(Integer.parseInt(request.queryParams("transporte[" + i + "]")));
                Punto punto_ini = new Punto(request.queryParams("punto_inicio[" + i + "]"));
                Punto punto_fin = new Punto(request.queryParams("punto_fin[" + i + "]"));

                Tramo tramo1 = new Tramo(transporte, punto_ini, punto_fin);
                trayecto.agregarTramo(tramo1);
            }
        }

        Organizacion org = repoUsuario.buscar(request.session().attribute("id")).getOrganizacion();
        if(!trayecto.perteneceALaOrg(org))
            throw new RuntimeException("El trayecto debe iniciar o terminar en el punto de la organizacion");

        List<Miembro> miembros = new ArrayList<>();
        for(int i = 1; i<5; i++){
            if(!request.queryParams("miembro_"+i).equals("null")){
                Miembro miembro = this.repoMiembro.buscar(Integer.parseInt(request.queryParams("miembro_"+i)));
                miembro.agregarTrayecto(trayecto);
                miembros.add(miembro);
            }
        }

        if(miembros.isEmpty())
            throw new RuntimeException("Se debe indicar al menos un miembro");
        else {
            this.repoTrayecto.agregar(trayecto);
            this.repoMiembro.actualizarMiembros(miembros);
        }
    }

    public List<MedioTransporte> obtenerNoPublicos(List<MedioTransporte> transportes, List<TransportePublico> publicos){

        List<MedioTransporte> filtered = transportes.stream()
                .filter(tr -> publicos.stream()
                        .allMatch(pub -> (pub.getId() != tr.getId())))
                .collect(Collectors.toList());
        return filtered;
    }

    public void vaciarMensajeDeError(){
        this.mensjaeDeError = "";
    }

    public String getMensajeDeError(){
        return this.mensjaeDeError;
    }

    public void setMensajeDeError(String mensaje){
        this.mensjaeDeError = mensaje;
    }
}
