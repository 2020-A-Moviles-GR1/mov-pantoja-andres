package vista

import controlador.ArtistaControlador
import controlador.CancionControlador
import modelo.Artista
import modelo.Cancion
import tornadofx.Fragment
import tornadofx.SmartResize
import tornadofx.column
import tornadofx.tableview

class ReadCancionFragment: Fragment("READ") {

    private val cancionControlador: CancionControlador by inject()


    override val root = tableview<Cancion>(cancionControlador.canciones) {

        column("ID", Cancion:: idCancion)
        column("Título", Cancion:: titulo)
        column("¿Premiada?", Cancion:: premiada)
        column("Fecha de Lanzamiento", Cancion:: fechaDeLanzamiento)
        column("Reproducciones", Cancion:: numeroDeReproducciones)
        column("Duración", Cancion:: duracionMinutos)
        column("ID Artista", Cancion:: idArtista)

        columnResizePolicy = SmartResize.POLICY
    }
}