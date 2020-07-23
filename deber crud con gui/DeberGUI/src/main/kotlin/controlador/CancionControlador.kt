package controlador


import modelo.Artista
import modelo.Cancion
import tornadofx.Controller
import tornadofx.SortedFilteredList
import tornadofx.toObservable
import java.lang.Exception
import java.time.LocalDate

class CancionControlador: Controller() {

    companion object{
        val archivo = Archivos("./src/main/kotlin/archivos/canciones.txt")
        val artistaControlador: ArtistaControlador = ArtistaControlador()
    
    }
    var canciones: SortedFilteredList<Cancion> = SortedFilteredList(items = devolverCanciones().toObservable())
    
    fun parsearCancion(arregloDeStrings: List<String>) :ArrayList<Cancion>{
        var acumuladorCanciones: ArrayList<Cancion> = arrayListOf();
        arregloDeStrings.forEach { valor ->
            var arregloDatosEnString: Array<String> = valor.split(",").toTypedArray();

            acumuladorCanciones.add(Cancion(
                    arregloDatosEnString[0],
                    arregloDatosEnString[1].toBoolean(),
                    LocalDate.parse(arregloDatosEnString[2]),
                    arregloDatosEnString[3].toInt(),
                    arregloDatosEnString[4].toDouble(),
                    arregloDatosEnString[5].toInt(),
                    arregloDatosEnString[6].toInt()
            )
            );
        }
        return acumuladorCanciones;
    }

    fun devolverCanciones(): List<Cancion>{
        var listaCanciones: ArrayList<Cancion> = parsearCancion(CancionControlador.archivo.leer())
        return listaCanciones.toList()
    }

    fun crearCancion(titulo:String,
                     premiada:Boolean,
                     fechaDeLanzamiento: LocalDate,
                     numeroDeReproducciones: Int,
                     duracionMinutos:Double,
                     idArtista: Int): Boolean{

        try {
            var listaCanciones: ArrayList<Cancion> = parsearCancion(CancionControlador.archivo.leer())
            val nuevaCancion: Cancion =Cancion(titulo,
                premiada,
                fechaDeLanzamiento,
                numeroDeReproducciones,
                duracionMinutos,
                listaCanciones.last().idCancion + 1,
                idArtista)
            listaCanciones.add(nuevaCancion);
            canciones.add(nuevaCancion)
            archivo.escribir(listaCanciones, false)

            return true;
        }catch (e: Exception) {
            return false
        }

    }

    fun quitarDeCanciones(cancion: Cancion ){
        canciones.remove(cancion)
    }

    fun modificarCancion(cancionVieja: Cancion, nuevoValorPremiado: Boolean, nuevaCantidadDeReproducciones: Int){
        var cancionNueva: Cancion = Cancion(
            cancionVieja.titulo,
            nuevoValorPremiado,
            cancionVieja.fechaDeLanzamiento,
            nuevaCantidadDeReproducciones,
            cancionVieja.duracionMinutos,
            cancionVieja.idCancion,
            cancionVieja.idArtista
        )
        with(canciones){
            remove(cancionVieja)
            add(cancionNueva)
        }
    }

    fun contarCanciones(): Int{
        var listaCanciones: ArrayList<Cancion> = parsearCancion(CancionControlador.archivo.leer())
        return listaCanciones.size
    }

    fun quitarPorIDArtista(idArtista: Int){
        var cancionesDelArtista: List<Cancion> = mostrarCancionesDeArtista(idArtista)
        cancionesDelArtista.forEach{c -> println(c.toString())}
        with(canciones){
            removeAll(cancionesDelArtista)
        }

    }




    fun encontrarIndiceSegunID( id: Int): Int{
        var listaCanciones: ArrayList<Cancion> = parsearCancion(CancionControlador.archivo.leer())
        var elementoEncontrado: List<Cancion> = listaCanciones.filter { Cancion ->
            return@filter Cancion.idCancion == id
        }
        try {
            var indice: Int = listaCanciones.indexOf(elementoEncontrado[0])
            return indice
        }catch (e: IndexOutOfBoundsException){
            return -1
        }


    }

    fun actualizarCancion(indice: Int, nuevoValorPremiada: Boolean, nuevoNumeroDeReproducciones: Int): Boolean{
        try {
            var listaCanciones: ArrayList<Cancion> = parsearCancion(CancionControlador.archivo.leer())
            listaCanciones[indice].premiada = nuevoValorPremiada
            listaCanciones[indice].numeroDeReproducciones = nuevoNumeroDeReproducciones
            canciones[indice].premiada = nuevoValorPremiada
            canciones[indice].numeroDeReproducciones = nuevoNumeroDeReproducciones
            CancionControlador.archivo.escribir(listaCanciones, false)
            return true
        }catch (e: Exception){
            return false
        }

    }

    fun eliminarCancion(id:Int): Boolean{

        val index: Int = encontrarIndiceSegunID(id)

        if(index != -1){
            var listaCanciones: ArrayList<Cancion> = parsearCancion(CancionControlador.archivo.leer())
            listaCanciones.removeAt(index)
            CancionControlador.archivo.escribir(listaCanciones, false)
        }else{
            return false
        }
        return true;
    }

    fun mostrarCancionesDeArtista(idArtista: Int): List<Cancion>{
        var listaCanciones: ArrayList<Cancion> = parsearCancion(CancionControlador.archivo.leer())
        var cancionesFiltradas: List<Cancion> =  listaCanciones
            .filter { cancion ->
                //println("Cancion idArtista ${cancion.idArtista}" )
                ///println("idArtista $idArtista")
                return@filter cancion.idArtista == idArtista
            }
        //cancionesFiltradas.forEach{c -> println(c.toString())}
        //println(cancionesFiltradas.size.toString() + " Size")
        return cancionesFiltradas
    }

    fun eliminarCancionPorArtista(idArtista: Int): Int{
        var listaCanciones: ArrayList<Cancion> = parsearCancion(CancionControlador.archivo.leer())
        var cancionesFiltradas: List<Cancion> =  listaCanciones
                .filter { cancion ->
                    return@filter cancion.idArtista != idArtista
                }
        val cancionesEliminadas: Int = listaCanciones.size - cancionesFiltradas.size
        archivo.escribir(cancionesFiltradas, false)
        return cancionesEliminadas
    }


    fun mostrarCanciones(){
        var listaCanciones: ArrayList<Cancion> = parsearCancion(CancionControlador.archivo.leer())
        if (listaCanciones.size != 0){
            listaCanciones.forEach{cancion ->
                cancion.toBeautyString()
                val artista: Artista = artistaControlador.indicarArtistaSegunID(cancion.idArtista)
                println("Artista: ${artista.nombre}")
            }
        }else{
            println("No hay Canciones")
        }

    }

}