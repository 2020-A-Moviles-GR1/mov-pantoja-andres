package com.example.examencrud.models

import android.os.Parcel
import android.os.Parcelable


import java.time.LocalDate
import java.util.*

open class Artista constructor(val nombre: String?,
                               val banda: Boolean,
                               val fechaInicio: LocalDate,
                               var cantidadDiscos: Int,
                               var gananciaTotal: Double,
                               val idArtista: Int){
    val nombreArchivo: String = "./src/archivos/artista.txt"
    var listaCanciones: List<Cancion> = listOf<Cancion>()


    override fun toString(): String {

        return "${nombre},${banda},${fechaInicio},${cantidadDiscos},${gananciaTotal},${idArtista}";
    }

    fun toBeautyString(){
        println("ID: $idArtista\tNombre: $nombre\tBanda: $banda\tFecha de Inicio: $fechaInicio\t" +
                "Cantidad de Discos: $cantidadDiscos\tGanancias: $gananciaTotal")
    }






}