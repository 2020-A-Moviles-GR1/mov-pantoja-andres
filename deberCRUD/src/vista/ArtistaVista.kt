package vista

import java.time.LocalDate

class ArtistaVista: Validador {
    fun crearArtista(){
        println("Crear Artista")
        println("Ingresar Nombre")
        val nombre: String = readLine()!!
        do {
            println("¿Es una banda? (true-false)")
            val banda: String = readLine()!!
        }while(!esValidoBoolean(banda))

        do {
            println("Fecha de inicio (yyyy-MM-dd)")
            val fecha: String = readLine()!!
        }while(!esValidoFecha(fecha))

        do {
            println("Cantidad de Discos")
            val cantidadDiscos: String = readLine()!!
        }while(!esValidoInt(cantidadDiscos))

        do {
            println("Ganancia Total")
            val gananciaTotal: String = readLine()!!
        }while(!esValidoDouble(gananciaTotal))



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