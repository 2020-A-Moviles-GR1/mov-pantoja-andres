package vista

import controlador.ArtistaControlador
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.Parent
import javafx.scene.control.TextField
import modelo.Artista
import tornadofx.*

class UpdateArtistaFragment : Fragment("UPDATE") {
    private val artistaControlador: ArtistaControlador by inject()
    private val comboboxObject = SimpleObjectProperty<Artista>()
    private var discos: TextField by singleAssign()
    private var ganancia: TextField by singleAssign()
    override val root = form {
        fieldset("Actualizar un artista") {
            field("Elegir Artista") {
                combobox<Artista>(comboboxObject) {
                   selectionModel.selectFirst()
                    items = artistaControlador.artistas
                    cellFormat {
                        text = this.item.nombre
                    }

                }
            }
            field("Nueva Cantidad de Discos") {
                discos = textfield() {
                    filterInput {
                        it.controlNewText.isInt()
                    }
                }
            }
            field("Nueva Ganancia total") {
                ganancia = textfield() {
                    filterInput {
                        it.controlNewText.isDouble()
                    }
                }
            }


            button("Actualizar Artista") {
                action {
                    artistaControlador.actualizarArtista(
                        artistaControlador.encontrarIndiceSegunID(comboboxObject.get().idArtista),
                        discos.text.toInt(),
                        ganancia.text.toDouble()
                    )
                    artistaControlador.modificarArtistas(
                        comboboxObject.get(),
                        discos.text.toInt(),
                        ganancia.text.toDouble()
                    )


                    comboboxObject.value = null
                    discos.text = ""
                    ganancia.text = ""
                }
            }
        }
    }
}
