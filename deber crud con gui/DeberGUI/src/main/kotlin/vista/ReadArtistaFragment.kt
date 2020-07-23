package vista

import controlador.ArtistaControlador
import javafx.scene.Parent
import modelo.Artista
import tornadofx.*

class ReadArtistaFragment: Fragment("READ") {
    private val artistaControlador: ArtistaControlador by inject()


    override val root = tableview<Artista>(artistaControlador.artistas) {

        column("ID", Artista:: idArtista)
        column("Nombre", Artista:: nombre)
        column("¿Banda?", Artista:: banda)
        column("Fecha de Inicio", Artista:: fechaInicio)
        column("Discos", Artista:: cantidadDiscos)
        column("Ganancia", Artista:: gananciaTotal)

        columnResizePolicy = SmartResize.POLICY
    }
}