package controlador

import modelo.Artista
import java.lang.Exception
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
            //println("Imprimir arreglo separado de comas")
            //arregloDatosEnString.forEach { v -> println(v) }
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
                     gananciaAcumulada:Double): Boolean{

        try {
            var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
            listaArtistas.add(Artista(nombre,
                    banda,
                    fechaInicio,
                    cantidadDiscos,
                    gananciaAcumulada,
                    listaArtistas.last().idArtista + 1))
            archivo.escribir(listaArtistas, false)
            return true;
        }catch (e: Exception) {
            return false
        }

    }

    fun contarArtistas(): Int{
        var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
        return listaArtistas.size
    }



    fun encontrarIndiceSegunID( id: Int): Int{
        var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
        var elementoEncontrado: List<Artista> = listaArtistas.filter { artista ->
            return@filter artista.idArtista == id
        }
        try {
            var indice: Int = listaArtistas.indexOf(elementoEncontrado[0])
            return indice
        }catch (e: IndexOutOfBoundsException){
            return -1
        }


    }

    fun actualizarArtista(indice: Int, nuevaCantidadDiscos: Int, nuevaGananciaTotal: Double): Boolean{
       try {
           var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
           listaArtistas[indice].cantidadDiscos = nuevaCantidadDiscos
           listaArtistas[indice].gananciaTotal = nuevaGananciaTotal
           archivo.escribir(listaArtistas, false)
           return true
       }catch (e: Exception){
           return false
       }

    }

    fun eliminarArtista(id:Int): Boolean{

        val index: Int = encontrarIndiceSegunID(id)

        if(index != -1){
            var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
            listaArtistas.removeAt(index)
            archivo.escribir(listaArtistas, false)
        }else{
            return false
        }
        return true;
    }


    fun mostrarArtistas(){
        var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
        if (listaArtistas.size != 0){
            listaArtistas.forEach{artista -> artista.toBeautyString()}
        }else{
            println("No hay artistas")
        }
    }

    fun indicarArtistaSegunID(id: Int): Artista {
        var listaArtistas: ArrayList<Artista> = parsearArtista(archivo.leer())
        return listaArtistas[encontrarIndiceSegunID(id)]
    }

}