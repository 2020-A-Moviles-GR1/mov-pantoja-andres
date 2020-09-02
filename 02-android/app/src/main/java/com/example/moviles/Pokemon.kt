package com.example.moviles


import android.util.Log
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.util.*


open class Pokemon(
        var createdAt: Long,
        var updatedAt: Long,
        var id: Int,
        var nombre: String,
        var usuario: Any?
) {

    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }

    override fun toString(): String {
        return "Nombre: $nombre, ID: $id, Usuario: $usuario"
    }

    companion object {
        val conversorPokemon = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == Pokemon::class.java


            override fun toJson(value: Any): String {
                return """
                    
                """.trimIndent()
            }

            override fun fromJson(jv: JsonValue): Pokemon {
                return if (jv.obj?.get("usuario") is Int) Pokemon(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objString("nombre"),
                        jv.objInt("usuario"))
                else Pokemon(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objString("nombre"),
                        Klaxon().parseFromJsonObject<UsuarioHttp>(jv.obj?.get("usuario") as JsonObject)
                )
            }
        }
    }
}




