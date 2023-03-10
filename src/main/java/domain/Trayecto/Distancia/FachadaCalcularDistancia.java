package domain.Trayecto.Distancia;

import domain.Trayecto.Punto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface FachadaCalcularDistancia {

    @GET("paises")
    Call<ListadoDePaises> paises(@Query("offset") int offset);

    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("offset") int offset);

    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("offset") int offset, @Query("paisId") int paisId);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("offset") int offset);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("offset") int offset, @Query("provinciaId") int provinciaId);

    @GET("localidades")
    Call<ListadoDeLocalidades> localidades(@Query("offset") int offset);

    @GET("localidades")
    Call<ListadoDeLocalidades> localidades(@Query("offset") int offset, @Query("municipíoId") int municipioId);

    @GET("distancia")
    Call<Distancia> distancia(@Query("localidadOrigenId") int localidadOrigenId,
                              @Query("calleOrigen") String calleOrigen,
                              @Query("alturaOrigen") int alturaOrigen,
                              @Query("localidadDestinoId") int localidadDestinoId,
                              @Query("calleDestino") String calleDestino,
                              @Query("alturaDestino") int alturaDestino,
                              @Header("Authorization") String authHeader
                              );
}
