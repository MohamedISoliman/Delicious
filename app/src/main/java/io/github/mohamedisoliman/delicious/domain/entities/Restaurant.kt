package io.github.mohamedisoliman.delicious.domain.entities


import com.squareup.moshi.Json

data class Restaurant(
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "sortingValues")
    val sortingValues: SortingValues? = SortingValues(),
    @Json(name = "status")
    val status: String? = ""
)