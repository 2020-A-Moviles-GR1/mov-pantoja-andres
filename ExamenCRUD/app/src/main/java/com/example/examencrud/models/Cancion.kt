package com.example.examencrud.models

import java.time.LocalDate


class Cancion(
    val titulo: String?,
    var premiada: Boolean,
    val fechaDeLanzamiento: LocalDate,
    var numeroDeReproducciones: Int,
    val duracionMinutos: Double,
    val idCancion: Int,
    val idArtista: Int
) {
    val nombreArchivo: String = "./src/archivos/cancion.txt"

    override fun toString(): String {
        return "${titulo}";
    }

    fun toBeautyString(){
        println("ID: $idCancion\tTítulo: $titulo\tPremiada: $premiada\tFecha de Lanzamiento: $fechaDeLanzamiento\t" +
                "Número de Reproducciones: $numeroDeReproducciones\tDuración en Minutos: $duracionMinutos")
    }

}