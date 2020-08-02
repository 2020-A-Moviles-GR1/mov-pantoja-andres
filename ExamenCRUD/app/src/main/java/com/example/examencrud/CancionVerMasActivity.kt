package com.example.examencrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examencrud.datos.ArtistaDatos
import com.example.examencrud.datos.CancionDatos
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

        val posicion: Int = intent.getIntExtra("posicion", -1)
        if(posicion != -1){
            val cancion: Cancion = listaDeCanciones[posicion]
            tv_titulo_cancion.text = cancion.titulo
            tv_premiada.text = if(cancion.premiada) "Sí" else "No"
            tv_fecha_lanzamiento.text = cancion.fechaDeLanzamiento.toString()
            tv_Duracion.text = cancion.duracionMinutos.toString()
            tv_reproducciones.text = cancion.numeroDeReproducciones.toString()
            val posicionArtista = encontrarIndiceSegunIDArtista(cancion.idArtista)
            tv_Artista.text =  listaDeArtistas[posicionArtista].nombre
        }



        btn_salir.setOnClickListener { boton -> finish() }
        btn_buscar.setOnClickListener { boton -> buscar() }

    }


    fun encontrarIndiceSegunIDArtista( id: Int): Int{

        var elementoEncontrado: List<Artista> = listaDeArtistas.filter { artista ->
            return@filter artista.idArtista == id
        }
        try {
            var indice: Int = listaDeArtistas.indexOf(elementoEncontrado[0])
            return indice
        }catch (e: IndexOutOfBoundsException){
            return -1
        }


    }

    fun buscar(){
        var intent: Intent = Intent(
            Intent.ACTION_SEARCH
        )
        intent.setPackage("com.google.android.youtube")
        intent.putExtra(
            "query",
            "${tv_titulo_cancion.text.toString()} - ${tv_Artista.text.toString()}")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


}