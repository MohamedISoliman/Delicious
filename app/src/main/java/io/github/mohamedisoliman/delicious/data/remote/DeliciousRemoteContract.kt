package io.github.mohamedisoliman.delicious.data.remote

import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow

interface DeliciousRemoteContract {

    fun retrieveRestaurants(): Flow<List<Restaurant>>
}