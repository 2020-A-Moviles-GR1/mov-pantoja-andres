package controlador

import modelo.Cancion
import java.time.LocalDate

class CancionControlador {

    fun parsearCancion(arregloDeStrings: List<String>) :ArrayList<Cancion>{
        var acumuladorCanciones: ArrayList<Cancion> = arrayListOf();
        arregloDeStrings.forEach { valor ->
            var arregloDatosEnString: Array<String> = valor.split(",").toTypedArray();
            println("Imprimir arreglo separado de comas")
            arregloDatosEnString.forEach { v -> println(v) }
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

}