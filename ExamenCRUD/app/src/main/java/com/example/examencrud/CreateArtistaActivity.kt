package com.example.examencrud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.example.examencrud.models.Artista
import kotlinx.android.synthetic.main.activity_create_artista.*

class CreateArtistaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_artista)

        btn_aceptar.setOnClickListener { boton -> crearArtista() }
        btn_cancelar.setOnClickListener { boton -> finish() }

        if(intent.hasExtra("posicion")){
            ponerEntornoActualizar()
        }


    }

    fun ponerEntornoActualizar(){
        tv_titulo.text = "Actualizar Artista"
        et_nombre.setText(intent.getStringExtra("nombre"))
        et_nombre.isEnabled = false
        sw_banda.isChecked = intent.getBooleanExtra("banda", false)
        sw_banda.isEnabled = false
        ed_fecha_inicio.setText(intent.getStringExtra("fecha"))
        ed_fecha_inicio.isEnabled = false
        etn_cantidad_discos.setText(intent.getIntExtra("discos", 0).toString())
        etnd_ganacia_total.setText(intent.getDoubleExtra("ganacia", 0.0).toString())
        btn_aceptar.setOnClickListener { boton -> actualizar() }

    }

    fun actualizar(){
        val intentRespuesta: Intent = Intent()
        intentRespuesta.putExtra("discos", etn_cantidad_discos.text.toString().toInt())
        intentRespuesta.putExtra("ganacia", etnd_ganacia_total.text.toString().toDouble())
        intentRespuesta.putExtra("posicion", intent.getIntExtra("posicion", 0))
        setResult(Activity.RESULT_OK, intentRespuesta)
        finish()

    }

    fun crearArtista(){
        Log.i("Datos", "Nombre ${et_nombre.text.toString()}")
        Log.i("Datos", "Fecha ${ed_fecha_inicio.text.toString()}")
        Log.i("Datos", "Banda ${sw_banda.isChecked}")
        Log.i("Datos", "Discos ${etn_cantidad_discos.text}")
        Log.i("Datos", "Ganancia ${etnd_ganacia_total.text}")
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


