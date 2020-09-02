package com.example.moviles

import com.beust.klaxon.*
import java.util.*
import kotlin.collections.ArrayList

class UsuarioHttp(
        var id: Int,
        var createdAt: Long,
        var updatedAt: Long,
        var cedula: String,
        var nombre: String,
        var correo: String,
        var estadoCivil: String,
        var password: String,
        var pokemons: ArrayList<Pokemon>? = null
) {

    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }

    override fun toString(): String {
        return "Nombre: $nombre. Cédula: $cedula"
    }

    companion object {
        val conversorUsuarioHttp = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == UsuarioHttp::class.java

            override fun toJson(value: Any): String {
                return """
                    
                """.trimIndent()
            }

            override fun fromJson(jv: JsonValue): UsuarioHttp {
                return UsuarioHttp(
                        jv.objInt("id"),
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objString("cedula"),
                        jv.objString("nombre"),
                        jv.objString("correo"),
                        jv.objString("estadoCivil"),
                        jv.objString("password"),
                        Klaxon().parseFromJsonArray<Pokemon>(
                                jv.obj?.get("pokemons") as JsonArray<*>
                        ) as ArrayList<Pokemon>)

            }
        }
    }
}

