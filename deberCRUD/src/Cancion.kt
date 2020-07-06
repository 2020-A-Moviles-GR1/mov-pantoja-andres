import java.util.*

class Cancion(
    val titulo: String,
    val duracionMinutos: Double,
    var numeroDeReproducciones: Int,
    val fechaDeLanzamiento: Date,
    var premiada: Boolean
) {

    override fun toString(): String {
        return "titulo: ${titulo}," +
                "duracion: ${duracionMinutos}," +
                "fecha: ${fechaDeLanzamiento}," +
                "reproducciones: ${numeroDeReproducciones}," +
                "premiada: ${premiada}"
    }

}