package modelo

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

open class Artista constructor(val nombre: String,
              val banda: Boolean,
              val fechaInicio: LocalDate,
              var cantidadDiscos: Int,
              var gananciaTotal: Double,
              val idArtista: Int){
    val nombreArchivo: String = "./src/archivos/artista.txt"
    val formatoDeFecha : String = "yyyy-MM-dd"
    var listaCanciones: List<Cancion> = listOf<Cancion>()

    override fun toString(): String {

        return "${nombre},${banda},${fechaInicio},${cantidadDiscos},${gananciaTotal},${idArtista}";
    }

}