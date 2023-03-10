package domain.Lectores;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.Actividad.Actividad;

import java.util.Collection;
import java.util.List;

public interface LectorArchivos {

    public Collection<Medible> leerMediciones(List<Actividad> actividades);

}
