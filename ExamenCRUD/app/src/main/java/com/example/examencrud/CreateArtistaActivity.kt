package com.example.examencrud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.example.examencrud.httphandler.HTTPHandler
import com.example.examencrud.htttpmodels.ArtistaHTTP
import com.example.examencrud.models.Artista
import kotlinx.android.synthetic.main.activity_create_artista.*

class CreateArtistaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_artista)

        btn_aceptar.setOnClickListener { boton -> crearArtista() }
        btn_cancelar.setOnClickListener { boton -> finish() }
        val handler = HTTPHandler();
        if (intent.hasExtra("id")) {
            val id = intent.getIntExtra("id", 0)
            val artista: ArtistaHTTP? = handler.getOne(id)
            if (artista != null) {
                ponerEntornoActualizar(artista)
            } else {
                Log.i("ERROR artista act", "No hay artista")
            }
        }
    }

    fun ponerEntornoActualizar(artista: ArtistaHTTP) {
        tv_titulo.text = "Actualizar Artista"
        et_nombre.setText(artista.nombre)
        et_nombre.isEnabled = false
        sw_banda.isChecked = artista.banda
        sw_banda.isEnabled = false
        ed_fecha_inicio.setText(artista.fechaInicioDate.toString())
        ed_fecha_inicio.isEnabled = false
        etn_cantidad_discos.setText(artista.cantidadDiscos.toString())
        etnd_ganacia_total.setText(artista.gananciaTotal.toString())
        btn_aceptar.setOnClickListener { boton -> actualizar() }
    }

    fun actualizar() {
        val intentRespuesta: Intent = Intent()
        intentRespuesta.putExtra("discos", etn_cantidad_discos.text.toString().toInt())
        intentRespuesta.putExtra("ganacia", etnd_ganacia_total.text.toString().toDouble())
        intentRespuesta.putExtra("id", intent.getIntExtra("id", 0))
        setResult(Activity.RESULT_OK, intentRespuesta)
        finish()
    }

    fun crearArtista() {
        val intentRespuesta: Intent = Intent()
        intentRespuesta.putExtra("nombre", et_nombre.text.toString())
        intentRespuesta.putExtra("banda", sw_banda.isChecked)
        intentRespuesta.putExtra("fecha", ed_fecha_inicio.text.toString())
        intentRespuesta.putExtra("discos", etn_cantidad_discos.text.toString().toInt())
        intentRespuesta.putExtra("ganacia", etnd_ganacia_total.text.toString().toDouble())
        setResult(Activity.RESULT_OK, intentRespuesta)
        finish()
    }
}


