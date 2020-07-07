package modelo

import modelo.Artista
import java.time.LocalDate
import java.util.*

class Cancion(
    val titulo: String,
    var premiada: Boolean,
    val fechaDeLanzamiento: LocalDate,
    var numeroDeReproducciones: Int,
    val duracionMinutos: Double,
    val idCancion: Int,
    val idArtista: Int
) {
    val nombreArchivo: String = "./src/archivos/cancion.txt"

    override fun toString(): String {
        return "${titulo},${premiada},${fechaDeLanzamiento},${numeroDeReproducciones},${duracionMinutos},${idCancion},${idArtista}";
    }

}