package com.example.examencrud.htttpmodels

import com.beust.klaxon.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CancionHTTP(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val titulo: String,
    var premiada: Boolean,
    val fechaLanzamiento: String,
    var numeroReproducciones: Int,
    val duracionMinutos: Double,
    val latitud: Double,
    val longitud: Double,
    val imagePath: String,
    val website: String,
    val artista: Any? = null
) {

    var fechaLanzamientoDate: LocalDate
    init {
        fechaLanzamientoDate = LocalDate.parse(fechaLanzamiento, DateTimeFormatter.ISO_DATE_TIME)
    }


    override fun toString(): String {
        return "${titulo}";
    }

    companion object{
        val conversorCancion = object : Converter{
            override fun canConvert(cls: Class<*>)= cls == CancionHTTP::class.java

            override fun fromJson(jv: JsonValue): CancionHTTP {
                return if (jv.obj?.get("artista") is Int)
                    CancionHTTP(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objString("titulo"),
                        jv.obj?.get("premiada") as Boolean,
                        jv.objString("fechaLanzamiento"),
                        jv.objInt("numeroReproducciones"),
                        jv.obj?.get("duracionMinutos").toString().toDouble(),
                        jv.obj?.get("latitud").toString().toDouble(),
                        jv.obj?.get("longitud").toString().toDouble(),
                        jv.objString("imagePath"),
                        jv.objString("website"),
                        jv.objInt("artista")
                    )
                else
                    CancionHTTP(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objString("titulo"),
                        jv.obj?.get("premiada") as Boolean,
                        jv.objString("fechaLanzamiento"),
                        jv.objInt("numeroReproducciones"),
                        jv.obj?.get("duracionMinutos").toString().toDouble(),
                        jv.obj?.get("latitud").toString().toDouble(),
                        jv.obj?.get("longitud").toString().toDouble(),
                        jv.objString("imagePath"),
                        jv.objString("website"),
                        Klaxon().parseFromJsonObject<ArtistaHTTP>(jv.obj?.get("artista") as JsonObject)
                    )
            }

            override fun toJson(value: Any): String {
                return """
                    
                """.trimIndent()
            }

        }
    }
}