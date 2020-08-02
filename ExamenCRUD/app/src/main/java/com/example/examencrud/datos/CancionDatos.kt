package com.example.examencrud.datos

import com.example.examencrud.models.Artista
import com.example.examencrud.models.Cancion
import java.time.LocalDate

class CancionDatos {
    companion object{
        var listaCanciones: ArrayList<Cancion> = arrayListOf<Cancion>(
            Cancion("sng1", true, LocalDate.parse("2019-01-01"), 1, 1000.00, 1, 1),
            Cancion("sng2", true, LocalDate.parse("2012-02-02"), 2, 2000.00, 2,2),
            Cancion("sng3", false, LocalDate.parse("2013-03-03"), 3, 3000.00, 3,3),
            Cancion("sng4", true, LocalDate.parse("2014-04-04"), 4, 4000.00, 4, 4)
        )
    }
}