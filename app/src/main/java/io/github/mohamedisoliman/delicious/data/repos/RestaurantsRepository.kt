package io.github.mohamedisoliman.delicious.data.repos

import io.github.mohamedisoliman.delicious.data.local.DeliciousLocalContract
import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import io.github.mohamedisoliman.delicious.domain.restaurants.RestaurantsRepositoryContract
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantsRepository @Inject constructor(
    private val local: DeliciousLocalContract,
) : RestaurantsRepositoryContract {

    override fun retrieveRestaurants(): Flow<List<Restaurant>> = local.retrieveRestaurants()
}