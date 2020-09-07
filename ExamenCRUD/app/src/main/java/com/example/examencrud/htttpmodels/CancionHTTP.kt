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
    val fechaLanzamiento: LocalDate,
    var numeroReproducciones: Int,
    val duracionMinutos: Double,
    val artista: Any?
) {
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
                        LocalDate.parse(jv.objString("fechaLanzamiento"),DateTimeFormatter.ISO_DATE_TIME),
                        jv.objInt("numeroReproducciones"),
                        jv.obj?.get("duracionMinutos").toString().toDouble(),
                        jv.objInt("artista")
                    )
                else
                    CancionHTTP(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objString("titulo"),
                        jv.obj?.get("premiada") as Boolean,
                        LocalDate.parse(jv.objString("fechaLanzamiento")),
                        jv.objInt("numeroReproducciones"),
                        jv.obj?.get("duracionMinutos") as Double,
                        Klaxon()
                            .parseFromJsonObject<ArtistaHTTP>(jv.obj?.get("artista") as JsonObject)
                    )
            }

            override fun toJson(value: Any): String {
                return """
                    
                """.trimIndent()
            }

        }
    }
}