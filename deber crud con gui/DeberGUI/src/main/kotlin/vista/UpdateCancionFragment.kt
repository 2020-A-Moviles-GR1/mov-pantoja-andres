package vista

import controlador.ArtistaControlador
import controlador.CancionControlador
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.Parent
import javafx.scene.control.TextField
import modelo.Artista
import modelo.Cancion
import tornadofx.*

class UpdateCancionFragment : Fragment("UPDATE") {
    private val cancionControlador: CancionControlador by inject()
    private val comboboxObject = SimpleObjectProperty<Cancion>()
    private var reproducciones: TextField by singleAssign()
    private val opcionesBooleanas = FXCollections.observableArrayList<String>("Sí", "No")
    private val booleanoSeleccionado = SimpleStringProperty()

    override val root = form {
        fieldset("Actualizar una canción") {
            field("Elegir Canción") {
                combobox<Cancion>(comboboxObject) {
                    selectionModel.selectFirst()
                    items = cancionControlador.canciones
                    cellFormat {
                        text = this.item.titulo
                    }
                }
            }
            field("Nueva Cantidad de Reproducciones") {
                reproducciones = textfield() {
                    filterInput {
                        it.controlNewText.isInt()
                    }
                }
            }
            field("¿Premiada?") {
                combobox(booleanoSeleccionado, opcionesBooleanas)
            }


            button("Actualizar Canción") {
                action {
                    cancionControlador.actualizarCancion(
                        cancionControlador.encontrarIndiceSegunID(comboboxObject.get().idCancion),
                        if (booleanoSeleccionado.value == "No") false else true,
                        reproducciones.text.toInt()
                    )
                    cancionControlador.modificarCancion(
                        comboboxObject.get(),
                        if (booleanoSeleccionado.value == "No") false else true,
                        reproducciones.text.toInt()
                    )

                    comboboxObject.value = null
                    booleanoSeleccionado.value = null
                    reproducciones.text = ""
                }
            }
        }
    }
}