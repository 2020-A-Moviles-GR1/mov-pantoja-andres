package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val listaEntrenadores: ArrayList<Entrenador> = arrayListOf<Entrenador>()
        listaEntrenadores.add(Entrenador("Andres", "Pantoja"))
        listaEntrenadores.add(Entrenador("Analía", "Pantoja"))
        listaEntrenadores.add(Entrenador("Daniel", "Pantoja"))
        listaEntrenadores.add(Entrenador("Mishel", "Castro"))
        listaEntrenadores.add(Entrenador("Juan", "Morales"))

        val adaptador: ArrayAdapter<Entrenador> = ArrayAdapter(
                this, // contexto
                android.R.layout.simple_list_item_1, // nombre layout
                listaEntrenadores // lista
        )

        lv_numeros.adapter = adaptador

        lv_numeros
                .onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id ->
            Log.i("list-view", "Posicion: $position")
        }


        btn_agregar.setOnClickListener{
            boton -> agregarElemento(adaptador,listaEntrenadores)
        }


    }


    fun agregarElemento(
            adaptador: ArrayAdapter<Entrenador> ,
            listaEntrenadores: ArrayList<Entrenador>){
      listaEntrenadores.add(Entrenador(text_nombre.text.toString(), text_Apellido.text.toString()))
        adaptador.notifyDataSetChanged()
    }


}