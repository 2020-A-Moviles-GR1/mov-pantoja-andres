package vista

import java.lang.NumberFormatException

class VistaPrincipal {
    companion object {
        val artistaVista: ArtistaVista = ArtistaVista()
        val cancionVista: CancionVista = CancionVista()

    }

    fun iniciar() {
        println("Bienvenido")
        println("Menú de opciones")
        println("1.Artistas")
        println("2.Canciones")
        println("3.Salir")
        println("Ingrese la opción deseada")
        try {
            val opcionPrincipal: Int = readLine()!!.toInt()
            compararOpcionPrincipal(opcionPrincipal)
        } catch (e: NumberFormatException) {
            println("La opcion ingresada no es un número")
            print("\u001b[H\u001b[2J")
            iniciar()
        }


    }

    fun compararOpcionPrincipal(opcion: Int) {
        if (opcion == 1) {
            gestionDeArtistas()
        } else if (opcion == 2) {
            gestionDeCanciones()
        } else if (opcion == 3) {
            println("Adios!")
        } else {
            println("Opción no identificada")
            iniciar()
        }
    }

    fun gestionDeArtistas() {
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
        } catch (e: NumberFormatException) {
            println("La opcion ingresada no es un número")
            print("\u001b[H\u001b[2J")
            gestionDeArtistas()
        }
    }

    fun compararOpcionArtista(opcion: Int) {
        if (opcion == 1) {
            artistaVista.crearArtista()
            gestionDeArtistas()
        } else if (opcion == 2) {
            artistaVista.mostrarArtistas()
            gestionDeArtistas()
        } else if (opcion == 3) {
            artistaVista.actualizar()
            gestionDeArtistas()
        } else if (opcion == 4) {
            artistaVista.eliminarArtista()
            gestionDeArtistas()
        } else if (opcion == 5) {
            iniciar()
        } else {
            println("Opción no identificada")
            gestionDeArtistas()
        }
    }

    fun gestionDeCanciones() {
        println("Gestion de Canciones")
        println("1.CREATE")
        println("2.READ")
        println("3.UPDATE")
        println("4.DELETE")
        println("5.SALIR")
        println("Ingrese la opción deseada")
        try {
            val opcionCanion: Int = readLine()!!.toInt()
            compararOpcionCancion(opcionCanion)
        } catch (e: NumberFormatException) {
            println("La opcion ingresada no es un número")
            print("\u001b[H\u001b[2J")
            gestionDeArtistas()
        }
    }

    fun compararOpcionCancion(opcion: Int) {
        if (opcion == 1) {
            cancionVista.crearCancion()
            gestionDeCanciones()
        } else if (opcion == 2) {
            cancionVista.mostrarCanciones()
            gestionDeCanciones()
        } else if (opcion == 3) {
            cancionVista.actualizar()
            gestionDeCanciones()
        } else if (opcion == 4) {
            cancionVista.eliminarCancion()
            gestionDeCanciones()
        } else if (opcion == 5) {
            iniciar()
        } else {
            println("Opción no identificada")
            gestionDeCanciones()
        }
    }

}