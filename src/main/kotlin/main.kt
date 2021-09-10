import java.util.*

// Respuesta Ejercicio 1: en un Set no importa el orden, y además no pueden existir dos elementos iguales.

// Ejercicio 2 y 3 :
const val MINUTES_IN_MILISECONDS = 60000

data class Parkable(var vehicle :Vehicle){
    var plate = vehicle.plate
}

data class Parking(val vehicles: MutableSet<Vehicle>){
    fun addVehicle(vehicle:Vehicle) : Boolean{
        if (vehicles.size < 20){
            vehicles.add(vehicle)
            return true
        }
        return false
    }
}

data class Vehicle(val plate: String, val Type: VehicleType, val checkInTime: Calendar, val discountCard: String? = null){
    val parkedTime : Long
        get() = (Calendar.getInstance().timeInMillis - checkInTime.timeInMillis) / MINUTES_IN_MILISECONDS
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
    MOTORCYCLE(15),
    MINIBUS(25),
    BUS(30)
}

fun main() {
    // Maximo 20 vehiculos
    val car = Vehicle("AA111AA",VehicleType.CAR, Calendar.getInstance(),"DISCOUNT_CARD_001")
    val moto = Vehicle("B222BBB",VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus = Vehicle("CC333CC",VehicleType.MINIBUS, Calendar.getInstance())
    val bus = Vehicle("DD444DD",VehicleType.BUS, Calendar.getInstance(),"DISCOUNT_CARD_002")
    val parking = Parking(mutableSetOf(car,moto,minibus,bus))
    println(parking.vehicles.contains(car))
    println(parking.vehicles.contains(moto))
    println(parking.vehicles.contains(minibus))
    println(parking.vehicles.contains(bus))
    for(num in 1..20) {
        println("num: $num - Inserted: " + parking.addVehicle(car.copy("AA11$num BB")))
    }
    parking.vehicles.map {
        println(it.plate)
    }
    println(parking.vehicles.size)



}

