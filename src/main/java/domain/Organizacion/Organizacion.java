package domain.Organizacion;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import com.google.gson.annotations.Expose;
import domain.*;
import domain.Actividad.*;
import domain.Organizacion.Comunicacion.Email;
import domain.Trayecto.CalculadorHUTrayectos;
import domain.Trayecto.Punto;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.IOException;
import java.util.*;

@Entity
@Table(name = "Organizacion")
public class Organizacion extends EntidadPersistente{

    @Expose
    @Column
    private String razonSocial;

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private TipoOrg tipo;

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private ClasificacionOrg clasificacion;

    @Expose
    @OneToOne(cascade = CascadeType.ALL)
    private Punto ubicacion;

    @Expose
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "organizacion_id")
    private List<Sector> sectores;

    @Transient
    private List<DatosActividad> datosActividad;

    @Expose
    @ManyToOne
    @JoinColumn(name = "AgenteSectorial_id", referencedColumnName = "id")
    private AgenteSectorial agente;

    @Transient
    private CalculadorHUTrayectos calculadorHUTrayectos;

    public Organizacion(){
        this.sectores = new ArrayList<>();
        this.datosActividad = new ArrayList<>();
    };

    public Organizacion(String razonSocial, TipoOrg tipo, ClasificacionOrg clasificacion,Punto ubicacion) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.clasificacion = clasificacion;
        this.ubicacion = ubicacion;
        this.sectores = new ArrayList<>();
        this.datosActividad = new ArrayList<>();
    }

    public List<Miembro> obtenerMiembros() {
        List<Miembro> miembrosList = new ArrayList<>();
        for (Sector sector : sectores) {
            miembrosList.addAll(sector.getMiembros());
        }

        return miembrosList;
    }

    public void cargarMedicion(DatosActividad da){
        this.datosActividad.add(da);
    }

    public void aceptarMiembro(Miembro miembro, Sector sector) throws Exception {

        if(this.obtenerMiembros().contains(miembro)){
            throw new Exception("El miembro no puede estar en mas de un sector");
        }

        sector.agregarMiembro(miembro);
        miembro.agregarOrganizacion(this);
    }

    public void agregarSector(Sector sector){
        this.sectores.add(sector);
    }

    public  void agregarSectores(Sector ... sector){
        Collections.addAll(this.sectores, sector);
    }

    public Float obtenerHUMedible(Collection<Medible> mediciones) {
        Float total = 0f;
        for (Medible medicion:mediciones) {
            total += medicion.calcularHU();
        }
        return total;
    }

    public Float obtenerHUDA(List<DatosActividad> datosActividadList) {
        Float total = 0f;
        for (DatosActividad datoActividad:datosActividadList) {
            total += datoActividad.calcularHU();
        }
        return total;
    }

    public Float obtenerHUTotal() throws IOException {
        return this.obtenerHUDA(this.datosActividad) + this.obtenerHUMiembros();
    }

    public float obtenerHUMiembro(Miembro miembro) throws IOException {
        return this.getCalculadorHUTrayectos().calcularHUMiembro(miembro);
    }

    public float obtenerHUMiembros() throws IOException {
        List<Miembro> miembros = this.obtenerMiembros();
        return this.getCalculadorHUTrayectos().calcularHUMiembros(miembros);
    }

    private void cargarReporteMediciones(ReporteHU reporte){
        for(DatosActividad da : this.datosActividad) {
            Medicion medicion = da.getMedicion();
            TipoConsumo tc = medicion.getTipoConsumo();
            float valor = medicion.getValor();
            float fe = tc.getFactorEmision().getValor();
            reporte.agregarHUMedicion(da.getActividad(), tc, valor * fe);
        }
    }

    private void cargarReporteTrayectos(ReporteHU reporte) throws IOException {
        for(Sector sector : this.sectores){
            for(Miembro miembro : sector.getMiembros()){
                float huMiembro = this.obtenerHUMiembro(miembro);
                reporte.agregarHUTrayecto(sector, miembro, huMiembro);
            }
        }
    }

    public ReporteHU obtenerReporteHU() throws IOException {
        ReporteHU reporte = new ReporteHU();
        this.cargarReporteMediciones(reporte);
        this.cargarReporteTrayectos(reporte);
        reporte.sumarTotal();
        return reporte;
    }

    public void mostrarReporteHU() throws IOException {
        ReporteHU reporte = this.obtenerReporteHU();
        System.out.println("MUESTRA DE REPORTE DE HU DE LA ORGANIZACION: " + this.getRazonSocial());
        reporte.mostrarReporte();
    }

    public void removerAgente(){
        this.agente = null;
    }

    public void notificarAgente() throws IOException {
        Email email = new Email();
        email.notificar(agente,this.obtenerHUTotal());
    }

    //Getters & setters
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public TipoOrg getTipo() {
        return tipo;
    }
    public void setTipo(TipoOrg tipo) {
        this.tipo = tipo;
    }
    public ClasificacionOrg getClasificacion() {
        return clasificacion;
    }
    public void setClasificacion(ClasificacionOrg clasificacion) {
        this.clasificacion = clasificacion;
    }
    public Punto getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(Float latitud, Float longitud) {
        this.ubicacion =new Punto(latitud,longitud);
    }
    public List<Sector> getSectores() {
        return sectores;
    }
    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }

    public void setUbicacion(Punto ubicacion) {
        this.ubicacion = ubicacion;
    }

    public AgenteSectorial getAgente() {
        return agente;
    }

    public void setAgente(AgenteSectorial territorio) {
        this.agente = territorio;
    }

    public void setDatosActividad(List<DatosActividad> da){ this.datosActividad = da;}
    public List<DatosActividad> getDatosActividad(){ return this.datosActividad;}

    public CalculadorHUTrayectos getCalculadorHUTrayectos() {
        if ( this.calculadorHUTrayectos == null) {
            this.calculadorHUTrayectos = new CalculadorHUTrayectos(this);
        }
        return calculadorHUTrayectos;
    }

    public void setActividadTrayectos(Actividad actividad){
        this.getCalculadorHUTrayectos().setActividadTrayectos(actividad);
    }
}
