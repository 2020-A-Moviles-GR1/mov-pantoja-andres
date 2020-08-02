package com.example.examencrud

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_artista.setOnClickListener {
                boton -> irArtista()
        }

        btn_cancion.setOnClickListener { boton -> irCancion() }
    }

    fun irArtista(){
        val intentExplicito: Intent = Intent(
            this,
            ArtistaActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun irCancion(){
        val intentExplicit: Intent = Intent(
            this,
            CancionActivity:: class.java
        )
        startActivity(intentExplicit)
    }
}