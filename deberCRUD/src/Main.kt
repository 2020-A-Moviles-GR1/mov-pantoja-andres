import java.util.*

fun main(args:Array<String>){
    /*println("Tamo <3");
    val art1: Artista = Artista("art1", true, Date(), 2, 2000.00)
    println(art1.toString());
    val canc1: Cancion = Cancion("tit", 2.5, 100, Date(), false)
    println(canc1.toString());
    */
    val listaArtisa: List<Artista> = listOf(
            Artista("art1", true, Date(2017,1,1), 1, 1000.00),
            Artista("art2", true, Date(2017,2,2), 2, 2000.00),
            Artista("art3", false, Date(2017,3,3), 3, 3000.00),
            Artista("art4", true, Date(2017,4,4), 4, 4000.00)
    )
    val arch1 = Archivos("./src/archivos/ejemplo.txt")
    arch1.escribir(listaArtisa);
    arch1.leer();

}