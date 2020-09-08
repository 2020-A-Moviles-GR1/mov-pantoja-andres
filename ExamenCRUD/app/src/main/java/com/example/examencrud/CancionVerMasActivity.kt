package com.example.examencrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.examencrud.datos.ArtistaDatos
import com.example.examencrud.datos.CancionDatos
import com.example.examencrud.httphandler.CancionHandler
import com.example.examencrud.httphandler.HTTPHandler
import com.example.examencrud.htttpmodels.ArtistaHTTP
import com.example.examencrud.htttpmodels.CancionHTTP
import com.example.examencrud.models.Artista
import com.example.examencrud.models.Cancion
import kotlinx.android.synthetic.main.activity_artista_ver_mas.*
import kotlinx.android.synthetic.main.activity_artista_ver_mas.btn_salir
import kotlinx.android.synthetic.main.activity_cancion_ver_mas.*

class CancionVerMasActivity : AppCompatActivity() {
    var listaDeArtistas: ArrayList<Artista> = arrayListOf()
    var listaDeCanciones: ArrayList<Cancion> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancion_ver_mas)
        listaDeArtistas = ArtistaDatos.listaArtista
        listaDeCanciones = CancionDatos.listaCanciones

        val handler = CancionHandler()
        val id = intent.getIntExtra("id", 0)
        if (id != 0) {
            val cancion: CancionHTTP? = handler.getOne(id)
            if (cancion != null) {

                tv_titulo_cancion.text = cancion.titulo
                tv_premiada.text = if (cancion.premiada) "Sí" else "No"
                tv_fecha_lanzamiento.text = cancion.fechaLanzamientoDate.toString()
                tv_Duracion.text = cancion.duracionMinutos.toString()
                tv_reproducciones.text = cancion.numeroReproducciones.toString()
                val artista: ArtistaHTTP = cancion.artista as ArtistaHTTP
                tv_Artista.text = artista.nombre
            } else {
                Log.i("Ver mas", "no hay cancion")
                finish()
            }
        } else {
            Log.i("Ver mas", "no hay id")
            finish()
        }
        btn_salir.setOnClickListener { boton -> finish() }
        btn_buscar.setOnClickListener { boton -> buscar() }
    }

    fun buscar() {
        var intent: Intent = Intent(
            Intent.ACTION_SEARCH
        )
        intent.setPackage("com.google.android.youtube")
        intent.putExtra(
            "query",
            "${tv_titulo_cancion.text.toString()} - ${tv_Artista.text.toString()}"
        )
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}