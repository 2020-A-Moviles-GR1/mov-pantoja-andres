package vista

import controlador.ArtistaControlador
import controlador.CancionControlador
import java.time.LocalDate

class ArtistaVista : Validador {
    companion object {
        val artistaControlador: ArtistaControlador = ArtistaControlador()
        val cancionControlador: CancionControlador = CancionControlador()
    }

    fun crearArtista() {
        println("Crear Artista")
        println("Ingresar Nombre")
        var banda: String
        var fecha: String
        var cantidadDiscos: String
        var gananciaAcumulada: String


        val nombre: String = readLine()!!
        do {
            println("¿Es una banda? (true-false)")
            banda = readLine()!!
        } while (!esValidoBoolean(banda))

        do {
            println("Fecha de inicio (yyyy-MM-dd)")
            fecha = readLine()!!
        } while (!esValidoFecha(fecha))

        do {
            println("Cantidad de Discos")
            cantidadDiscos = readLine()!!
        } while (!esValidoInt(cantidadDiscos))

        do {
            println("Ganancia Total")
            gananciaAcumulada = readLine()!!
        } while (!esValidoDouble(gananciaAcumulada))

        artistaControlador.crearArtista(
                nombre,
                banda.toBoolean(),
                LocalDate.parse(fecha),
                cantidadDiscos.toInt(),
                gananciaAcumulada.toDouble()
        ).let { resultado -> if (resultado) println("Artista Creado con exito") else println("Error al crear") }

    }

    fun mostrarArtistas() {
        println("Artistas actuales")
        artistaControlador.mostrarArtistas()
    }

    fun eliminarArtista() {
        println("Eliminación de Artista")
        var entrada: String
        do {
            println("Ingrese el ID del artista a eliminar")
            entrada = readLine()!!
        } while (!esValidoInt(entrada))
        val resultado: Boolean = artistaControlador.eliminarArtista(entrada.toInt())
        if (resultado) {
            println("Artista Eliminado")
            cancionControlador.eliminarCancionPorArtista(entrada.toInt())
                    .let { cancionesEliminadas ->
                        println("$cancionesEliminadas canciones eliminadas")
                    }
        } else println("El artista con el ID mencionado no existe")


    }


    fun actualizar() {
        println("Actualizar Artista")
        var entradaID: String
        do {
            println("Ingrese el ID del artista a Actualizar")
            entradaID = readLine()!!
        } while (!esValidoInt(entradaID))
        val indiceArtista: Int = artistaControlador.encontrarIndiceSegunID(entradaID.toInt())
        if (indiceArtista == -1) {
            println("No se encontró Artista con ese ID")
        } else {
            actualizarArtistaValido(indiceArtista)

        }

    }

    fun actualizarArtistaValido(id: Int) {
        var cantidadDiscos: String
        var gananciaAcumulada: String
        do {
            println("Cantidad de Discos")
            cantidadDiscos = readLine()!!
        } while (!esValidoInt(cantidadDiscos))

        do {
            println("Ganancia Total")
            gananciaAcumulada = readLine()!!
        } while (!esValidoDouble(gananciaAcumulada))

        artistaControlador.actualizarArtista(id, cantidadDiscos.toInt(), gananciaAcumulada.toDouble())
                .let { resultado ->
                    if (resultado) println("Artista Actualizado")
                    else println("Error al actualizar artista")
                }
    }


    override fun esValidoBoolean(boolean: String): Boolean {
        return super.esValidoBoolean(boolean)
    }

    override fun esValidoDouble(double: String): Boolean {
        return super.esValidoDouble(double)
    }

    override fun esValidoFecha(fecha: String): Boolean {
        return super.esValidoFecha(fecha)
    }

    override fun esValidoInt(int: String): Boolean {
        return super.esValidoInt(int)
    }

}