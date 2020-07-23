package vista

import controlador.ArtistaControlador
import controlador.CancionControlador
import javafx.geometry.Pos
import javafx.scene.control.TabPane
import tornadofx.*

import javafx.scene.paint.*
import javafx.scene.text.FontWeight
import modelo.Artista
import javax.swing.GroupLayout

class MyView: View("CRUD Artista - Canción") {
    companion object{
        val artistaControlador: ArtistaControlador = ArtistaControlador()
        val cancionControlador: CancionControlador = CancionControlador()
        val artistaModelo = ArtistaModel(Artista())
    }

    override  val root = borderpane {

        top{
            label("CRUD Artista - Canción") {
                style{
                    fontWeight = FontWeight.BOLD
                    alignment = Pos.CENTER
                }
            }
        }


        center{
            tabpane{
                tab("Artista"){
                    tabpane{
                        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
                        tab<ReadArtistaFragment>{
                        }
                        tab<CreateArtistaFragment>{
                        }
                        tab<DeleteArtistaFragment>{
                        }
                        tab<UpdateArtistaFragment>{
                        }
                    }
                }
                tab("Cancion"){
                    tabpane{
                        tab<ReadCancionFragment>{
                        }
                        tab<CreateCancionFragment>{
                        }
                        tab<DeleteCancionFragment>{
                        }
                        tab<UpdateCancionFragment>{
                        }
                    }
                }

            }
        }


    }
}

