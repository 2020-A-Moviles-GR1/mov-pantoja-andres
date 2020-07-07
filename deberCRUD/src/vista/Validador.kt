package vista

import java.time.LocalDate

interface Validador {
    fun esValidoFecha(fecha: String):Boolean{
        try{
            LocalDate.parse(fecha)
        }catch (e: Exception){
            return false
        }
        return true
    }

    fun esValidoBoolean(boolean: String): Boolean{
        try{
            boolean.toBoolean()
        }catch (e: Exception){
            return false
        }
        return true
    }

    fun esValidoDouble(double:String):Boolean{
        try{
            double.toDouble()
        }catch (e: Exception){
            return false
        }
        return true
    }

    fun esValidoInt(int: String): Boolean{
        try{
            int.toInt()
        }catch (e: Exception){
            return false
        }
        return true
    }

}