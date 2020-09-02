package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Converter
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_h_t_t_p.*

class HTTPActivity : AppCompatActivity() {
    val URLPrincipal: String = "http://192.168.0.132:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h_t_t_p)

        btn_obtener.setOnClickListener({ boton ->
            obtenerDatos()
        })

        btn_crear.setOnClickListener { boton ->
            crearUsuario()
        }

        btn_get_usuario.setOnClickListener { boton ->
            getUsuario()
        }

        btn_get_pokemon.setOnClickListener { boton ->
            getPokemon()
        }

    }


    fun crearUsuario() {
        val url: String = URLPrincipal + "/usuario"
        val nombre = "Andres";
        val parametrosUsuario = listOf(
                "cedula" to "1234567890",
                "nombre" to "$nombre",
                "correo" to "adsadf@gmail.com",
                "password" to "SuperS3gura"
        )

        url.httpPost(parametrosUsuario)
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Success -> {
                            val data = result.get()
                            Log.i("http-klaxon", "Data: $data")

                        }
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http-klaxon", "Error: ${ex.message}")
                        }

                    }
                }

    }

    fun getUsuario() {
        val url: String = URLPrincipal + "/usuario"
        url.httpGet()
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http-klaxon", "Error: ${ex.message}")
                        }
                        is Result.Success -> {
                            val data = result.get()
                            val usuarios = Klaxon()
                                    .converter(UsuarioHttp.conversorUsuarioHttp)
                                    .parseArray<UsuarioHttp>(data)
                            if (usuarios != null) {
                                usuarios.forEach {
                                    Log.i("Klaxon",
                                            "Usuario: $it")

                                    it.pokemons?.forEach {
                                        Log.i("klaxon", "Pokemon: $it")
                                    }
                                }
                            }
                        }
                    }
                }
    }


    fun getPokemon() {
        val url: String = URLPrincipal + "/pokemon"
        url.httpGet()
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Success -> {
                            val data = result.get()
                            val pokemons = Klaxon()
                                    .converter(Pokemon.conversorPokemon)
                                    .parseArray<Pokemon>(data)

                            if (pokemons != null) {
                                pokemons.forEach {
                                    Log.i(
                                            "Klaxon",
                                            "Pokemon: ${it}"
                                    )

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


    fun obtenerDatos() {
        val pokemonString = """
            {
            "createdAt": 1598531451966,
            "updatedAt": 1598531451966,
            "id": 1,
            "nombre": "umbreon",
            "usuario": 1
          }
        """.trimIndent()

        val pokemon = Klaxon().parse<Pokemon>(pokemonString)
        if (pokemon != null) {
            Log.i("Pokemon", "${pokemon.nombre}, ${pokemon.fechaCreacion}, ${pokemon.fechaActualizacion}, ${pokemon.id}, ${pokemon.usuario} ")
        }


        val url: String = URLPrincipal + "/usuario"
        url.httpGet()
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Success -> {
                            val data = result.get()
                            //Log.i("http-klaxon", "Data: $data")

                            val usuarios = Klaxon().parseArray<UsuarioHttp>(data)

                            if (usuarios != null) {
                                usuarios.forEach {
                                    Log.i(
                                            "Klaxon",
                                            "Nombre: ${it.nombre}"
                                    )

                                    it.pokemons?.forEach {
                                        Log.i("klaxon", "Nombre: ${it.nombre}")
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