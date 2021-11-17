package io.github.mohamedisoliman.delicious

import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import io.github.mohamedisoliman.delicious.domain.entities.SortingValues

fun fakeRestaurant(name: String = "Tanoshii Sushi") = Restaurant(
    name = name,
    status = "open",
    sortingValues = SortingValues(
        bestMatch = 0.0,
        newest = 96.0,
        ratingAverage = 4.5,
        distance = 1190,
        popularity = 17.0,
        averageProductPrice = 1536,
        deliveryCosts = 200,
        minCost = 1000
    )
)