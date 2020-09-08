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
import com.example.examencrud.datos.CancionDatos
import com.example.examencrud.httphandler.HTTPHandler
import com.example.examencrud.htttpmodels.ArtistaHTTP
import com.example.examencrud.models.Artista
import com.example.examencrud.models.Cancion
import com.github.kittinunf.fuel.core.FuelError
import kotlinx.android.synthetic.main.activity_artista.*
import java.time.LocalDate

class ArtistaActivity : AppCompatActivity() {
    var listaDeArtistasHTTP: ArrayList<ArtistaHTTP> = arrayListOf()
    var handler: HTTPHandler = HTTPHandler()
    lateinit var  adapter: ArrayAdapter<ArtistaHTTP>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artista)
        actionBar?.setDisplayHomeAsUpEnabled(true)

       listaDeArtistasHTTP =  handler.getAll()
        btn_actualizar.isEnabled = false
        btn_eliminar.isEnabled = false
        btn_ver_mas.isEnabled =  false

         adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_activated_1,
            listaDeArtistasHTTP
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

        btn_eliminar.setOnClickListener { boton -> eliminarArtistaActual() }
        btn_ver_mas.setOnClickListener { boton -> verMas() }
        btn_actualizar.setOnClickListener { boton -> actualizar() }
        btn_agregar.setOnClickListener { boton -> irNuevoArtista() }
        btn_salir.setOnClickListener { boton -> finish() }
    }

    fun actualizar(){
        var posicion: Int = lv_artista.checkedItemPosition
        var intentExplicito: Intent = Intent(this, CreateArtistaActivity::class.java)
        intentExplicito.putExtra("id", listaDeArtistasHTTP[posicion].id)

        startActivityForResult(intentExplicito, 2)
    }

    fun verMas(){
        var posicion: Int = lv_artista.checkedItemPosition
        var intentExplicito: Intent = Intent(this, ArtistaVerMasActivity::class.java)
        intentExplicito.putExtra("id", listaDeArtistasHTTP[posicion].id)

        startActivity(intentExplicito)
    }

    fun eliminarArtistaActual(
    ) {
        var posicion: Int = lv_artista.checkedItemPosition
        var artistaPorEliminar = listaDeArtistasHTTP[posicion]
        val artistaEliminados = handler.deleteOne(artistaPorEliminar.id)
        if (artistaEliminados != null){
            listaDeArtistasHTTP.removeAt(posicion)
            adapter.notifyDataSetChanged()
        }else{
            Log.i("Error", "error al eliminar")
        }
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
                            val parametros = listOf(
                                "nombre" to "$nombre",
                                "banda" to "$banda",
                                "fechaInicio" to "$fecha",
                                "cantidadDiscos" to "$discos",
                                "gananciaTotal" to "$ganancia"
                            )
                            var artistaCreado = handler.createOne(parametros)
                            if (artistaCreado!= null){
                                listaDeArtistasHTTP.add(artistaCreado)
                                adapter.notifyDataSetChanged()
                            }else{
                                Log.i("Error", "Error creando artista")
                            }
                        }
                    }
                    2 -> {
                        if (data != null){
                            val discos = data.getIntExtra("discos", 0)
                            val id = data.getIntExtra("id", 0)
                            val ganancia = data.getDoubleExtra("ganacia", 0.0)
                            val parametros: List<Pair<String, Any>> = listOf(
                                "gananciaTotal" to "$ganancia",
                                "cantidadDiscos" to "$discos"
                            )
                            if (id!=0){
                                val artista = handler.updateOne(parametros, id)
                                if (artista!= null){
                                    val artistasActualizados = handler.getAll()
                                    if (artistasActualizados.size > 0){
                                        listaDeArtistasHTTP.clear()
                                        listaDeArtistasHTTP.addAll(artistasActualizados)
                                        adapter.notifyDataSetChanged()
                                    }else{
                                        Log.i("Http-get-update", "No hay datos")
                                    }
                                }else{
                                    Log.i("Error", "Error creando artista")
                                }
                            }else {
                                Log.i("Errore traer datos update", "Error al traer datos update")
                            }
                        }
                    }
                }
            }
        }
    }
}