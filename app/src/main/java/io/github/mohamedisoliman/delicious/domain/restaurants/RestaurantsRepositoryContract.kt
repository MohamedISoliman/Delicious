package io.github.mohamedisoliman.delicious.domain.restaurants

import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantsRepositoryContract {

    fun retrieveRestaurants(): Flow<List<Restaurant>>
}