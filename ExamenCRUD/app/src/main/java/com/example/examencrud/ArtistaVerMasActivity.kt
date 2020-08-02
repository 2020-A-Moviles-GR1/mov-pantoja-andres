package com.example.examencrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_artista_ver_mas.*

class ArtistaVerMasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artista_ver_mas)
        tv_nombre.text = intent.getStringExtra("nombre")
        tv_banda.text = if(intent.getBooleanExtra("banda", false)) "Sí" else "No"
        tv_fecha.text = intent.getStringExtra("fecha")
        tv_ganacia.text = intent.getDoubleExtra("ganacia", 0.0).toString()
        tv_discos.text = intent.getIntExtra("discos", 0).toString()


        btn_salir.setOnClickListener { boton -> finish() }
    }
}