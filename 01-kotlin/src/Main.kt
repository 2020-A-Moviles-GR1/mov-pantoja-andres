import java.util.*
import kotlin.collections.ArrayList

fun main(args:Array<String>) {
    /*
    println("Hola");
    //Int edad = 31;
    // Variables mutables
    var edad = 31 // No se especifica el tipo de dato
                  // El ; no es necesario
    var edadCachorro: Int
    edadCachorro = 3
    edad = 32
    edadCachorro = 4


    // Variables inmutables
    val numeroCuenta = 123456
    //numeroCuenta = 123

    //Tipos variables
    val nombreEstudiante: String = "Andrés Sebastián" // String con "
    val sueldo: Double = 12.20
    val apellidosEstudiante: Char = 'P' // Char con '
    val fechaDeNacimiento = Date() //No se necesita usar New, solo llamar al constructor

    // Comparaciones
    if (sueldo == 12.20){
    } else{
    }

    when (sueldo){
        12.20 -> println("Sueldo normal")
        -12.20 -> println("Sueldo negativo")
        else -> println("No se reconoce el sueldo")
    }

    val esSueldoMayorAEstablecido = if(sueldo == 12.20) true else false


    calcularSueldo(1000.00, 14.00, 2 )
    print(calcularSueldo(
            tasa = 16.00,
            sueldo = 800.00
    )) // Named Params
    */

    // Arreglos
    val umero = 20

    var arregloConstante: Array<Int> = arrayOf(1,2,3)
    val arregloCumpleanos : ArrayList<Int> = arrayListOf(30,40,20,22)
    arregloCumpleanos.add(25)
    //println(arregloCumpleanos)
    arregloCumpleanos.remove(20)
   // println(arregloCumpleanos)
/*
    arregloCumpleanos
            .forEach{
                println("Valor iteracion: " + it)
            }
    arregloCumpleanos
            .forEach(
                { valorIteracion: Int ->
                    println("Valor iteracion: " + valorIteracion)
                }
            )
    arregloCumpleanos
            .forEach{valorIteracion: Int ->
                println("Valor: " + valorIteracion)

    }

    arregloCumpleanos
            .forEachIndexed{ index: Int, value: Int ->
                println("El valor " + index + " es: " + value)
            }
*/
    val respuestaMap =   arregloCumpleanos
            .map { valorArreglo: Int -> valorArreglo * -1 }

//    println("Respuesta Map")
//    println(respuestaMap)
//    println("Arreglo")
//    println(arregloCumpleanos)

     val respuestaMapDos =   arregloCumpleanos
            .map { valorArreglo: Int ->
                val nuevoValor: Int = valorArreglo * -1
                val valorFinal: Int = nuevoValor + 2
                return@map valorFinal.toString()
            }
     //println("Valores finales")
     //println(respuestaMapDos)

    val mayoresA23 =  arregloCumpleanos
            .filter { valor: Int ->
                val esMayorA23: Boolean = valor > 23
                return@filter esMayorA23
            }
    val menorA30 = arregloCumpleanos
            .filter { valor: Int -> valor < 30 }
    //println("Arreglo inicial ${arregloCumpleanos}")
    //println("Arreglo filtrado mayor ${mayoresA23}")
    //println("Arreglo filtrado menor ${menorA30}")
    //println(arregloCumpleanos)
    val respuestaAny : Boolean = arregloCumpleanos.any{
        iterador: Int ->
        return@any iterador< 25
    }
    //println(respuestaAny)
    val respuestaAll: Boolean = arregloCumpleanos.all {
        iterador: Int ->
        return@all iterador > 18
    }
    //print(respuestaAll)

    // Operador Reduce
    val respuestaReduce: Int = arregloCumpleanos
            .reduce { acumulador : Int , iteracion: Int ->
                return@reduce acumulador + iteracion
            }
    //println(respuestaReduce)
    val arregloString : ArrayList<String> = arrayListOf<String>("a", "b", "c")
    val reduceString: String = arregloString
            .reduce { acc, s ->
                return@reduce acc + ";" + s
            }
    //println(reduceString)

    val foldRespuesta : Int = arregloCumpleanos
            .fold(
                    100,
                    { accumulador: Int, iterador: Int ->
                return@fold accumulador - iterador
            }
    )
    //println(foldRespuesta)
    
    val arregloDano: ArrayList<Int> = arrayListOf(30, 31, 22, 23, 20)
    val vidaActual: Double = arregloDano
            .map { it * 0.8 }
            .filter { it > 18 }
            .fold(
                    100.00,
                    { acc, d -> acc - d }
            )
    //println(vidaActual)

//    val nuevaSumaUno = SumarDosNumerosDos(1,1)
//    val nuevaSumaDos = SumarDosNumerosDos(null,1)
//    val nuevaSumaTres = SumarDosNumerosDos(1,null)
//    val nuevaSumaCuatro = SumarDosNumerosDos(null,null)
////    println(SumarDosNumerosDos.arregloNumeros)
//    SumarDosNumerosDos.agregarNumero(12)
////    println(SumarDosNumerosDos.arregloNumeros)
//    SumarDosNumerosDos.eliminarNumero(0)
////    println(SumarDosNumerosDos.arregloNumeros)

    var nombre: String? = null
    nombre = "Andres"
    imprimirLengthNombre(nombre)
    imprimirLengthNombre(null)
//    if (nombre != null){
//        println(nombre.length)
//    }




}

fun imprimirLengthNombre(nombre: String?) {
    println(nombre?.length?.toInt()?.toDouble()) //Elvis Operator
                            //NULL Safe Calls
}

//Clase abstracta.
abstract class NumerosJava{
    val numewroUno: Int
    val numeroDos: Int
    constructor(uno: Int, dos: Int){
        numeroDos = dos
        numewroUno = uno
    }
}

abstract class Numeros( // val nuevosNumeros = Numeros(1, 2)
        protected var numeroUno: Int,
        protected var numeroDos: Int
){
}

class Suma(
        uno: Int,
        dos: Int
):Numeros(uno, dos){
    public fun sumar(): Int {
        return this.numeroUno + this.numeroDos
    }
    fun foo(){

    }
}

class SumarDosNumerosDos(
     uno: Int,
     dos: Int
):Numeros(uno, dos){
    init {
        println("Hola init")
    }
    constructor(uno: Int?, dos: Int): this(
            if(uno == null) 0 else uno,
            dos
    ){
        println ("Hola 1")
    }
    constructor(uno: Int, dos: Int?): this(
            uno,
            if(dos == null) 0 else dos
    ){
        println("Hola 2")
    }
    constructor(uno: Int?, dos: Int?): this(
            if(uno == null) 0 else uno,
            if(dos == null) 0 else dos
    ){
        println("Hola 3")
    }
    companion object{
        val arregloNumeros: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4)
        fun agregarNumero(nuevoNumero:Int){
            this.arregloNumeros.add(nuevoNumero)
        }
        fun eliminarNumero(posicionNumero:Int){
            this.arregloNumeros.removeAt(posicionNumero)
        }
    }
}

class BaseDeDatos{
    companion object{
        val datos = arrayListOf<Int>()
    }
}








/*
// Funciones
fun calcularSueldo(
        sueldo: Double, // Prarm requerido
        tasa: Double = 12.00, // Valor por defecto
        calculoEspecial: Int ? = null // Pueden ser nulos
): Double{
    if (calculoEspecial != null) {
        return sueldo * tasa * calculoEspecial
    } else {
        return sueldo * tasa
    }
}

fun imprimirMensaje() : Unit { // Unit = Void
    println("")
}*/


