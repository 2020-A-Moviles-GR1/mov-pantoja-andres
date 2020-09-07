package com.example.examencrud.htttpmodels

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ArtistaHTTP(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val nombre: String,
    val banda: Boolean,
    val fechaInicio: LocalDate,
    var cantidadDiscos: Int,
    var gananciaTotal: Double,
    var canciones: ArrayList<CancionHTTP>? = null

) {
    //var df1: DateTimeFormatter = Date("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

    override fun toString(): String {
        return "$nombre, $fechaInicio $cantidadDiscos"
    }


    companion object{
        val conversorArtista = object: Converter{
            override fun canConvert(cls: Class<*>)= cls == ArtistaHTTP::class.java

            override fun fromJson(jv: JsonValue): ArtistaHTTP {
                return ArtistaHTTP(
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objInt("id"),
                    jv.objString("nombre"),
                    jv.obj?.get("banda") as Boolean,
                    LocalDate.parse(jv.objString("fechaInicio"), DateTimeFormatter.ISO_DATE_TIME),
                    jv.objInt("cantidadDiscos"),
                    jv.obj?.get("gananciaTotal").toString().toDouble(),
                    Klaxon().converter(CancionHTTP.conversorCancion)
                        .parseFromJsonArray<CancionHTTP>(
                        jv.obj?.get("canciones") as JsonArray<*>
                    ) as ArrayList<CancionHTTP>
                )
            }

            override fun toJson(value: Any): String {
                return """
                    
                """.trimIndent()
            }

        }
    }


}