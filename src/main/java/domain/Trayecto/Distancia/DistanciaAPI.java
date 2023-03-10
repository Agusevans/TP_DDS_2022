package domain.Trayecto.Distancia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

import java.io.IOException;

public class DistanciaAPI {
    private static DistanciaAPI instancia = null;
    private static final String urlAPI = "https://ddstpa.com.ar/api/";
    private Retrofit retrofit;

    private DistanciaAPI(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static DistanciaAPI getInstancia(){
        if(instancia == null){
            instancia = new DistanciaAPI();
        }
        return instancia;
    }

    public ListadoDePaises listadoDePaises(int offset) throws IOException {
        FachadaCalcularDistancia serviceDistancia = this.retrofit.create(FachadaCalcularDistancia.class);
        Call<ListadoDePaises> requestPaises = serviceDistancia.paises(offset);
        Response<ListadoDePaises> responsePaises = requestPaises.execute();
        return responsePaises.body();
    }

    public ListadoDeProvincias listadoDeProvincias(int offset) throws IOException {
        FachadaCalcularDistancia serviceDistancia = this.retrofit.create(FachadaCalcularDistancia.class);
        Call<ListadoDeProvincias> requestProvincias = serviceDistancia.provincias(offset);
        Response<ListadoDeProvincias> responseProvincias = requestProvincias.execute();
        return responseProvincias.body();
    }

    public ListadoDeProvincias listadoDeProvincias(int offset, int paisId) throws IOException {
        FachadaCalcularDistancia serviceDistancia = this.retrofit.create(FachadaCalcularDistancia.class);
        Call<ListadoDeProvincias> requestProvincias = serviceDistancia.provincias(offset, paisId);
        Response<ListadoDeProvincias> responseProvincias = requestProvincias.execute();
        return responseProvincias.body();
    }

    public ListadoDeMunicipios listadoDeMunicipios(int offset) throws IOException {
        FachadaCalcularDistancia serviceDistancia = this.retrofit.create(FachadaCalcularDistancia.class);
        Call<ListadoDeMunicipios> requestMunicipios = serviceDistancia.municipios(offset);
        Response<ListadoDeMunicipios> responseMunicipios = requestMunicipios.execute();
        return responseMunicipios.body();
    }

    public ListadoDeMunicipios listadoDeMunicipios(int offset, int provinciaId) throws IOException {
        FachadaCalcularDistancia serviceDistancia = this.retrofit.create(FachadaCalcularDistancia.class);
        Call<ListadoDeMunicipios> requestMunicipios = serviceDistancia.municipios(offset, provinciaId);
        Response<ListadoDeMunicipios> responseMunicipios = requestMunicipios.execute();
        return responseMunicipios.body();
    }

    public ListadoDeLocalidades listadoDeLocalidades(int offset) throws IOException {
        FachadaCalcularDistancia serviceDistancia = this.retrofit.create(FachadaCalcularDistancia.class);
        Call<ListadoDeLocalidades> requestLocalidades = serviceDistancia.localidades(offset);
        Response<ListadoDeLocalidades> responseLocalidades = requestLocalidades.execute();
        return responseLocalidades.body();
    }

    public ListadoDeLocalidades listadoDeLocalidades(int offset, int municipioId) throws IOException {
        FachadaCalcularDistancia serviceDistancia = this.retrofit.create(FachadaCalcularDistancia.class);
        Call<ListadoDeLocalidades> requestLocalidades = serviceDistancia.localidades(offset, municipioId);
        Response<ListadoDeLocalidades> responseLocalidades = requestLocalidades.execute();
        return responseLocalidades.body();
    }

    public Distancia distancia(int localidadOrigenId,
                               String calleOrigen,
                               int alturaOrigen,
                               int localidadDestinoId,
                               String calleDestino,
                               int alturaDestino) throws IOException {
        FachadaCalcularDistancia serviceDistancia = this.retrofit.create(FachadaCalcularDistancia.class);
        Call<Distancia> requestDistancia = serviceDistancia.distancia(localidadOrigenId, calleOrigen, alturaOrigen, localidadDestinoId, calleDestino, alturaDestino, "Bearer By4KlRdZrTwxG5oRIBUXLuKwk2ZzwzWYbYZlGifB4Tc=");
        Response<Distancia> responseDistancia = requestDistancia.execute();
        return responseDistancia.body();
    }
}