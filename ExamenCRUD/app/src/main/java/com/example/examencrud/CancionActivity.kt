package com.example.examencrud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.examencrud.datos.ArtistaDatos
import com.example.examencrud.datos.CancionDatos
import com.example.examencrud.httphandler.CancionHandler
import com.example.examencrud.htttpmodels.CancionHTTP
import com.example.examencrud.models.Artista
import com.example.examencrud.models.Cancion
import kotlinx.android.synthetic.main.activity_artista.*
import kotlinx.android.synthetic.main.activity_cancion.*
import java.time.LocalDate

class CancionActivity : AppCompatActivity() {
    var listaDeCancionesHTTP: ArrayList<CancionHTTP> = arrayListOf()
    var handler: CancionHandler = CancionHandler()
    lateinit var adapterCancion: ArrayAdapter<CancionHTTP>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancion)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        listaDeCancionesHTTP = handler.getAll()
        btn_actualizar_cancion.isEnabled = false
        btn_eliminar_cancion.isEnabled = false
        btn_ver_mas_cancion.isEnabled = false

        adapterCancion = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_activated_1,
            listaDeCancionesHTTP
        )
        lv_cancion.choiceMode = ListView.CHOICE_MODE_SINGLE
        lv_cancion.adapter = adapterCancion
        lv_cancion
            .onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            lv_cancion.setItemChecked(position, true)
            btn_actualizar_cancion.isEnabled = true
            btn_eliminar_cancion.isEnabled = true
            btn_ver_mas_cancion.isEnabled = true
        }

        btn_eliminar_cancion.setOnClickListener { boton -> eliminarCancionActual() }
        btn_actualizar_cancion.setOnClickListener { boton -> actualizar() }
        btn_agregar_cancion.setOnClickListener { boton -> irNuevaCancion() }
        btn_salir_cancion.setOnClickListener { boton -> finish() }
        btn_ver_mas_cancion.setOnClickListener { boton -> verMas() }
    }

    fun verMas() {
        var posicion: Int = lv_cancion.checkedItemPosition
        var intent: Intent = Intent(
            this,
            CancionVerMasActivity::class.java
        )
        Log.i("Pos", "$posicion")
        intent.putExtra("id", listaDeCancionesHTTP[posicion].id)
        startActivity(intent)
    }

    fun actualizar() {
        var posicion: Int = lv_cancion.checkedItemPosition
        Log.i("list-view", "Posicion: $posicion")
        var intent: Intent = Intent(
            this,
            CreateCancionActivity::class.java
        )
        intent.putExtra("id", listaDeCancionesHTTP[posicion].id)
        startActivityForResult(intent, 2)
    }

    fun eliminarCancionActual() {
        var posicion: Int = lv_cancion.checkedItemPosition
        Log.i("list-view", "Posicion: $posicion")
        var cancionPorEliminar = listaDeCancionesHTTP[posicion]
        val cancionEliminada = handler.deleteOne(cancionPorEliminar.id)
        if (cancionEliminada != null) {
            listaDeCancionesHTTP.removeAt(posicion)
            adapterCancion.notifyDataSetChanged()
        } else {
            Log.i("Error", "error al eliminar")
        }
    }

    fun irNuevaCancion() {
        var intent: Intent = Intent(
            this,
            CreateCancionActivity::class.java
        )
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                when (requestCode) {
                    1 -> {
                        if (data != null) {
                            val titulo = data.getStringExtra("titulo")
                            val premiada = data.getBooleanExtra("premiada", true)
                            val fecha = data.getStringExtra("fecha")
                            val reproducciiones = data.getIntExtra("reproducciones", 0)
                            val duracion = data.getDoubleExtra("duracion", 0.0)
                            val idArtista = data.getIntExtra("idArtista", 1)
                            val parametros = listOf(
                                "titulo" to "$titulo",
                                "premiada" to "$premiada",
                                "fechaLanzamiento" to "$fecha",
                                "numeroReproducciones" to "$reproducciiones",
                                "duracionMinutos" to "$duracion",
                                "artista" to "$idArtista"
                            )
                            var cancionCreada = handler.createOne(parametros)
                            if (cancionCreada != null) {
                                listaDeCancionesHTTP.add(cancionCreada)
                                adapterCancion.notifyDataSetChanged()
                            } else {
                                Log.i("Error", "Error creando artista")
                            }
                        }
                    }
                    2 -> {
                        if (data != null) {
                            val reproducciones = data.getIntExtra("reproducciones", 0)
                            val id = data.getIntExtra("id", 0)
                            val premiada = data.getBooleanExtra("premiada", false)
                            val parametros = listOf(
                                "numeroReproducciones" to "$reproducciones",
                                "premiada" to "$premiada"
                            )
                            if (id != 0) {
                                val cancion = handler.updateOne(parametros, id)
                                if (cancion != null) {
                                    val cancionesActualizadas = handler.getAll()
                                    if (cancionesActualizadas.size > 0) {
                                        Log.i("Si hay canciones", "Si hay artistas")
                                        listaDeCancionesHTTP.clear()
                                        listaDeCancionesHTTP.addAll(cancionesActualizadas)
                                        adapterCancion.notifyDataSetChanged()
                                    } else {
                                        Log.i("Http-get-update", "No hay datos")
                                    }
                                } else {
                                    Log.i("Error", "Error creando artista")
                                }
                            } else {
                                Log.i("Errore traer datos update", "Error al traer datos update")
                            }
                        }
                    }
                }
            }

        }
    }
}