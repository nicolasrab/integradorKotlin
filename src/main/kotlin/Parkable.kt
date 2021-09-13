import java.util.*
import kotlin.math.ceil

data class Parkable(var vehicle: Vehicle) {
    var plate = vehicle.plate
    val parkedTime: Long
        get() = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / MINUTES_IN_MILISECONDS


    fun calculateFee(): Int {
        return applyDiscount(calculatePricePerTime())
    }

    private fun applyDiscount(calculatePricePerTime: Int): Int {
        if (vehicle.discountCard != null)
            return (calculatePricePerTime * 0.85).toInt()
        return calculatePricePerTime
    }

    private fun calculatePricePerTime(): Int {
        var amount: Int = 0
        if (parkedTime <= 120) {
            amount = vehicle.type.value
        } else {
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