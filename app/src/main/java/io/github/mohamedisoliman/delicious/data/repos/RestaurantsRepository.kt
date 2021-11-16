package io.github.mohamedisoliman.delicious.data.repos

import io.github.mohamedisoliman.delicious.data.local.DeliciousLocal
import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import io.github.mohamedisoliman.delicious.domain.restaurants.RestaurantsRepositoryContract
import kotlinx.coroutines.flow.Flow

class RestaurantsRepository(
    private val local: DeliciousLocal,
) : RestaurantsRepositoryContract {

    override fun retrieveRestaurants(): Flow<List<Restaurant>> = local.retrieveRestaurants()
}