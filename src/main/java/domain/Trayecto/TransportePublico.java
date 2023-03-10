package domain.Trayecto;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class TransportePublico extends MedioTransporte {

    @Expose
    @Column
    private String linea;

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private TipoPublico tipo;

    @Expose
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "transporte_id")
    private List<Parada> paradas;

    public TransportePublico(){
        this.usaCombustible = true;
        this.paradas = new ArrayList<>();
    };

    public TransportePublico(String nombre,String linea, TipoPublico tipo, List<Parada> paradas){
        this.nombre = nombre;
        this.linea = linea;
        this.tipo = tipo;
        this.paradas = paradas;
        this.usaCombustible = true;
    }

    public TransportePublico(int id, String nombre, List<Parada> paradas){
        this.id = id;
        this.nombre = nombre;
        this.paradas = paradas;
    }

    @Override
    public float calcularDistancia(Punto puntoInicio, Punto puntoFin){

        List<Parada> recorrido = this.obtenerRecorrido(puntoInicio, puntoFin);

        float distancia = 0f;

        Parada parada1 = recorrido.get(0);
        for(int i = 1; i <= recorrido.size() - 1; i++){
            Parada parada2 = recorrido.get(i);
            distancia += parada1.distanciaA(parada2);
            parada1 = parada2;
        }

        return distancia;
    }

    private Parada buscarParada(Punto punto)
    {
        Parada parada = null;
        for(Parada p : paradas){
            if(p.getPunto().getLatitud().floatValue() == punto.getLatitud().floatValue() && p.getPunto().getLongitud().floatValue() == punto.getLongitud().floatValue()){
                parada = p;
                break;
            }
        }

        return parada;
    }

    private List<Parada> obtenerRecorrido(Punto a, Punto b){
        Parada pa = this.buscarParada(a);
        Parada pb = this.buscarParada(b);

        return paradas.subList(paradas.indexOf(pa), paradas.indexOf(pb)+1);
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public TipoPublico getTipo() {
        return tipo;
    }

    public void setTipo(TipoPublico tipo) {
        this.tipo = tipo;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }
}
