package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_h_t_t_p.*

class HTTPActivity : AppCompatActivity() {
    val URLPrincipal: String = "http://192.168.1.112:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h_t_t_p)

        btn_obtener.setOnClickListener({
            boton -> obtenerDatos()
        })

    }


    fun obtenerDatos(){
        val pokemonString = """
            {
            "createdAt": 1598531451966,
            "updatedAt": 1598531451966,
            "id": 1,
            "nombre": "umbreon",
            "usuario": 1
          }
        """.trimIndent()

        val pokemon  = Klaxon().parse<Pokemon>(pokemonString)
        if (pokemon != null){
            Log.i("Pokemon", "${pokemon.nombre}, ${pokemon.fechaCreacion}, ${pokemon.fechaActualizacion}, ${pokemon.id}, ${pokemon.usuario} ")
        }


        val url: String = URLPrincipal + "/usuario"
        url.httpGet()
        .responseString{
            request, response, result ->
            when(result){
                is Result.Success -> {
                    val data = result.get()
                    //Log.i("http-klaxon", "Data: $data")

                    val usuarios = Klaxon().
                    parseArray<UsuarioHttp>(data)

                    if (usuarios != null){
                        usuarios.forEach{
                            Log.i(
                                    "Klaxon",
                                    "Nombre: ${it.nombre}"
                            )
                            if(it.pokemons.size > 0){
                                it.pokemons.forEach {
                                    Log.i("klaxon", "Nombre: ${it.nombre}")
                                }
                            }
                        }
                    }

                }
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }

            }
        }
    }


}