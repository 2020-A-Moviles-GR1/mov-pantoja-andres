package com.example.examencrud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.examencrud.httphandler.CancionHandler
import com.example.examencrud.httphandler.ArtistaHandler
import com.example.examencrud.htttpmodels.ArtistaHTTP
import com.example.examencrud.htttpmodels.CancionHTTP
import kotlinx.android.synthetic.main.activity_create_cancion.*

class CreateCancionActivity : AppCompatActivity() {
    var listaDeArtistas: ArrayList<ArtistaHTTP> = arrayListOf()
    var handlerCancion: CancionHandler = CancionHandler()
    var handlerArtista: ArtistaHandler = ArtistaHandler()
    lateinit var adapterArtista: ArrayAdapter<ArtistaHTTP>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_cancion)
        btn_aceptar_cancion.setOnClickListener { boton -> crearCancion() }
        btn_cancelar_cancion.setOnClickListener { boton -> finish() }

        listaDeArtistas = handlerArtista.getAll();
        if (listaDeArtistas.size <= 0) {
            Log.i("ERROR artista ", "No hay artistas")
            finish()
        }
        adapterArtista = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listaDeArtistas
        )
        sp_artistas.adapter = adapterArtista
        if (intent.hasExtra("id")) {
            val id: Int = intent.getIntExtra("id", 0)
            if (id != 0) {
                val cancion: CancionHTTP? = handlerCancion.getOne(id)
                if (cancion != null) {
                    ponerEntornoActualizar(cancion)
                } else {
                    Log.i("ERROR artista handler", "No hay cacnion del handler")
                    finish()
                }
            } else {
                Log.i("ERROR artista act", "No hay cancion")
                finish()
            }
        }
    }

    fun crearCancion() {
        val intentRespuesta: Intent = Intent()
        intentRespuesta.putExtra("titulo", et_titulo.text.toString())
        intentRespuesta.putExtra("premiada", sw_premiada.isChecked)
        intentRespuesta.putExtra("fecha", ed_fecha_lanzamiento.text.toString())
        intentRespuesta.putExtra("reproducciones", etn_reproducciones.text.toString().toInt())
        intentRespuesta.putExtra("duracion", etnd_duracion.text.toString().toDouble())
        intentRespuesta.putExtra(
            "idArtista",
            listaDeArtistas[sp_artistas.selectedItemPosition].id
        )
        setResult(Activity.RESULT_OK, intentRespuesta)
        finish()
    }

    fun ponerEntornoActualizar(cancion: CancionHTTP) {
        val artista: ArtistaHTTP = cancion.artista as ArtistaHTTP
        tv_titulo_crear_cancion.text = "Actualizar Cancion"
        et_titulo.setText(cancion.titulo)
        et_titulo.isEnabled = false
        sw_premiada.isChecked = cancion.premiada
        ed_fecha_lanzamiento.setText(cancion.fechaLanzamientoDate.toString())
        ed_fecha_lanzamiento.isEnabled = false
        etn_reproducciones.setText(cancion.numeroReproducciones.toString())
        etnd_duracion.setText(cancion.duracionMinutos.toString())
        etnd_duracion.isEnabled = false
        val posicionArtista = encontrarIndiceSegunIDArtista(artista.id)
        if (posicionArtista == -1) {
            Log.i("ERROR artista ", "No hay artistas")
            finish()
        }
        sp_artistas.setSelection(posicionArtista)
        sp_artistas.isEnabled = false
        btn_aceptar_cancion.setOnClickListener { boton -> actualizar() }
    }

    fun actualizar() {
        val intentRespuesta: Intent = Intent()
        intentRespuesta.putExtra("premiada", sw_premiada.isChecked)
        intentRespuesta.putExtra("reproducciones", etn_reproducciones.text.toString().toInt())
        intentRespuesta.putExtra(
            "id",
            intent.getIntExtra("id", -1)
        )
        setResult(Activity.RESULT_OK, intentRespuesta)
        finish()
    }

    fun encontrarIndiceSegunIDArtista(id: Int): Int {
        var elementoEncontrado: List<ArtistaHTTP> = listaDeArtistas.filter { artista ->
            return@filter artista.id == id
        }
        try {
            var indice: Int = listaDeArtistas.indexOf(elementoEncontrado[0])
            return indice
        } catch (e: IndexOutOfBoundsException) {
            return -1
        }
    }
}