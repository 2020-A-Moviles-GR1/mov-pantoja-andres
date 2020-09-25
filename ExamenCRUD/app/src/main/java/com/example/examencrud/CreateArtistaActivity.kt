package com.example.examencrud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.examencrud.httphandler.ArtistaHandler
import com.example.examencrud.htttpmodels.ArtistaHTTP
import kotlinx.android.synthetic.main.activity_create_artista.*

class CreateArtistaActivity : AppCompatActivity() {

    var id: Int = 0
    val handler = ArtistaHandler();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_artista)

        btn_aceptar.setOnClickListener { boton -> crearArtista() }
        btn_cancelar.setOnClickListener { boton -> finish() }

        if (intent.hasExtra("id")) {
            id = intent.getIntExtra("id", 0)
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

        val discos: Int = etn_cantidad_discos.text.toString().toInt()
        val ganancia: Double = etnd_ganacia_total.text.toString().toDouble()


        val parametros: List<Pair<String, Any>> = listOf(
            "gananciaTotal" to "$ganancia",
            "cantidadDiscos" to "$discos"
        )
        val artista: ArtistaHTTP? = handler.updateOne(parametros, id)

        if (artista != null) {
            Toast.makeText(this, "Artista Actualizado", Toast.LENGTH_LONG)
            finish()
        } else {
            Toast.makeText(this, "Error al actualizar", Toast.LENGTH_LONG)
        }


    }

    fun crearArtista() {

        val intent: Intent = Intent(
            this,
            ArtistaActivity::class.java
        )

                            val nombre = et_nombre.text.toString()
                            val banda = sw_banda.isChecked
                            val fecha = ed_fecha_inicio.text.toString()
                            val discos = etn_cantidad_discos.text.toString().toInt()
                            val ganancia = etnd_ganacia_total.text.toString().toDouble()
                            val parametros = listOf(
                                "nombre" to "$nombre",
                                "banda" to "$banda",
                                "fechaInicio" to "$fecha",
                                "cantidadDiscos" to "$discos",
                                "gananciaTotal" to "$ganancia"
                            )
                            var artistaCreado = handler.createOne(parametros)
                            if (artistaCreado!= null){
                                Toast.makeText(this, "Artista Creado", Toast.LENGTH_LONG)
                                startActivity(intent)

                            } else {
                                Toast.makeText(this, "Error al crear", Toast.LENGTH_LONG)
                            }

    }
}


