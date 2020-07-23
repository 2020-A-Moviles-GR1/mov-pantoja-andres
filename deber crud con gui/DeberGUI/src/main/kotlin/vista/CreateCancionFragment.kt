package vista

import controlador.ArtistaControlador
import controlador.CancionControlador
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.Parent
import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import modelo.Artista
import tornadofx.*
import java.time.LocalDate

class CreateCancionFragment: Fragment("CREATE") {
    private val artistaControlador: ArtistaControlador by inject()
    private val cancionControlador: CancionControlador by inject()
    private val opcionesBooleanas = FXCollections.observableArrayList<String>("Sí", "No")
    private val booleanoSeleccionado = SimpleStringProperty()
    private var titulo: TextField by singleAssign()
    private var fecha: DatePicker by singleAssign()
    private var reproducciones: TextField by singleAssign()
    private var duracion: TextField by singleAssign()
    private val comboboxObject = SimpleObjectProperty<Artista>()


    override val root = form {
        fieldset("Creacion de una Canción") {
            field("Título") {
                titulo = textfield()
            }
            field("¿Premiada?") {
                combobox(booleanoSeleccionado, opcionesBooleanas)
            }
            field("Fecha de Lanzamiento") {
                fecha = datepicker()
            }
            field("Cantidad de Reproducciones") {
                reproducciones = textfield {
                    filterInput {
                        it.controlNewText.isInt()
                    }
                }
            }
            field("Duración en minutos") {
                duracion = textfield {
                    filterInput {
                        it.controlNewText.isDouble()
                    }
                }
            }
            field("Artista") {
                combobox<Artista>(comboboxObject) {
                    selectionModel.selectFirst()
                    items = artistaControlador.artistas
                    cellFormat {
                        text = this.item.nombre
                    }

                }
            }
            button("Crear") {
                action {
                    cancionControlador.crearCancion(
                        titulo.text,
                        if (booleanoSeleccionado.value == "No") false else true,
                        LocalDate.parse(fecha.value.toString()),
                        reproducciones.text.toInt(),
                        duracion.text.toDouble(),
                        comboboxObject.get().idArtista
                    )
                    titulo.text = ""
                    booleanoSeleccionado.value = null
                    fecha.value = null
                    reproducciones.text = ""
                    duracion.text = ""

                }
            }
        }
    }

}