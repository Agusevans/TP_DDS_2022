package domain.Lectores;

import com.google.gson.GsonBuilder;
import domain.Actividad.Actividad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import com.google.gson.Gson;
import domain.Actividad.Actividades;
import domain.Organizacion.Organizacion;
import domain.Trayecto.MedioTransporte;
import domain.Trayecto.MedioTransportes;

public class LectorJSON_String {
    Gson gson = new GsonBuilder().setDateFormat("dd-mm-yyyy").excludeFieldsWithoutExposeAnnotation().create();
    public LectorJSON_String() {
    }

    public String leerJSON(String path){ //direccion del archivo

        String json = "";

        try{
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                temp = temp + bfRead;
            }
            json = temp;

        }catch(Exception e){
            System.err.println("No se encontro archivo");
        }

        return json;
    }

    public List<Actividad> leerActividades(String path){
        String json = this.leerJSON(path);

        return gson.fromJson(json, Actividades.class).getActividades();
    }

    public Organizacion leerOrganizacion(String path){
        String json = this.leerJSON(path);

        return gson.fromJson(json, Organizacion.class);
    }

    public List<MedioTransporte> leerMediosTransporte(String path){
        String json = this.leerJSON(path);

        return gson.fromJson(json, MedioTransportes.class).getTransportes();
    }


}
