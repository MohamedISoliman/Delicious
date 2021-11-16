package io.github.mohamedisoliman.delicious.domain.entities


import com.squareup.moshi.Json

data class DeliciousResponse(
    @Json(name = "restaurants")
    val restaurants: List<Restaurant>? = listOf()
)