import java.util.*

// Respuesta Ejercicio 1: en un Set no importa el orden, y además no pueden existir dos elementos iguales.

const val MINUTES_IN_MILISECONDS = 60000
const val PLACES_AVAILABLES = 20

fun main() {
    val parking = Parking(mutableSetOf())

    val listVehicles = initData()

    listVehicles.map {
        parking.checkIn(it)
    }

    parking.checkOutVehicle("HH888HH",
        { println("Your fee is $it. Come back soon.") },
        { println("Sorry, the check-out failed") })

    parking.checkOutVehicle("RR800RR",
        { println("Your fee is $it. Come back soon.") },
        { println("Sorry, the check-out failed") })

    parking.checkOutVehicle("TT010TT",
        { println("Your fee is $it. Come back soon.") },
        { println("Sorry, the check-out failed") })

    parking.checkOutVehicle("TT010TT",
        { println("Your fee is $it. Come back soon.") },
        { println("Sorry, the check-out failed") })

    parking.listParking()

    parking.showRetiredVehiclesInformation()
}

fun initData(): List<Vehicle> {

    val car = Vehicle("AA111AA", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_001")
    val moto = Vehicle("BB222BB", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus = Vehicle("CC333CC", VehicleType.MINIBUS, Calendar.getInstance(), "DISCOUNT_CARD_002")
    val bus = Vehicle("DD444DD", VehicleType.BUS, Calendar.getInstance())

    //car2 repetido! debe fallar
    val car2 = Vehicle("AA111AA", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_003")

    val moto2 = Vehicle("EE555EE", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus2 = Vehicle("FF666FF", VehicleType.MINIBUS, Calendar.getInstance(), "DISCOUNT_CARD_004")
    val bus2 = Vehicle("GG777GG", VehicleType.BUS, Calendar.getInstance())

    val car3 = Vehicle("HH888HH", VehicleType.CAR, Calendar.getInstance())
    val moto3 = Vehicle("II999II", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus3 = Vehicle("JJ000JJ", VehicleType.MINIBUS, Calendar.getInstance(), "DISCOUNT_CARD_006")
    val bus3 = Vehicle("KK100KK", VehicleType.BUS, Calendar.getInstance())

    val car4 = Vehicle("LL200LL", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_007")
    val moto4 = Vehicle("MM300MM", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus4 = Vehicle("NN400NN", VehicleType.MINIBUS, Calendar.getInstance(), "DISCOUNT_CARD_008")
    val bus4 = Vehicle("OO500OO", VehicleType.BUS, Calendar.getInstance())

    val car5 = Vehicle("PP600PP", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_009")
    val moto5 = Vehicle("QQ700QQ", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus5 = Vehicle("RR800RR", VehicleType.MINIBUS, Calendar.getInstance(), "DISCOUNT_CARD_010")
    val bus5 = Vehicle("SS900SS", VehicleType.BUS, Calendar.getInstance())

    val car6 = Vehicle("TT010TT", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_011")
    val moto6 = Vehicle("UU020UU", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus6 = Vehicle("VV030VV", VehicleType.MINIBUS, Calendar.getInstance(), "DISCOUNT_CARD_012")
    val bus6 = Vehicle("WW040WW", VehicleType.BUS, Calendar.getInstance())

    return listOf(
        car, car2, car3, car4, car5, car6,
        moto, moto2, moto3, moto4, moto5, moto6,
        minibus, minibus2, minibus3, minibus4, minibus5, minibus6,
        bus, bus2, bus3, bus4, bus5, bus6
    )
}

