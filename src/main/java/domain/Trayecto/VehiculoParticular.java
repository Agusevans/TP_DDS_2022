package domain.Trayecto;

import com.google.gson.annotations.Expose;
import domain.Trayecto.MedioTransporte;
import domain.Trayecto.TipoCombustible;
import domain.Trayecto.TipoVehiculo;

import javax.persistence.*;

@Table
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class VehiculoParticular extends MedioTransporte {

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private TipoVehiculo tipoVehiculo;

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private TipoCombustible combustible;

    public VehiculoParticular(){
        this.usaCombustible = true;
    };

    public VehiculoParticular(TipoVehiculo vehiculo,TipoCombustible combustible){
        this.tipoVehiculo = vehiculo;
        this.combustible = combustible;
        this.usaCombustible = true;
    };

    public VehiculoParticular(String nombre, TipoVehiculo vehiculo,TipoCombustible combustible){
        this.nombre = nombre;
        this.tipoVehiculo = vehiculo;
        this.combustible = combustible;
        this.usaCombustible = true;
    };
}
