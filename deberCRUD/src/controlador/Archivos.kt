package controlador

import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream

class Archivos(var nombreArchivo: String) {

    fun escribir(lista: List<Any>, append: Boolean): Unit{
        val archivo: File = File(nombreArchivo)
        val fileOutputStram: FileOutputStream = FileOutputStream(archivo, append)
        fileOutputStram
                .bufferedWriter()
                .use{ out ->
                    lista.forEach{
                        instancia ->
                        out.write(instancia.toString()+ "\n")
                    }
                }

        println("Writed to file: ${nombreArchivo}")
    }

    fun leer():ArrayList<String> {
        val archivo: File = File(nombreArchivo)
        val buffReader: BufferedReader = archivo.bufferedReader()
        val inp: String = buffReader.use { it.readText() }
        var arregloDeStrings:ArrayList<String> = inp.split("\n").toTypedArray().toCollection(ArrayList());
        println("arreglooooo 1 : " + arregloDeStrings.size.toString())
        arregloDeStrings.removeAt(arregloDeStrings.size - 1)
        println("arreglooooo 2 : " + arregloDeStrings.size.toString())
        //println(inp);
        //println("lista de strings: \n")

        return  arregloDeStrings;
    }
}