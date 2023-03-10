package domain.Trayecto;

import com.google.gson.annotations.Expose;

import java.util.List;

public class MedioTransportes {

    @Expose
    List<MedioTransporte> transportes;

    public List<MedioTransporte> getTransportes() {
        return transportes;
    }
}
