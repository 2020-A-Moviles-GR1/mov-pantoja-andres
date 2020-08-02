package com.example.examencrud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import com.example.examencrud.datos.ArtistaDatos
import com.example.examencrud.datos.CancionDatos
import com.example.examencrud.models.Artista
import com.example.examencrud.models.Cancion
import kotlinx.android.synthetic.main.activity_create_artista.*
import kotlinx.android.synthetic.main.activity_create_artista.btn_aceptar
import kotlinx.android.synthetic.main.activity_create_artista.btn_cancelar
import kotlinx.android.synthetic.main.activity_create_artista.ed_fecha_inicio
import kotlinx.android.synthetic.main.activity_create_artista.et_nombre
import kotlinx.android.synthetic.main.activity_create_artista.etn_cantidad_discos
import kotlinx.android.synthetic.main.activity_create_artista.etnd_ganacia_total
import kotlinx.android.synthetic.main.activity_create_artista.sw_banda
import kotlinx.android.synthetic.main.activity_create_cancion.*

class CreateCancionActivity : AppCompatActivity() {
    var listaDeArtistas: ArrayList<Artista> = arrayListOf()
    var listaDeCanciones: ArrayList<Cancion> = arrayListOf()
    lateinit var  adapterArtista: ArrayAdapter<Artista>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_cancion)
        btn_aceptar_cancion.setOnClickListener { boton -> crearCancion() }
        btn_cancelar_cancion.setOnClickListener { boton -> finish() }
        listaDeArtistas = ArtistaDatos.listaArtista
        listaDeCanciones = CancionDatos.listaCanciones

        adapterArtista =  ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listaDeArtistas
        )
        sp_artistas.adapter = adapterArtista


        if(intent.hasExtra("idCancion")){
            ponerEntornoActualizar()
        }

    }

    fun crearCancion(){
//        Log.i("Datos", "Nombre ${et_titulo.text.toString()}")
//        Log.i("Datos", "Fecha ${ed_fecha_lanzamiento.text.toString()}")
//        Log.i("Datos", "Banda ${sw_premiada.isChecked}")
//        Log.i("Datos", "Discos ${etn_reproducciones.text}")
//        Log.i("Datos", "Ganancia ${etnd_duracion.text}")
//        Log.i("Datos", "IdPosicion ${sp_artistas.selectedItemPosition}")
//        Log.i("Datos", "IdPosicion ${listaDeArtistas[sp_artistas.selectedItemPosition].idArtista}")

        val intentRespuesta: Intent = Intent()
        intentRespuesta.putExtra("titulo", et_titulo.text.toString())
        intentRespuesta.putExtra("premiada", sw_premiada.isChecked)
        intentRespuesta.putExtra("fecha", ed_fecha_lanzamiento.text.toString())
        intentRespuesta.putExtra("reproduccines", etn_reproducciones.text.toString().toInt())
        intentRespuesta.putExtra("duracion", etnd_duracion.text.toString().toDouble())
        intentRespuesta.putExtra("idArtista", listaDeArtistas[sp_artistas.selectedItemPosition].idArtista)

        setResult(Activity.RESULT_OK, intentRespuesta)
        finish()
    }

    fun ponerEntornoActualizar(){
        val idCancion = intent.getIntExtra("idCancion", -1)
        val posicion: Int = encontrarIndiceSegunIDCancion(idCancion)
        if(posicion != -1){
            tv_titulo_crear_cancion.text = "Actualizar Cancion"
            et_titulo.setText(listaDeCanciones[posicion].titulo)
            et_titulo.isEnabled = false
            sw_premiada.isChecked = listaDeCanciones[posicion].premiada

            ed_fecha_lanzamiento.setText(listaDeCanciones[posicion].fechaDeLanzamiento.toString())
            ed_fecha_lanzamiento.isEnabled = false
            etn_reproducciones.setText(listaDeCanciones[posicion].numeroDeReproducciones.toString())
            etnd_duracion.setText(listaDeCanciones[posicion].duracionMinutos.toString())
            etnd_duracion.isEnabled = false
            val posicionArtista = encontrarIndiceSegunIDArtista(listaDeCanciones[posicion].idArtista)
            sp_artistas.setSelection(posicionArtista)
            sp_artistas.isEnabled = false
            btn_aceptar_cancion.setOnClickListener { boton -> actualizar() }
        }else{
            finish()
        }



    }

    fun actualizar(){
        val intentRespuesta: Intent = Intent()
        intentRespuesta.putExtra("premiada", sw_premiada.isChecked)
        intentRespuesta.putExtra("reproducciones", etn_reproducciones.text.toString().toInt())
        intentRespuesta.putExtra("posicion",
            encontrarIndiceSegunIDCancion(intent.getIntExtra("idCancion", -1)))
        setResult(Activity.RESULT_OK, intentRespuesta)
        finish()
    }

    fun encontrarIndiceSegunIDCancion( id: Int): Int{

        var elementoEncontrado: List<Cancion> = listaDeCanciones.filter { Cancion ->
            return@filter Cancion.idCancion == id
        }
        try {
            var indice: Int = listaDeCanciones.indexOf(elementoEncontrado[0])
            return indice
        }catch (e: IndexOutOfBoundsException){
            return -1
        }


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


}