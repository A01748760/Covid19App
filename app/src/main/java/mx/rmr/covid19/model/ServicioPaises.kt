package mx.rmr.covid19.model

// Modelo
class ServicioPaises
{
    fun leerPaises(): List<Pais> {
        return listOf(
            Pais("México"),
            Pais("Estados Unidos"),
            Pais("Canadá"),
            Pais("España"),
            Pais("Argentina"),
        )
    }

    // Retrofit
}
