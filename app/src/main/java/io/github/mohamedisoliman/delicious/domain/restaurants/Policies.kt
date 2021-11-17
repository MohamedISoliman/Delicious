package io.github.mohamedisoliman.delicious.domain.restaurants


enum class RestaurantStatus(val order: Int) {
    OPEN(1),
    ORDER_AHEAD(2),
    CLOSED(3),
    UNKNOWN(0)
}

fun String?.toStatusEnum(): RestaurantStatus {
    return when (this) {
        "open" -> RestaurantStatus.OPEN
        "closed" -> RestaurantStatus.CLOSED
        "order ahead" -> RestaurantStatus.ORDER_AHEAD
        else -> RestaurantStatus.UNKNOWN
    }
}