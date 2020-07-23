package com.example.moviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_envia_parametros.*

class IntentEnviaParametrosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_envia_parametros)
        // intent -> propiedad de la clase
        var numeroEncontrado  = intent.getIntExtra("numero", 0)

        Log.i("Intent", "El numero encontrado es $numeroEncontrado")

        val textoCompartido = intent.getStringExtra(Intent.EXTRA_TEXT)
        if(textoCompartido != null){
            Log.i("Intent", "El texto compartido es $textoCompartido")
        }

        btn_devolver_respuesta
                .setOnClickListener{
                    finish()
                }

        btn_resp_aceptar
                .setOnClickListener{
                    val nombre = "Andres"
                    val edad = 21
                    val intentRespuesta = Intent()
                    intentRespuesta.putExtra("nombre", nombre)
                    intentRespuesta.putExtra("edad", edad)
                    setResult(
                            Activity.RESULT_OK,
                            intentRespuesta
                    )
                    finish()
                }

        btn_resp_cancelar
                .setOnClickListener{
                    val intentCanelado =Intent()
                    setResult(
                            Activity.RESULT_CANCELED,
                            intentCanelado
                    )
                    finish()
                }
    }
}