package com.example.moviles

class Entrenador(
    var nombre: String,
    var apellido: String
){
    override fun toString(): String {
        return "Nombre: $nombre Apellido: $apellido"
    }
}