package vista

import controlador.CancionControlador
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.Parent
import modelo.Artista
import modelo.Cancion
import tornadofx.*

class DeleteCancionFragment: Fragment("DELETE") {
    private val cancionControlador: CancionControlador by inject()
    private val comboboxObject = SimpleObjectProperty<Cancion>()
    override val root = form{
        fieldset("Eliminar una canción") {
            field("Elegir Canción"){
                combobox<Cancion>(comboboxObject) {
                    items = cancionControlador.canciones
                    cellFormat{
                        text = this.item.titulo
                    }

                }
            }
            button("Eliminar Canción") {
                action {
                    cancionControlador.eliminarCancion(
                        comboboxObject.get().idCancion
                    )
                    cancionControlador.quitarDeCanciones(comboboxObject.get())
                    comboboxObject.value = null
                }
            }
        }
    }
}