package vista

import controlador.ArtistaControlador
import controlador.CancionControlador
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.Parent
import modelo.Artista
import tornadofx.*

class DeleteArtistaFragment: Fragment("DELETE") {
    private val artistaControlador: ArtistaControlador by inject()
    private val cancionControlador: CancionControlador by inject()
    private val comboboxObject = SimpleObjectProperty<Artista>()
    override val root = form{
        fieldset("Eliminar un artista") {
            field("Elegir Artista"){
                combobox<Artista>(comboboxObject) {
                    items = artistaControlador.artistas
                    cellFormat{
                        text = this.item.nombre
                    }

                }
            }
            button("Eliminar Artista") {
                action {
                    artistaControlador.quitarDeArtistas(comboboxObject.get())
                    cancionControlador.quitarPorIDArtista(comboboxObject.get().idArtista)
                    artistaControlador.eliminarArtista(
                        comboboxObject.get().idArtista
                    )
                    cancionControlador.eliminarCancionPorArtista(comboboxObject.get().idArtista)

                    comboboxObject.value = null
                }
            }
        }
    }

}