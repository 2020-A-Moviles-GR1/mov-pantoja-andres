package com.example.moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_ciclo_vida
            .setOnClickListener { boton ->
                irCicloDeVida()
            }

        btn_listView.setOnClickListener{
            boton -> irListView()
        }
    }

    fun irCicloDeVida(){
        val intentExplicito: Intent = Intent(
            this,
            CicloVida::class.java
        )
        startActivity(intentExplicito)
    }

    fun irListView(){
        val intentExplicito: Intent = Intent(
            this,
            ListViewActivity::class.java
        )
        startActivity(intentExplicito)
    }



}