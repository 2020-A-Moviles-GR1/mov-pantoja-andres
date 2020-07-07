import controlador.Archivos
import controlador.ArtistaControlador
import controlador.CancionControlador
import modelo.Artista
import modelo.Cancion
import vista.VistaPrincipal
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

fun main(args:Array<String>){
    /*println("Tamo <3");
    val art1: modelo.Artista = modelo.Artista("art1", true, Date(), 2, 2000.00)
    println(art1.toString());
    val canc1: modelo.Cancion = modelo.Cancion("tit", 2.5, 100, Date(), false)
    println(canc1.toString());
    */
    val listaArtisa: List<Artista> = listOf(
            Artista("art1", true, LocalDate.parse("2017-01-01"), 1, 1000.00, 1),
            Artista("art2", true, LocalDate.parse("2017-02-02"), 2, 2000.00, 2),
            Artista("art3", false, LocalDate.parse("2013-03-03"), 3, 3000.00, 3),
            Artista("art4", true, LocalDate.parse("2014-04-04"), 4, 4000.00, 4)
    )
    val listaCanciones: List<Cancion> = listOf<Cancion>(
            Cancion("art1", true, LocalDate.parse("2019-01-01"), 1, 1000.00, 1, 1),
            Cancion("art2", true, LocalDate.parse("2012-02-02"), 2, 2000.00, 2,2),
            Cancion("art3", false, LocalDate.parse("2013-03-03"), 3, 3000.00, 3,3),
            Cancion("art4", true, LocalDate.parse("2014-04-04"), 4, 4000.00, 4, 4)
    )

//    val arch1 = Archivos("./src/archivos/artistas.txt")
//    arch1.escribir(listaArtisa, false);
//    /*arch1.nombreArchivo = "./src/archivos/canciones.txt"
//    arch1.escribir(listaCanciones, false)*/
//    val arregloDeStrings: ArrayList<String> = arch1.leer();
//    arregloDeStrings.forEach { v -> println(v)}
//    val controlaArtista = ArtistaControlador()
//    val arraylistArtistas: ArrayList<Artista> = controlaArtista.parsearArtista(arregloDeStrings)
//    println("***************")
//    arraylistArtistas.forEach{artista -> println(artista.toString())}
//
    /*
    val arch1 = Archivos("./src/archivos/canciones.txt")
    arch1.escribir(listaCanciones, false);
    /*arch1.nombreArchivo = "./src/archivos/canciones.txt"
    arch1.escribir(listaCanciones, false)*/
    val arregloDeStrings: ArrayList<String> = arch1.leer();
    //arregloDeStrings.forEach { v -> println(v)}
    val controlaCancion = CancionControlador()
    val arraylistArtistas: ArrayList<Cancion> = controlaCancion.parsearCancion(arregloDeStrings)
    println("***************")
    arraylistArtistas.forEach{cancion -> println(cancion.toString())}*/

    //val vista: VistaPrincipal = VistaPrincipal()
    //vista.iniciar()
    var ac = ArtistaControlador()
    ac.eliminarArtista(2)



}