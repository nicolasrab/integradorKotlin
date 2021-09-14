data class Parking(val parkables: MutableSet<Parkable>) {

    var emptyPlaces = PLACES_AVAILABLES - parkables.size
    var retiredVehicles: Pair<Int, Int> = Pair(0, 0)

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
        if (parkable == null) {
            onError()
        } else {
            val fee = parkable.calculateFee()
            onSuccess(fee)
            parkables.remove(parkable)
            updateRetiredVehicles(fee)
        }

    }

    private fun updateRetiredVehicles(fee: Int) {
        retiredVehicles = retiredVehicles.copy(first = retiredVehicles.first + 1, second = retiredVehicles.second + fee)
    }

    fun showRetiredVehiclesInformation() {
        println("${retiredVehicles.first} vehicles have checked out and have earnings of ${retiredVehicles.second}")
    }

    fun listParking() {
        parkables.map {
            println(it.plate)
        }
    }

}