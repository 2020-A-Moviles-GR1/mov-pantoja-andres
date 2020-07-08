package vista

import controlador.ArtistaControlador
import controlador.CancionControlador

import java.time.LocalDate

class CancionVista: Validador {

    companion object{
        val artistaControlador: ArtistaControlador = ArtistaControlador()
        val cancionControlador: CancionControlador = CancionControlador()
    }

    fun crearCancion(){

        var idArtista: String
        var premiada: String
        var fecha: String
        var numeroReproducciones: String
        var duracionMinutos: String

        println("Crear Cancion")
        println("Ingresar Título")
        val titulo: String = readLine()!!
        do {
            println("¿Ha sido premiada? (true-false)")
            premiada= readLine()!!
        }while(!esValidoBoolean(premiada))

        do {
            println("Fecha de lanzamiento (yyyy-MM-dd)")
            fecha = readLine()!!
        }while(!esValidoFecha(fecha))

        do {
            println("Número de Reproducciones")
            numeroReproducciones = readLine()!!
        }while(!esValidoInt(numeroReproducciones))

        do {
            println("Duracion en minutos (XX.XX)")
            duracionMinutos = readLine()!!
        }while(!esValidoDouble(duracionMinutos))

        do {
            println("ID Artista (Si el ID no existe, se pedirá nuevamente)")
            idArtista = readLine()!!
        }while(!existeArtista(idArtista) )




        cancionControlador.crearCancion(
                titulo,
                premiada.toBoolean(),
                LocalDate.parse(fecha),
                numeroReproducciones.toInt(),
                duracionMinutos.toDouble(),
                idArtista.toInt()
        ).let{resultado -> if(resultado) println("Cancion Creado con exito") else println("Error al crear")}

    }


    fun mostrarCanciones(){
        println("Canciones actuales")
        cancionControlador.mostrarCanciones()
    }

    fun eliminarCancion(){
        println("Eliminación de Cancion")
        var entrada: String
        do{
            println("Ingrese el ID de la Cancion a eliminar")
            entrada = readLine()!!
        }while(!esValidoInt(entrada))
        cancionControlador.eliminarCancion(entrada.toInt())
                .let{respuesta ->
                    if(respuesta) println("Cancion Eliminada")
                    else println("La Cancion con el ID mencionado no existe")
                }


    }

    fun actualizar(){
        println("Actualizar Cancion")
        var entradaID: String
        do{
            println("Ingrese el ID de la Cancion a Actualizar")
            entradaID = readLine()!!
        }while(!esValidoInt(entradaID))
        val indiceCancion: Int = cancionControlador.encontrarIndiceSegunID(entradaID.toInt())
        if(indiceCancion == -1){
            println("No se encontró la canción con ese ID")
        }else{
            actualizarCancionValida(indiceCancion)

        }

    }

    fun actualizarCancionValida(id: Int){
        var esPremiada: String
        var numeroDeReproducciones: String
        do {
            println("¿Es premiada?")
            esPremiada = readLine()!!
        }while(!esValidoBoolean(esPremiada))

        do {
            println("Cantidad de reproducciones:")
            numeroDeReproducciones = readLine()!!
        }while(!esValidoInt(numeroDeReproducciones))

        cancionControlador.actualizarCancion(id,esPremiada.toBoolean(), numeroDeReproducciones.toInt())
                .let{resultado -> if (resultado) println("Cancion Actualizada")
                else println("Error al actualizar Cancion")
                }
    }





    fun existeArtista(idArtista:String): Boolean{
        if(esValidoInt(idArtista)){
            if(artistaControlador.encontrarIndiceSegunID(idArtista.toInt()) == -1)  return false
            else return true
        }else{
            return false
        }
    }

    override fun esValidoFecha(fecha: String): Boolean {
        return super.esValidoFecha(fecha)
    }

    override fun esValidoBoolean(boolean: String): Boolean {
        return super.esValidoBoolean(boolean)
    }

    override fun esValidoDouble(double: String): Boolean {
        return super.esValidoDouble(double)
    }

    override fun esValidoInt(int: String): Boolean {
        return super.esValidoInt(int)
    }
}