package modelo

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

open class Artista constructor(var nombre: String = "",
                               var banda: Boolean = true,
                               var fechaInicio: LocalDate = LocalDate.parse("0001-01-01"),
                               var cantidadDiscos: Int = -1,
                               var gananciaTotal: Double = 0.0,
                               var idArtista: Int = -1){
    val nombreArchivo: String = "./src/archivos/artista.txt"
    val formatoDeFecha : String = "yyyy-MM-dd"
    var listaCanciones: List<Cancion> = listOf<Cancion>()

    override fun toString(): String {

        return "${nombre},${banda},${fechaInicio},${cantidadDiscos},${gananciaTotal},${idArtista}";
    }

    fun toBeautyString(){
        println("ID: $idArtista\tNombre: $nombre\tBanda: $banda\tFecha de Inicio: $fechaInicio\t" +
                "Cantidad de Discos: $cantidadDiscos\tGanancias: $gananciaTotal")
    }

}