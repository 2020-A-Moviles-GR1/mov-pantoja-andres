package com.example.examencrud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examencrud.adapters.ArtistaAdapter
import com.example.examencrud.adapters.MyOnArtistaClickedListener
import com.example.examencrud.httphandler.ArtistaHandler
import com.example.examencrud.htttpmodels.ArtistaHTTP
import kotlinx.android.synthetic.main.activity_artista.*
import kotlinx.android.synthetic.main.activity_cancion.*

class ArtistaActivity : AppCompatActivity(), MyOnArtistaClickedListener {
    var listaDeArtistasHTTP: ArrayList<ArtistaHTTP> = arrayListOf()
    var handler: ArtistaHandler = ArtistaHandler()
    lateinit var  adapter: ArrayAdapter<ArtistaHTTP>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artista)
        actionBar?.setDisplayHomeAsUpEnabled(true)

       listaDeArtistasHTTP =  handler.getAll()

        iniciarRecyclerView()

        btn_agregar.setOnClickListener{ irNuevoArtista() }

    }

    fun iniciarRecyclerView(){
        val artistaAdapter = ArtistaAdapter(
            listaDeArtistasHTTP,
            this,
            this
        )
        rv_artista.adapter = artistaAdapter
        rv_artista.layoutManager = LinearLayoutManager(this)
        rv_artista.itemAnimator = DefaultItemAnimator()
        artistaAdapter.notifyDataSetChanged()
    }





    fun irNuevoArtista() {
        var intent: Intent = Intent(
            this,
            CreateArtistaActivity::class.java
        )
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                when (requestCode) {
                    1 -> {
                            rv_artista.adapter!!.notifyDataSetChanged()
                        }
                    }

                }
            }
        }


    override fun onArtistaClicked(artista: ArtistaHTTP) {

        var intentExplicito: Intent = Intent(this, ArtistaVerMasActivity::class.java)
        intentExplicito.putExtra("id", artista.id)

        startActivity(intentExplicito)
    }
}