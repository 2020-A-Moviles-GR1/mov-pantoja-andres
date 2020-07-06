import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class Archivos(val nombreArchivo: String) {

    fun escribir(lista: List<Any>): Unit{
        val archivo: File = File(nombreArchivo)
        val fileOutputStram: FileOutputStream = FileOutputStream(archivo, true)
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

    fun leer():Unit {
        val archivo: File = File(nombreArchivo)
        val buffReader: BufferedReader = archivo.bufferedReader()
        val inp: String = buffReader.use { it.readText() }
        println(inp);
    }
}