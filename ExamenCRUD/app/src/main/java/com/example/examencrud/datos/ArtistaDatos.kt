package com.example.examencrud.datos
import com.example.examencrud.models.Artista
import com.example.examencrud.models.Cancion
import java.time.LocalDate

class ArtistaDatos {
    companion object{
        var listaArtista: ArrayList<Artista> = arrayListOf(
            Artista("Sam Hunt", true, LocalDate.parse("2017-01-01"), 1, 1000.00, 1),
            Artista("The Weekend", true, LocalDate.parse("2017-02-02"), 2, 2000.00, 2),
            Artista("Red Hot Chili Peppers", false, LocalDate.parse("2013-03-03"), 3, 3000.00, 3),
            Artista("Stromae", true, LocalDate.parse("2014-04-04"), 4, 4000.00, 4)
        )
    }
}