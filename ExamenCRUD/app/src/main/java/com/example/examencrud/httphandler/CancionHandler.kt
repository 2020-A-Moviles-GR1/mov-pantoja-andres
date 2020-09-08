package com.example.examencrud.httphandler

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.examencrud.htttpmodels.ArtistaHTTP
import com.example.examencrud.htttpmodels.CancionHTTP
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class CancionHandler {

    val URLPrincipal: String = "http://192.168.0.132:1337/cancion"

    fun getAll(): ArrayList<CancionHTTP> {
        val url = URLPrincipal;
        var canciones: ArrayList<CancionHTTP> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    canciones = ArrayList(
                        Klaxon()
                            .converter(CancionHTTP.conversorCancion)
                            .parseArray<CancionHTTP>(data)!!
                    )
                    canciones.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return canciones;
    }

    fun getOne(id: Int): CancionHTTP? {
        val url = URLPrincipal + "/$id";
        var cancion: CancionHTTP? = null;
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    cancion = Klaxon().converter(CancionHTTP.conversorCancion)
                        .parse<CancionHTTP>(data)!!
                }
            }
        }
        getHttp.join()
        return cancion
    }

    fun deleteOne(id: Int): CancionHTTP? {
        val url = URLPrincipal + "/$id";
        var cancion: CancionHTTP? = null;
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    cancion = Klaxon().converter(CancionHTTP.conversorCancion)
                        .parse<CancionHTTP>(data)!!
                }
            }
        }
        deleteHttp.join()
        return cancion
    }

    fun createOne(parametrosArtista: List<Pair<String, Any>>): CancionHTTP? {
        var cancion: CancionHTTP? = null;
        val url = URLPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    cancion = Klaxon().converter(CancionHTTP.conversorCancion)
                        .parse<CancionHTTP>(data)!!
                }
            }
        }
        postHttp.join()
        return cancion
    }

    fun updateOne(parametrosArtista: List<Pair<String, Any>>, id: Number): CancionHTTP? {
        var cancion: CancionHTTP? = null;
        val url = URLPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    cancion = Klaxon().converter(CancionHTTP.conversorCancion)
                        .parse<CancionHTTP>(data)!!
                }
            }
        }
        putHttp.join()
        return cancion
    }
}