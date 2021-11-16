package io.github.mohamedisoliman.delicious.domain.entities


import com.squareup.moshi.Json

data class SortingValues(
    @Json(name = "averageProductPrice")
    val averageProductPrice: Int? = 0,
    @Json(name = "bestMatch")
    val bestMatch: Double? = 0.0,
    @Json(name = "deliveryCosts")
    val deliveryCosts: Int? = 0,
    @Json(name = "distance")
    val distance: Int? = 0,
    @Json(name = "minCost")
    val minCost: Int? = 0,
    @Json(name = "newest")
    val newest: Double? = 0.0,
    @Json(name = "popularity")
    val popularity: Double? = 0.0,
    @Json(name = "ratingAverage")
    val ratingAverage: Double? = 0.0
)