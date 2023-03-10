package domain.Trayecto;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;
import domain.Organizacion.Organizacion;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Trayecto extends EntidadPersistente {

    @Expose
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "trayecto_id")
    private List<Tramo> tramos;

    @Expose
    @Column
    private int integrantes;

    @Expose
    @Column
    private int vecesRealizadoXMes;

    public Trayecto(){
        this.tramos = new ArrayList<>();
        this.integrantes = 0;
    };

    public Trayecto(List<Tramo> tramos, int vecesRealizadoXMes) {
        this.tramos = tramos;
        this.integrantes = 0;
        this.vecesRealizadoXMes = vecesRealizadoXMes;
    }

    public float getDistancia() throws IOException {
        float distancia = 0f;
        for (Tramo tramo : tramos) {
            distancia += tramo.getDistancia();
        }
        return distancia;
    }

    public float getDistanciaDeConsumo() throws IOException {
        float distancia = 0f;
        for (Tramo tramo : tramos) {
            if(tramo.getMedioTransporte().usaCombustible())
                distancia += tramo.getDistancia();
        }
        return distancia;
    }

    private Punto puntoInicio(){
        Tramo primerTramo = this.tramos.get(0);
        return primerTramo.getPuntoInicio();
    }

    private Punto puntoFinal(){
        Tramo ultimoTramo = this.tramos.get( this.tramos.size() -1 );
        return ultimoTramo.getPuntoFin();
    }

    public boolean iniciaOTerminaEn(Punto punto){
        return this.puntoInicio().coincideCon(punto) || this.puntoFinal().coincideCon(punto);
    }

    public boolean perteneceALaOrg(Organizacion org){
        return this.iniciaOTerminaEn(org.getUbicacion());
    }

    public void sumarIntegrante(){
        this.integrantes++;
    }

    public void restarIntegrante(){
        this.integrantes--;
    }

    public boolean esCompartido(){
        return this.integrantes >= 2;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) { this.tramos = tramos; }

    public void agregarTramo(Tramo tramo){
        this.tramos.add(tramo);
    }

    public int getVecesRealizadoXMes() {
        return vecesRealizadoXMes;
    }

    public void setIntegrantes(int integrantes) {
        this.integrantes = integrantes;
    }

    public int getIntegrantes() {
        return integrantes;
    }

    public void setVecesRealizadoXMes(int vecesRealizadoXMes) {
        this.vecesRealizadoXMes = vecesRealizadoXMes;
    }
}
