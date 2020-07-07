package vista

import java.lang.NumberFormatException

class VistaPrincipal {
    fun iniciar(){
            println("Bienvenido")
            println("Menú de opciones")
            println("1.Artistas")
            println("2.Canciones")
            println("3.Salir")
            println("Ingrese la opción deseada")
            try {
                val opcionPrincipal: Int = readLine()!!.toInt()
                compararOpcionPrincipal(opcionPrincipal)
            }catch (e: NumberFormatException){
                println("La opcion ingresada no es un número")
                print("\u001b[H\u001b[2J")
                iniciar()
            }



    }
    fun compararOpcionPrincipal(opcion: Int){
        if(opcion == 1 ){
            gestionDeArtistas()
        } else if (opcion == 2){
            println("Gestion de Canciones")
        } else if (opcion ==3){
            println("Adios!")
        }else{
            println("Opción no identificada")
            iniciar()
        }
    }

    fun gestionDeArtistas(){
        println("Gestion de Artistas")
        println("1.CREATE")
        println("2.READ")
        println("3.UPDATE")
        println("4.DELETE")
        println("5.SALIR")
        println("Ingrese la opción deseada")
        try {
            val opcionArtista: Int = readLine()!!.toInt()
            compararOpcionArtista(opcionArtista)
        }catch (e: NumberFormatException){
            println("La opcion ingresada no es un número")
            print("\u001b[H\u001b[2J")
            gestionDeArtistas()
        }
    }

    fun compararOpcionArtista(opcion: Int){
        if(opcion == 1 ){
            println("Crear Artista")
        } else if (opcion == 2){
            println("Mostrar Artistas")
        } else if (opcion ==3) {
            println("Actualizar Artista")
        }else if(opcion == 4) {
            println("Eliminar Artista")
        }else if(opcion == 5){
            iniciar()
        }else{
            println("Opción no identificada")
            gestionDeArtistas()
        }
    }

}