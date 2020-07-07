package controlador

import modelo.Artista
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class ArtistaControlador{
    companion object{
        val archivo = Archivos("./src/archivos/artistas.txt")

    }
    fun parsearArtista(arregloDeStrings: List<String>) :ArrayList<Artista>{
        var acumuladorArtistas: ArrayList<Artista> = arrayListOf();
        arregloDeStrings.forEach { valor ->
            var arregloDatosEnString: Array<String> = valor.split(",").toTypedArray();
            println("Imprimir arreglo separado de comas")
            arregloDatosEnString.forEach { v -> println(v) }
            acumuladorArtistas.add(Artista(
                    arregloDatosEnString[0],
                    arregloDatosEnString[1].toBoolean(),
                    LocalDate.parse(arregloDatosEnString[2]),
                    arregloDatosEnString[3].toInt(),
                    arregloDatosEnString[4].toDouble(),
                    arregloDatosEnString[5].toInt()
                    )
            );
        }
        return acumuladorArtistas;
    }

    fun crearArtista(nombre:String,
                     banda:Boolean,
                     fechaInicio: LocalDate,
                     cantidadDiscos: Int,
                     gananciaAcumulada:Double){

        var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
        listaArtistas.add(Artista(nombre,
                banda,
                fechaInicio,
                cantidadDiscos,
                gananciaAcumulada,
                listaArtistas.last().idArtista + 1))
        archivo.escribir(listaArtistas, false)
    }

    fun contarArtistas(): Int{
        var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
        return listaArtistas.size
    }

    fun actualizarArtista(id: Int,
                          nombre:String,
                          banda:Boolean,
                          fechaInicio: LocalDate,
                          cantidadDiscos: Int,
                          gananciaAcumulada:Double
    ){
        var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
        val nuevoArtista: Artista = Artista(nombre,banda, fechaInicio, cantidadDiscos, gananciaAcumulada, id)

    }

    fun encontrarIndiceSegunID(listaArtistas: ArrayList<Artista>, id: Int): Int{
        var elementoEncontrado: List<Artista> = listaArtistas.filter { artista ->
            return@filter artista.idArtista == id
        }
        var indice: Int = listaArtistas.indexOf(elementoEncontrado[0])
        return indice
    }

    fun eliminarArtista(id:Int): Boolean{
        var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
        val index: Int = encontrarIndiceSegunID(listaArtistas, id)
        if(index != -1){
            listaArtistas.removeAt(index)
            archivo.escribir(listaArtistas, false)
        }else{
            return false
        }
        return true;
    }
}