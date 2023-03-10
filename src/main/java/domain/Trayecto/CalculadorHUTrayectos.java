package domain.Trayecto;

import domain.Actividad.Actividad;
import domain.Organizacion.Miembro;
import domain.Organizacion.Organizacion;

import java.io.IOException;
import java.util.List;

public class CalculadorHUTrayectos {

    Organizacion organizacion;
    Actividad actividadTrayectos; //Se setea en la Org al momento de pedir un calculo de hu

    public CalculadorHUTrayectos(Organizacion organizacion){
        this.organizacion = organizacion;
    }

    public float calcularHUMiembro(Miembro miembro) throws IOException {
        List<Trayecto> trayectosOrg = miembro.trayectosDeLaOrg(this.organizacion);

        float huTrayectos = 0f;
        float fe = actividadTrayectos.getTiposConsumo().get(0).getFactorEmision().getValor();

        for(Trayecto trayecto : trayectosOrg){
            float huTrayecto = trayecto.getDistanciaDeConsumo() * trayecto.getVecesRealizadoXMes() * fe;
            if(trayecto.esCompartido()){
                huTrayectos += huTrayecto / trayecto.getIntegrantes(); //Asume que cada miembro tiene la misma instancia del trayecto
            }
            else {
                huTrayectos += huTrayecto;
            }
        }

        return huTrayectos;
    }

    public float calcularHUMiembros(List<Miembro> miembros) throws IOException {
        float huMiembros = 0f;
        for( Miembro miembro : miembros){
            huMiembros += this.calcularHUMiembro(miembro);
        }
        return huMiembros;
    }

    public void setActividadTrayectos(Actividad actividadTrayectos){
        this.actividadTrayectos = actividadTrayectos;
    }


}
