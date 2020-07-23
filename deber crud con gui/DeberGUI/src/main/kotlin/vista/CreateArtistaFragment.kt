package vista

import controlador.ArtistaControlador
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.Parent
import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup
import modelo.Artista
import tornadofx.*
import java.time.LocalDate

class CreateArtistaFragment : Fragment("CREATE") {
    private val artistaControlador: ArtistaControlador by inject()
    private val opcionesBooleanas = FXCollections.observableArrayList<String>("Sí", "No")
    private val booleanoSeleccionado = SimpleStringProperty()
    private var nombre: TextField by singleAssign()
    private var fecha: DatePicker by singleAssign()
    private var discos: TextField by singleAssign()
    private var ganancia: TextField by singleAssign()

    override val root = form {
        fieldset("Creacion de un Artista") {
            field("Nombre") {
                nombre = textfield()
            }
            field("¿Es Banda?") {
                combobox(booleanoSeleccionado, opcionesBooleanas)
            }
            field("Fecha de Creación") {
                fecha = datepicker()
            }
            field("Cantidad de Discos") {
                discos = textfield {
                    filterInput {
                        it.controlNewText.isInt()
                    }
                }
            }
            field("Ganancia total") {
                ganancia = textfield {
                    filterInput {
                        it.controlNewText.isDouble()
                    }
                }
            }
            button("Crear") {
                action {
                    artistaControlador.crearArtista(
                        nombre.text,
                        if (booleanoSeleccionado.value == "No") false else true,
                        LocalDate.parse(fecha.value.toString()),
                        discos.text.toInt(),
                        ganancia.text.toDouble()
                    )
                    nombre.text = ""
                    booleanoSeleccionado.value = null
                    fecha.value = null
                    discos.text = ""
                    ganancia.text = ""

                }
            }
        }
    }
}
