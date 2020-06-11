import java.util.*

fun main(args:Array<String>) {
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
}
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
}
