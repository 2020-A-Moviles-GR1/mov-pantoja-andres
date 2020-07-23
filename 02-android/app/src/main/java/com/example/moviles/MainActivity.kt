package com.example.moviles

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_ciclo_vida
            .setOnClickListener { boton ->
                irCicloDeVida()
            }

        btn_listView.setOnClickListener{
            boton -> irListView()
        }

        btn_intent_respuesta.setOnClickListener{
            boton -> irIntentConRespuesta()
        }

        btn_intent_implicito.setOnClickListener{
            boton -> enviarIntentConRespuesta()
        }

        btn_resp_propia.setOnClickListener{
            boton -> enviarIntentConRespuestaPropia()
        }

    }

    fun enviarIntentConRespuestaPropia(){
        val intentConRespuesta = Intent(
               this,
                IntentEnviaParametrosActivity::class.java
        )
        //El cod 304 no es nada especial, simplemente lo ponemos
        startActivityForResult(intentConRespuesta, 305)
    }

    fun irIntentConRespuesta(){
        val intentExplicito: Intent = Intent(
                this,
                IntentEnviaParametrosActivity:: class.java
        )
        intentExplicito.putExtra("numero", 2 )

        startActivity(intentExplicito)

    }

    fun irCicloDeVida(){
        val intentExplicito: Intent = Intent(
            this,
            CicloVida::class.java
        )
        startActivity(intentExplicito)
    }

    fun irListView(){
        val intentExplicito: Intent = Intent(
            this,
            ListViewActivity::class.java
        )
        startActivity(intentExplicito)
    }


    fun enviarIntentConRespuesta(){
        val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        //El cod 304 no es nada especial, simplemente lo ponemos
        startActivityForResult(intentConRespuesta, 304)

    }

    override fun onActivityResult(requestCode: Int,//numero enviado
                                  resultCode: Int,//Numero que nos llega
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            Activity.RESULT_OK -> {
                Log.i("resultado", ":D")
                when(requestCode){
                    304 -> {
                        val uri = data?.data;
                        if(uri != null){
                            val cursor = contentResolver.query(
                                    uri,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(indiceTelefono!!)
                            cursor?.close()
                            Log.i("resultado", "Telefono: $telefono")
                        }

                    }
                    305 -> {
                        if(data != null){
                            val nombre = data.getStringExtra("nombre")
                            val edad = data.getIntExtra("edad", 0)
                            Log.i("resultado", "nombre: $nombre, edad: $edad" )
                        }


                    }
                }
            }
            Activity.RESULT_CANCELED -> {
                Log.i("resultado", ":C")
            }
        }
    }

}