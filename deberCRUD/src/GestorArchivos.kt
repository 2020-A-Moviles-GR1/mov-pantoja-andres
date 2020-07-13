import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream

interface GestorArchivos {
    val nombreArchivo: String

    fun escribir(lista: List<Any>, append: Boolean){
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

    fun leer():String {
        val archivo: File = File(nombreArchivo)
        val buffReader: BufferedReader = archivo.bufferedReader()
        val inp: String = buffReader.use { it.readText() }
        return inp
    }

}