package persistencia.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Actividad.FactorEmision;
import persistencia.factories.FactoryRepositorio;
import persistencia.repositorio.Repositorio;
import spark.Request;
import spark.Response;

public class FactorEmisionController {

    Repositorio<FactorEmision> repoFactorEmision;
    Gson gson;
    public FactorEmisionController(){
        this.repoFactorEmision = FactoryRepositorio.get(FactorEmision.class);
        this.gson = new GsonBuilder().setDateFormat("dd-mm-yyyy").create();
    }

    public String update(Request request, Response response){

        FactorEmision factorEmision;
        String json;
        response.type("application/json");

        if (this.repoFactorEmision.buscar(Integer.parseInt(request.params("id"))) != null){
            factorEmision = gson.fromJson(request.body(),FactorEmision.class);
            factorEmision.setId(Integer.parseInt(request.params("id")));
            this.repoFactorEmision.actualizar(factorEmision);
            json = gson.toJson(factorEmision);
        }
        else{
            response.status(400);
            json = "ERROR: No existe el factor de emision";
        }

        return json;

    }

}
