import java.util.*

// Respuesta Ejercicio 1: en un Set no importa el orden, y además no pueden existir dos elementos iguales.

// Ejercicio 2 y 3 :
//const val MINUTES_IN_MILISECONDS = 60000

data class Parkable(var vehicle :Vehicle){
    var plate = vehicle.plate
}

data class Parking(val vehicles: MutableSet<Vehicle>)

data class Vehicle(val plate: String, val Type: VehicleType, val checkInTime: Calendar, val discountCard: String?){

    override fun equals(other :Any?): Boolean {
        if (other is Vehicle){
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return this.plate.hashCode()
    }


}

enum class VehicleType(val value : Int){
    CAR(20),
    MOTORBIKE(15),
    MINIBUS(25),
    BUS(30)
}

fun main() {
    // Maximo 20 vehiculos

println(Calendar.MINUTE)

    // Ejercicio 2:

}

