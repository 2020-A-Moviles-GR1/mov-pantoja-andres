package com.example.examencrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.examencrud.httphandler.HTTPHandler
import kotlinx.android.synthetic.main.activity_artista_ver_mas.*

class ArtistaVerMasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artista_ver_mas)
        val handler = HTTPHandler()
        val id = intent.getIntExtra("id", 0)
        if (id != 0) {
            val artista = handler.getOne(id)
            if (artista != null) {

                tv_nombre.text = artista.nombre
                tv_banda.text = if (artista.banda) "Sí" else "No"
                tv_fecha.text = artista.fechaInicioDate.toString()
                tv_ganacia.text = artista.gananciaTotal.toString()
                tv_discos.text = artista.cantidadDiscos.toString()
            } else {
                Log.i("Ver mas", "no hay artista")
                finish()
            }
        } else {
            Log.i("Ver mas", "no hay id")
            finish()
        }
        btn_salir.setOnClickListener { boton -> finish() }
    }
}