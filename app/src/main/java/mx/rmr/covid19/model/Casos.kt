package mx.rmr.covid19.model

import com.google.gson.annotations.SerializedName

data class Casos(
    @SerializedName("country")
    val nombre: String,
    @SerializedName("timeline")
    val historico: Map<String, Map<String, Int>>
)
