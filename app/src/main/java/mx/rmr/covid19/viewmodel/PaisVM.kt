package mx.rmr.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.rmr.covid19.api.ServicioCovidApi
import mx.rmr.covid19.model.Casos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//ViewModel del Fragmento Pais
class PaisVM : ViewModel()
{
    var arrContagios = MutableLiveData<ArrayList<Int>>()
    //var muertes = MutableLiveData<Int>()
    //var recuperados = MutableLiveData<Int>()
    //var totales = MutableLiveData<Int>()

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


    fun descargarHistorico(nombre: String, dias: String) {
        val call = servicioCovidApi.descargarHistoricoPais(nombre, dias)
        call.enqueue(object : Callback<Casos> {
            override fun onResponse(call: Call<Casos>, response: Response<Casos>) {
                if (response.isSuccessful) {
                    println("Datos: ${response.body()}")
                    arrContagios.value = ArrayList(response.body()?.historico?.get("cases")?.values?.sorted())
                    //muertes.value = response.body()?.historico?.get("deaths")?.values?.last()

                }
            }

            override fun onFailure(call: Call<Casos>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}