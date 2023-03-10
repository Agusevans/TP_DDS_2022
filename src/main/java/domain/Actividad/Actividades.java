package domain.Actividad;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Actividades {
    @Expose
    private List<Actividad> actividades;

    public Actividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }
}
