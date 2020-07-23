package vista

import modelo.Artista
import modelo.Cancion
import tornadofx.ItemViewModel

class ArtistaModel(artista: Artista): ItemViewModel<Artista>(artista){
    val idArtista = bind(Artista::idArtista)
    val nombre = bind(Artista::nombre)
    val banda = bind(Artista::banda)
    val fechaInicio = bind(Artista::fechaInicio)
    val cantidadDiscos = bind(Artista::cantidadDiscos)
    val gananciaTotal = bind(Artista::gananciaTotal)
}

class CancionModel(cancion: Cancion): ItemViewModel<Cancion>(cancion){

}