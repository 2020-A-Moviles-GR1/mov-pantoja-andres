package com.example.examencrud.httphandler

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.examencrud.htttpmodels.ArtistaHTTP
import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.awaitResult
import com.github.kittinunf.result.Result

class HTTPHandler {
    val URLPrincipal: String = "http://192.168.0.132:1337/artista"

    fun getAll() : ArrayList<ArtistaHTTP> {
        val url = URLPrincipal;
        var artistas: ArrayList<ArtistaHTTP> = arrayListOf()
        Log.i("result", "Antes de get")
//        val (request, response, result) = url.httpGet().responseString()
//        Log.i("result", "Despues de get")
//        val (value, error) = result;
//        Log.i("Value", "$value")
//        Log.i("Error", "$error")


        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artistas = ArrayList(
                        Klaxon()
                            .converter(ArtistaHTTP.conversorArtista)
                            .parseArray<ArtistaHTTP>(data)!!
                    )
                    artistas.forEach {
                        Log.i("Result Succes", "$it")
                    }

                }
            }
        }

        getHttp.join()

        return artistas;

    }

    fun getOne(id: Int): ArtistaHTTP?{
        val url = URLPrincipal + "/$id";
        var artista: ArtistaHTTP? = null;
        Log.i("URL", "$url")
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    Log.i("Data", "$data")
//                    artista = ArrayList(
//                        Klaxon()
//                            .converter(ArtistaHTTP.conversorArtista)
//                            .parseJsonObject(data)
//                    )
//                    artistas.forEach {
//                        Log.i("Result Succes", "$it")
//                    }
                    artista = Klaxon().converter(ArtistaHTTP.conversorArtista)
                        .parse<ArtistaHTTP>(data)!!

                }
            }
        }

        getHttp.join()

        Log.i("Artista", "$artista")

        return artista
    }

    fun deleteOne(id: Int): ArtistaHTTP?{
        val url = URLPrincipal + "/$id";
        var artista: ArtistaHTTP? = null;
        var artistas: ArrayList<ArtistaHTTP> = arrayListOf()
        Log.i("URL", "$url")
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    Log.i("Data", "$data")
//                    artista = ArrayList(
//                        Klaxon()
//                            .converter(ArtistaHTTP.conversorArtista)
//                            .parseJsonObject(data)
//                    )
//                    artistas.forEach {
//                        Log.i("Result Succes", "$it")
//                    }
                    artista = Klaxon().converter(ArtistaHTTP.conversorArtista)
                        .parse<ArtistaHTTP>(data)!!

                }
            }
        }

        deleteHttp.join()

       Log.i("Artista", "$artista")

        return artista
    }

    fun createOne(parametrosArtista: List<Pair<String, Any>>): ArtistaHTTP?{
        var artista: ArtistaHTTP? = null;
        val url = URLPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    Log.i("Data", "$data")
//                    artista = ArrayList(
//                        Klaxon()
//                            .converter(ArtistaHTTP.conversorArtista)
//                            .parseJsonObject(data)
//                    )
//                    artistas.forEach {
//                        Log.i("Result Succes", "$it")
//                    }
                    artista = Klaxon().converter(ArtistaHTTP.conversorArtista)
                        .parse<ArtistaHTTP>(data)!!

                }
            }
        }

        postHttp.join()
        return artista
    }



    fun updateOne(parametrosArtista: List<Pair<String, Any>>, id: Number): ArtistaHTTP?{
        var artista: ArtistaHTTP? = null;
        val url = URLPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    Log.i("Data", "$data")
//                    artista = ArrayList(
//                        Klaxon()
//                            .converter(ArtistaHTTP.conversorArtista)
//                            .parseJsonObject(data)
//                    )
//                    artistas.forEach {
//                        Log.i("Result Succes", "$it")
//                    }
                    artista = Klaxon().converter(ArtistaHTTP.conversorArtista)
                        .parse<ArtistaHTTP>(data)!!

                }
            }
        }

        putHttp.join()
        return artista
    }




}