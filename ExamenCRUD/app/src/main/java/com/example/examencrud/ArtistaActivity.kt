package com.example.examencrud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.examencrud.datos.ArtistaDatos
import com.example.examencrud.models.Artista
import kotlinx.android.synthetic.main.activity_artista.*
import java.time.LocalDate

class ArtistaActivity : AppCompatActivity() {
    var listaDeArtistas: ArrayList<Artista> = arrayListOf()
    lateinit var  adapter: ArrayAdapter<Artista>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artista)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        listaDeArtistas = ArtistaDatos.listaArtista;

        btn_actualizar.isEnabled = false
        btn_eliminar.isEnabled = false
        btn_ver_mas.isEnabled =  false

         adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_activated_1,
            listaDeArtistas
        )

        lv_artista.choiceMode = ListView.CHOICE_MODE_SINGLE
        lv_artista.adapter = adapter

        lv_artista
            .onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            lv_artista.setItemChecked(position, true)
            btn_actualizar.isEnabled = true
            btn_eliminar.isEnabled = true
            btn_ver_mas.isEnabled = true
        }

        btn_eliminar.setOnClickListener { boton ->
            eliminarArtistaActual()
        }
        btn_ver_mas.setOnClickListener { boton ->
            verMas()
        }
        btn_actualizar.setOnClickListener { boton -> actualizar() }

        btn_agregar.setOnClickListener { boton -> irNuevoArtista() }

        btn_salir.setOnClickListener { boton -> finish() }


    }
    fun actualizar(){
        var posicion: Int = lv_artista.checkedItemPosition
        var intentExplicito: Intent = Intent(this, CreateArtistaActivity::class.java)
        intentExplicito.putExtra("nombre", listaDeArtistas[posicion].nombre)
        intentExplicito.putExtra("banda", listaDeArtistas[posicion].banda)
        intentExplicito.putExtra("fecha", listaDeArtistas[posicion].fechaInicio.toString())
        intentExplicito.putExtra("discos", listaDeArtistas[posicion].cantidadDiscos)
        intentExplicito.putExtra("ganacia", listaDeArtistas[posicion].gananciaTotal)
        intentExplicito.putExtra("posicion", posicion)
        startActivityForResult(intentExplicito, 2)
    }

    fun verMas(){
        var posicion: Int = lv_artista.checkedItemPosition
        var intentExplicito: Intent = Intent(this, ArtistaVerMasActivity::class.java)
        intentExplicito.putExtra("nombre", listaDeArtistas[posicion].nombre)
        intentExplicito.putExtra("banda", listaDeArtistas[posicion].banda)
        intentExplicito.putExtra("fecha", listaDeArtistas[posicion].fechaInicio.toString())
        intentExplicito.putExtra("discos", listaDeArtistas[posicion].cantidadDiscos)
        intentExplicito.putExtra("ganacia", listaDeArtistas[posicion].gananciaTotal)

        startActivity(intentExplicito)

    }

    fun eliminarArtistaActual(
    ) {
        var posicion: Int = lv_artista.checkedItemPosition
        Log.i("list-view", "Posicion: $posicion")

        listaDeArtistas.removeAt(posicion)
        adapter.notifyDataSetChanged()

    }

    fun irNuevoArtista() {
        var intent: Intent = Intent(
            this,
            CreateArtistaActivity::class.java
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
                            val nombre = data.getStringExtra("nombre")
                            val banda = data.getBooleanExtra("banda", true)
                            val fecha = data.getStringExtra("fecha")
                            val discos = data.getIntExtra("discos", 0)
                            val ganancia = data.getDoubleExtra("ganacia", 0.0)
                            val id: Int = listaDeArtistas.last().idArtista + 1
                            listaDeArtistas.add(
                                Artista(
                                    nombre,
                                    banda,
                                    LocalDate.parse(fecha),
                                    discos,
                                    ganancia,
                                    id
                                )
                            )
                            adapter.notifyDataSetChanged()


                        }

                    }
                    2 -> {
                        if (data != null){
                            val discos = data.getIntExtra("discos", 0)
                            val posicion = data.getIntExtra("posicion", 0)
                            val ganancia = data.getDoubleExtra("ganacia", 0.0)
                            listaDeArtistas[posicion].cantidadDiscos = discos
                            listaDeArtistas[posicion].gananciaTotal = ganancia
                            adapter.notifyDataSetChanged()

                        }
                    }
                }
            }
        }
    }
}