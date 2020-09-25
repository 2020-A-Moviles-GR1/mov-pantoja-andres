package com.example.examencrud.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examencrud.CancionActivity
import com.example.examencrud.CreateCancionActivity
import com.example.examencrud.R
import com.example.examencrud.httphandler.CancionHandler
import com.example.examencrud.htttpmodels.CancionHTTP

class CancionAdapter(
    private val listaCanciones: ArrayList<CancionHTTP>,
    private val contexto: CancionActivity,
    private val clickListener: MyOnCancionClickedListener
): RecyclerView.Adapter<CancionAdapter.MyViewHolder>() {

    var handler: CancionHandler = CancionHandler()

    inner class MyViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view){
        var cancionImageView: ImageView
        var cancionTituloTextView: TextView
        var eliminarButton: Button
        var actualizarButton: Button
        init {
             cancionImageView = view.findViewById(R.id.iv_cancion_adapter)
             cancionTituloTextView = view.findViewById(R.id.tv_titulo_adapter)
             eliminarButton = view.findViewById(R.id.btn_eliminar_cancion_adapter)
             actualizarButton = view.findViewById(R.id.btn_actualizar_cancion_adapter)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.cancion_recycler_adapter,
                parent,
                false
            )
        return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaCanciones.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cancion: CancionHTTP = listaCanciones[position]

        Glide
            .with(contexto)
            .load(cancion.imagePath)
            .into(holder.cancionImageView)

        holder.cancionTituloTextView.text = cancion.titulo

        holder.itemView.setOnClickListener{
            clickListener.onCacnionClicked(cancion)
        }

        holder.eliminarButton.setOnClickListener{
            eliminarCancion(cancion, position)
        }
        holder.actualizarButton.setOnClickListener{
            actualizarCancion(cancion)
        }

    }


    fun eliminarCancion(cancion: CancionHTTP, posicion: Int){
        val cancion: CancionHTTP? = handler.deleteOne(cancion.id)
        if (cancion != null){
            listaCanciones.removeAt(posicion)
            //Recuerda poner un notify on dataset changed
            notifyDataSetChanged()
        }else{
            Toast.makeText(contexto, "Error al eliminar", Toast.LENGTH_LONG)
        }
    }
    fun actualizarCancion(cancion: CancionHTTP){
        val intentExplicito: Intent = Intent(
            contexto,
            CreateCancionActivity::class.java
        )
        intentExplicito.putExtra("id", cancion.id)
        contexto.startActivity(intentExplicito)
    }

}

interface MyOnCancionClickedListener{
    fun onCacnionClicked(cancion: CancionHTTP)
}