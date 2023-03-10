package persistencia.controllers;

import domain.Organizacion.*;
import persistencia.factories.FactoryRepoUsuario;
import persistencia.repositorio.RepoUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    RepoUsuario repoUsuario = FactoryRepoUsuario.get();


    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));
            if( usuario != null){
                parametros.put("usuario", usuario);
            }
        }
    }

    public ModelAndView login(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"login.hbs");
    }

    public ModelAndView mostrarIndex(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "index.hbs");
    }

    public Response loginPost(Request request, Response response) {
        try {

            String nombreUsuario = request.queryParams("usuario");
            String contrasenia = request.queryParams("contrasenia");

            if (repoUsuario.existe(nombreUsuario, contrasenia)) {
                Usuario usuario = repoUsuario.buscarUsuario(nombreUsuario, contrasenia);

                request.session(true);
                request.session().attribute("id", usuario.getId());

                response.redirect("/index");
            }

            else {
                response.redirect("/");
            }
        } catch (Exception e) {
            //Funcionalidad disponible solo con persistencia en Base de Datos
            response.redirect("/");
        }
        return response;
    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/");
        return response;
    }

}