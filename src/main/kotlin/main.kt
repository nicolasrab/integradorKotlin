import java.util.*
import kotlin.math.ceil

// Respuesta Ejercicio 1: en un Set no importa el orden, y además no pueden existir dos elementos iguales.
// Ejercicio 2 y 3 :

const val MINUTES_IN_MILISECONDS = 60000
const val PLACES_AVAILABLES = 20

data class Parkable(var vehicle: Vehicle) {
    var plate = vehicle.plate
//    val parkedTime: Long
//        get() = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / MINUTES_IN_MILISECONDS

    val parkedTime = 198.toLong()
    fun calculateFee() : Int{
        return applyDiscount(calculatePricePerTime())
    }

    private fun applyDiscount(calculatePricePerTime: Int): Int {
        if (vehicle.discountCard != null)
            return (calculatePricePerTime * 0.85).toInt()
        return calculatePricePerTime
    }

    private fun calculatePricePerTime() : Int {
        var amount : Int = 0
        if (parkedTime <= 120){
            amount = vehicle.type.value
        }
        else {
            var extraMin = parkedTime - 120
            amount = vehicle.type.value + calculateExtraTimeCost(extraMin)
        }
        return amount
    }

    private fun calculateExtraTimeCost(extraMin: Long): Int {
        val extraTimeFraction = 15
        val extraTimeCost = 5
        return (ceil(extraMin.toDouble() / extraTimeFraction.toDouble()) * extraTimeCost).toInt()
    }

    override fun equals(other: Any?): Boolean {
        if (other is Parkable) {
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return this.plate.hashCode()
    }

}

data class Parking(val parkables: MutableSet<Parkable>) {

    var emptyPlaces = PLACES_AVAILABLES - parkables.size
    var retiredVehicles : Pair<Int,Int> = Pair(0,0)

    fun checkIn(vehicle: Vehicle) {
        if (emptyPlaces != 0 && addVehicle(vehicle)) {
            println("Welcome to AlkeParking!")
            emptyPlaces--
        } else println("Sorry, the check-in failed")
    }

    fun addVehicle(vehicle: Vehicle): Boolean {
        if (parkables.size < PLACES_AVAILABLES)
            return parkables.add(Parkable(vehicle))
        return false
    }
    fun checkOutVehicle(plate: String, onSuccess: (Int) -> Unit, onError: () -> Unit) {
        val parkable = parkables.firstOrNull {
            it.plate == plate
        }
        if (parkable == null){
            onError()
        } else {
            val fee = parkable.calculateFee()
            onSuccess(fee)
            parkables.remove(parkable)
            updateRetiredVehicles(fee)
        }

    }

    private fun updateRetiredVehicles(fee: Int) {
            retiredVehicles=retiredVehicles.copy(first = retiredVehicles.first+1)
            retiredVehicles=retiredVehicles.copy(second = retiredVehicles.second+fee)
    }

    fun showRetiredVehiclesInformation(){
        println("${retiredVehicles.first} vehicles have checked out and have earnings of ${retiredVehicles.second}")
    }

    fun listParking(){
        parkables.map {
            println(it.plate)
        }
    }

}

data class Vehicle(
    val plate: String,
    val type: VehicleType,
    val checkInTime: Calendar,
    val discountCard: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (other is Vehicle) {
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return this.plate.hashCode()
    }
}

enum class VehicleType(val value: Int) {
    CAR(20),
    MOTORCYCLE(15),
    MINIBUS(25),
    BUS(30)
}

fun main() {
    val parking = Parking(mutableSetOf())
    //prueba cargar 20 sabiendo que hay 4 cargados, deberia fallar 4 veces


    val car = Vehicle("AA111AA",VehicleType.CAR, Calendar.getInstance(),"DISCOUNT_CARD_001")
    val moto = Vehicle("BB222BB",VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus = Vehicle("CC333CC",VehicleType.MINIBUS, Calendar.getInstance(),"DISCOUNT_CARD_002")
    val bus = Vehicle("DD444DD",VehicleType.BUS, Calendar.getInstance())

    val car2 = Vehicle("AA111AA",VehicleType.CAR, Calendar.getInstance(),"DISCOUNT_CARD_003")
    val moto2 = Vehicle("EE555EE",VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus2 = Vehicle("FF666FF",VehicleType.MINIBUS, Calendar.getInstance(),"DISCOUNT_CARD_004")
    val bus2 = Vehicle("GG777GG",VehicleType.BUS, Calendar.getInstance())

    val car3 = Vehicle("HH888HH",VehicleType.CAR, Calendar.getInstance())
    val moto3 = Vehicle("II999II",VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus3 = Vehicle("JJ000JJ",VehicleType.MINIBUS, Calendar.getInstance(),"DISCOUNT_CARD_006")
    val bus3 = Vehicle("KK100KK",VehicleType.BUS, Calendar.getInstance())

    val car4 = Vehicle("LL200LL",VehicleType.CAR, Calendar.getInstance(),"DISCOUNT_CARD_007")
    val moto4 = Vehicle("MM300MM",VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus4 = Vehicle("NN400NN",VehicleType.MINIBUS, Calendar.getInstance(),"DISCOUNT_CARD_008")
    val bus4 = Vehicle("OO500OO",VehicleType.BUS, Calendar.getInstance())

    val car5 = Vehicle("PP600PP",VehicleType.CAR, Calendar.getInstance(),"DISCOUNT_CARD_009")
    val moto5 = Vehicle("QQ700QQ",VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus5 = Vehicle("RR800RR",VehicleType.MINIBUS, Calendar.getInstance(),"DISCOUNT_CARD_010")
    val bus5 = Vehicle("SS900SS",VehicleType.BUS, Calendar.getInstance())

    val car6 = Vehicle("TT010TT",VehicleType.CAR, Calendar.getInstance(),"DISCOUNT_CARD_011")
    val moto6 = Vehicle("UU020UU",VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus6 = Vehicle("VV030VV",VehicleType.MINIBUS, Calendar.getInstance(),"DISCOUNT_CARD_012")
    val bus6 = Vehicle("WW040WW",VehicleType.BUS, Calendar.getInstance())

    val listVehicles = listOf(
        car, car2, car3, car4, car5, car6,
        moto, moto2, moto3, moto4, moto5, moto6,
        minibus, minibus2, minibus3, minibus4, minibus5, minibus6,
        bus, bus2, bus3, bus4, bus5, bus6)

    //for (num in 1..30) {
      //  parking.checkIn(Vehicle("AA1${num}1AA", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_001"))
    //}
    listVehicles.map {
        parking.checkIn(it)
    }


    parking.checkOutVehicle("HH888HH",{println("Your fee is $it. Come back soon.")},{ println("Sorry, the check-out failed")})
    parking.checkOutVehicle("RR800RR",{println("Your fee is $it. Come back soon.")},{ println("Sorry, the check-out failed")})
    parking.checkOutVehicle("TT010TT",{println("Your fee is $it. Come back soon.")},{ println("Sorry, the check-out failed")})
    parking.checkOutVehicle("TT010TT",{println("Your fee is $it. Come back soon.")},{ println("Sorry, the check-out failed")})

    parking.listParking()

    parking.showRetiredVehiclesInformation()
}

