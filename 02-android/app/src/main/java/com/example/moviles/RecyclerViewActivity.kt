package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    var numeroLikes = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val listaUsuarios = arrayListOf<UsuarioHttp>()
        listaUsuarios.add(
                UsuarioHttp(
                        1,
                        3214,
                        21341234,
                        "180494098",
                        "Andres",
                        "a@a.com",
                        "Soltero",
                        "Supersegura",
                        null
                )
        )
        listaUsuarios.add(
                UsuarioHttp(
                        2,
                        3214,
                        21341234,
                        "060494098",
                        "Sebastian",
                        "a@a.com",
                        "Soltero",
                        "Supersegura",
                        null
                )
        )
        listaUsuarios.add(
                UsuarioHttp(
                        3,
                        3214,
                        21341234,
                        "178494098",
                        "Analia",
                        "a@a.com",
                        "Soltero",
                        "Supersegura",
                        null
                )
        )


        iniciarRecyclerView(
                listaUsuarios,
                this,
                rv_usuarios
        )

    }


    fun iniciarRecyclerView(
            lista: List<UsuarioHttp>,
            actividad: RecyclerViewActivity,
            recyclerView: RecyclerView
    ){
        val adaptadorUsuario = RecyclerAdapter(

                lista,
                actividad,
                recyclerView
        )
        rv_usuarios.adapter = adaptadorUsuario
        rv_usuarios.itemAnimator = DefaultItemAnimator()
        rv_usuarios.layoutManager = LinearLayoutManager(actividad)
        adaptadorUsuario.notifyDataSetChanged()
    }

    fun agregarLikesActividad( numero: Int){
        numeroLikes = numeroLikes + numero
        tv_titulo_rv.text = numeroLikes.toString()
    }

}