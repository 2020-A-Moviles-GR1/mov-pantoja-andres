package modelo

import modelo.Artista
import java.time.LocalDate
import java.util.*

class Cancion(
    var titulo: String,
    var premiada: Boolean,
    var fechaDeLanzamiento: LocalDate,
    var numeroDeReproducciones: Int,
    var duracionMinutos: Double,
    var idCancion: Int,
    var idArtista: Int
) {
    val nombreArchivo: String = "./src/archivos/cancion.txt"

    override fun toString(): String {
        return "${titulo},${premiada},${fechaDeLanzamiento},${numeroDeReproducciones},${duracionMinutos},${idCancion},${idArtista}";
    }

    fun toBeautyString(){
        println("ID: $idCancion\tTítulo: $titulo\tPremiada: $premiada\tFecha de Lanzamiento: $fechaDeLanzamiento\t" +
                "Número de Reproducciones: $numeroDeReproducciones\tDuración en Minutos: $duracionMinutos")
    }

}