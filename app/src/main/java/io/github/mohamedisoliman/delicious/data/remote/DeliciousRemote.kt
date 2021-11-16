package io.github.mohamedisoliman.delicious.data.remote

import android.content.Context
import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeliciousRemote(private val context: Context) : DeliciousRemoteContract {


    override fun retrieveRestaurants(): Flow<List<Restaurant>> = flow {
        val response = context.readResponseFromAssets()
        if (response?.restaurants != null) {
            emit(response.restaurants)
        } else {
            throw NullPointerException("No response found on Assets")
        }
    }


}