package mx.rmr.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.rmr.covid19.api.ServicioCovidApi
import mx.rmr.covid19.model.Pais
import mx.rmr.covid19.model.ServicioPaises
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ListaPaisesVM : ViewModel()
{
    private val modelo = ServicioPaises()
    val arrPaises = MutableLiveData<List<Pais>>()

    // Retrofit, para  descargar datos
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://disease.sh")
            //.addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioCovidApi by lazy {
        retrofit.create(ServicioCovidApi::class.java)
    }

    fun leerDatos() {
        arrPaises.value = modelo.leerPaises()
        descargarDatosPaises()
    }

    private fun descargarDatosPaises() {
        val call = servicioCovidApi.descargarDatosPaises()
        call.enqueue(object: Callback<List<Pais>> {
            override fun onResponse(call: Call<List<Pais>>, response: Response<List<Pais>>) {
                // response son los datos que descargo
                if (response.isSuccessful) {
                    println(response.body())
                    arrPaises.value = response.body()
                } else {
                    println("Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Pais>>, t: Throwable) {
                println("Error, descargando datos ${t.localizedMessage}")
            }
        })
    }

    private fun descargarDatosGlobales() {
        val call = servicioCovidApi.descargarDatosGlobales()
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                // response son los datos que descargo
                if (response.isSuccessful) {
                    println("Datos descargados: ${response.body()}")
                    println(response.code())
                } else {
                    println("Error: ${response.errorBody()}")
                    println(response.code())
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                println("Error, descargando datos ${t.localizedMessage}")
            }
        })
    }
}





