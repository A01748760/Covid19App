package mx.rmr.covid19.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// Clase especial, genera toString, get/set, equals,
data class Pais (
    @SerializedName("country")
    val nombre: String,
    @SerializedName("cases")
    val casos: Int = 0,
    @SerializedName("countryInfo")
    val bandera: Map<String, out Any> = mapOf("flag" to 1)
// Agregar la bandera (está dentro de un diccionario, Map<String, Any>, mapOf()
    ) : Serializable //Pasarlo entre fragmentos
