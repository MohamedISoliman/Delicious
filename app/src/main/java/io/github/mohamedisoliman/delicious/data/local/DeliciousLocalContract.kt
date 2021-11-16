package io.github.mohamedisoliman.delicious.data.local

import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow

interface DeliciousLocalContract {

    fun retrieveRestaurants(): Flow<List<Restaurant>>
}