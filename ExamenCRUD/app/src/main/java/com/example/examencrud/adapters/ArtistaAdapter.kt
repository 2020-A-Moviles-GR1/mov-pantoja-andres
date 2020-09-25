package com.example.examencrud.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.examencrud.*
import com.example.examencrud.httphandler.ArtistaHandler
import com.example.examencrud.htttpmodels.ArtistaHTTP
import com.example.examencrud.htttpmodels.CancionHTTP

class ArtistaAdapter(
  private val listaArtistas: ArrayList<ArtistaHTTP>,
  private val contexto: ArtistaActivity,
  private val clickListener: MyOnArtistaClickedListener
): RecyclerView.Adapter<ArtistaAdapter.MyViewHolder>() {

    var handler: ArtistaHandler = ArtistaHandler()

    inner class MyViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view){

        var artistaNombreTextView: TextView
        var eliminarButton: Button
        var actualizarButton: Button
        var cancionesButton: Button

        init {
            artistaNombreTextView = view.findViewById(R.id.tv_nombre_artista_adapter)
            eliminarButton = view.findViewById(R.id.btn_eliminar_artista_adapter)
            actualizarButton = view.findViewById(R.id.btn_actualizar_artista_adapter)
            cancionesButton = view.findViewById(R.id.btn_canciones_artista_adapter)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.artista_recycler_adapter,
                parent,
                false
            )
        return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  listaArtistas.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val artista: ArtistaHTTP = listaArtistas[position]



        holder.artistaNombreTextView.text = artista.nombre

        holder.itemView.setOnClickListener{
            clickListener.onArtistaClicked(artista)
        }

        holder.eliminarButton.setOnClickListener{
            eliminarArtista(artista, position)
        }
        holder.actualizarButton.setOnClickListener{
            actualizarArtista(artista)
        }
        holder.cancionesButton.setOnClickListener{
            irMapa(artista)
        }
    }


    fun eliminarArtista(artista: ArtistaHTTP, posicion: Int){
        val artista: ArtistaHTTP? = handler.deleteOne(artista.id)
        if (artista != null){
            listaArtistas.removeAt(posicion)
            //Recuerda poner un notify on dataset changed
            notifyDataSetChanged()
        }else{
            Toast.makeText(contexto, "Error al eliminar", Toast.LENGTH_LONG)
        }
    }

    fun actualizarArtista(artista: ArtistaHTTP){
        val intentExplicito: Intent = Intent(
            contexto,
            CreateArtistaActivity::class.java
        )
        intentExplicito.putExtra("id", artista.id)
        contexto.startActivity(intentExplicito)
    }

    fun irMapa(artista: ArtistaHTTP){
        val intentExplicito: Intent = Intent(
            contexto,
            MapsActivity::class.java
        )

        intentExplicito.putExtra("id", artista.id)
        contexto.startActivity(intentExplicito)

    }

}


interface MyOnArtistaClickedListener{
    fun onArtistaClicked(artista: ArtistaHTTP)
}