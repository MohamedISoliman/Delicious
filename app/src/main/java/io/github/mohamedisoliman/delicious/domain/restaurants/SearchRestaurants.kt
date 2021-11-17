package io.github.mohamedisoliman.delicious.domain.restaurants

import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SearchRestaurants @Inject constructor() : (String, List<Restaurant>?) -> Flow<HomeViewState> {

    override fun invoke(query: String, restaurants: List<Restaurant>?): Flow<HomeViewState> =
        flow<HomeViewState> {
            if (query.isEmpty()) {
                emit(HomeViewState.SearchResult(text = query, data = restaurants))
            } else {
                val result = restaurants?.filter { it.name?.contains(query, true) ?: false }
                emit(HomeViewState.SearchResult(query, result ?: emptyList()))
            }

        }.onStart {
            emit(HomeViewState.SearchResult(text = query, data = restaurants))
        }.flowOn(Dispatchers.IO)

}
