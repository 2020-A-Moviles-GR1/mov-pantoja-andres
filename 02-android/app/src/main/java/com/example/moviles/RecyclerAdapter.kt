package com.example.moviles

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
        private val listaUsuario: List<UsuarioHttp>,
         private val contexto: RecyclerViewActivity,
        private val recyclerView: RecyclerView
): RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(
            view: View
    ): RecyclerView.ViewHolder(view){
        var nombreTextView: TextView;
        var cedulaTextView: TextView;
        var likesTextView: TextView;
        var accionButton: Button;
        var numeroLikes = 0

        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionButton = view.findViewById(R.id.btn_accion)
            accionButton.setOnClickListener{
                agregarLike()
            }


        }

        fun agregarLike(){
            numeroLikes = numeroLikes + 1
            likesTextView.text = numeroLikes.toString()
            contexto.agregarLikesActividad(1)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int
    ): RecyclerAdapter.MyViewHolder {
       //Definir la interfaz a usar
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(
                        R.layout.adaptador_persona, // Recurso del adaptador
                        parent,
                        false)
        return  MyViewHolder(itemView)
    }

    // numero de items que tenemos
    override fun getItemCount(): Int {
        return listaUsuario.size
    }

    // Se ejecuta con cada uno de los items (iterable)
    override fun onBindViewHolder(
            holder: MyViewHolder, //inner class implementada
            position: Int) { // posicion
        val usuario = listaUsuario.get(position)
        holder.nombreTextView.text = usuario.nombre;
        holder.cedulaTextView.text = usuario.cedula;
        holder.likesTextView.text = "0";
        holder.accionButton.text = "Like ${usuario.correo}"



    }


}