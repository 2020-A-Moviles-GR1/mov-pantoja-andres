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
import com.example.examencrud.models.Artista
import com.example.examencrud.models.Cancion
import kotlinx.android.synthetic.main.activity_artista.*
import kotlinx.android.synthetic.main.activity_cancion.*
import java.time.LocalDate

class CancionActivity : AppCompatActivity() {
    var listaDeCanciones: ArrayList<Cancion> = arrayListOf()
    lateinit var  adapterCancion: ArrayAdapter<Cancion>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancion)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        listaDeCanciones = CancionDatos.listaCanciones

        btn_actualizar_cancion.isEnabled = false
        btn_eliminar_cancion.isEnabled = false
        btn_ver_mas_cancion.isEnabled =  false

        adapterCancion = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_activated_1,
            listaDeCanciones
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

        btn_eliminar_cancion.setOnClickListener { boton ->
            eliminarCancionActual()
        }

        btn_actualizar_cancion.setOnClickListener { boton -> actualizar() }

        btn_agregar_cancion.setOnClickListener { boton -> irNuevaCancion() }

        btn_salir_cancion.setOnClickListener { boton -> finish() }

        btn_ver_mas_cancion.setOnClickListener{boton -> verMas()}

    }

    fun verMas(){
        var posicion: Int = lv_cancion.checkedItemPosition
        var intent: Intent = Intent(
            this,
            CancionVerMasActivity::class.java
        )
        intent.putExtra("posicion", posicion)

        startActivity(intent)
    }

    fun actualizar(){
        var posicion: Int = lv_cancion.checkedItemPosition
        Log.i("list-view", "Posicion: $posicion")
        var intent: Intent = Intent(
            this,
            CreateCancionActivity::class.java
        )
        intent.putExtra("idCancion", listaDeCanciones[posicion].idCancion)

        startActivityForResult(intent, 2)

    }

    fun eliminarCancionActual(){
        var posicion: Int = lv_cancion.checkedItemPosition
        Log.i("list-view", "Posicion: $posicion")

        listaDeCanciones.removeAt(posicion)
        adapterCancion.notifyDataSetChanged()
    }

    fun irNuevaCancion(){
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
                            val reproduccinoes = data.getIntExtra("reproducciones", 0)
                            val duracion = data.getDoubleExtra("duracion", 0.0)
                            val idArtista = data.getIntExtra("idArtista", 1)
                            val id: Int = listaDeCanciones.last().idCancion + 1
                            listaDeCanciones.add(
                                Cancion(
                                    titulo,
                                    premiada,
                                    LocalDate.parse(fecha),
                                    reproduccinoes,
                                    duracion,
                                    id,
                                    idArtista
                                )
                            )
                            adapterCancion.notifyDataSetChanged()


                        }

                    }
                    2 -> {
                        if (data != null){
                            val reproducciones = data.getIntExtra("reproducciones", 0)
                            val posicion = data.getIntExtra("posicion", 0)
                            val premiada = data.getBooleanExtra("premiada", false)
                            listaDeCanciones[posicion].numeroDeReproducciones = reproducciones
                            listaDeCanciones[posicion].premiada = premiada
                            adapterCancion.notifyDataSetChanged()

                        }
                    }
                }
            }
        }
    }

}