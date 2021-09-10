import java.util.*

// Respuesta Ejercicio 1: en un Set no importa el orden, y además no pueden existir dos elementos iguales.

// Ejercicio 2 y 3 :
const val MINUTES_IN_MILISECONDS = 60000
const val PLACES_AVAILABLES = 20

data class Parkable(var vehicle :Vehicle){
    var plate = vehicle.plate
}

data class Parking(val vehicles: MutableSet<Vehicle>){

    var emptyPlaces = PLACES_AVAILABLES - vehicles.size

    fun checkIn(vehicle: Vehicle){
        if(emptyPlaces != 0 && addVehicle(vehicle)){
                println("Welcome to AlkeParking!")
                emptyPlaces--
        }
            else println("Sorry, the check-in failed")
    }

    fun addVehicle(vehicle:Vehicle) : Boolean{
        if (vehicles.size < PLACES_AVAILABLES)
            return vehicles.add(vehicle)
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
    //prueba patente repetida
    val car2 = Vehicle("AA111AA",VehicleType.CAR, Calendar.getInstance(),"DISCOUNT_CARD_001")
    parking.checkIn(car2)

    //prueba cargar 20 sabiendo que hay 4 cargados, deberia fallar 4 veces
    for(num in 1..20) {
        parking.checkIn(car.copy("AA11$num BB"))
    }
    //total
    println(parking.vehicles.size)



}

