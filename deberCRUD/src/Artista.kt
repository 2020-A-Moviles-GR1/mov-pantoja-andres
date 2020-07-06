import java.util.*

class Artista constructor(val nombre: String,
              val banda: Boolean,
              val fechaInicio: Date,
              var cantidadDiscos: Int,
              var gananciaTotal: Double): GestorArchivos {


    override val nombreArchivo: String
        get() = "./src/archivos/artista.txt"

    override fun escribir(lista: List<Any>, append: Boolean) {
        super.escribir(lista, append);
    }

    override fun leer(): String {
        return super.leer()
    }

    override fun toString(): String {
        return "${nombre},${banda},${fechaInicio},${cantidadDiscos},${gananciaTotal}";
    }


}