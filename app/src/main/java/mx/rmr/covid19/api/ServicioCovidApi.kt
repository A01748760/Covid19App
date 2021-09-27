package mx.rmr.covid19.api

import mx.rmr.covid19.model.Casos
import mx.rmr.covid19.model.Pais
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// 'Descargar' datos de la red
// https://disease.sh/v3/covid-19/all
// https://disease.sh/v3/covid-19/countries
// https://disease.sh/v3/covid-19/historical/mexico?lastdays=5
interface ServicioCovidApi
{
    @GET("v3/covid-19/all")
    fun descargarDatosGlobales(): Call<String>   // No son los datos

    @GET("v3/covid-19/countries")
    fun descargarDatosPaises(): Call<List<Pais>>

    @GET("v3/covid-19/historical/{nombrePais}")
    fun descargarHistoricoPais(@Path("nombrePais") nombre: String,
                               @Query("lastdays") dias: String) : Call<Casos>


}