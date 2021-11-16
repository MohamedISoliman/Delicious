package io.github.mohamedisoliman.delicious.domain.restaurants

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RetrieveRestaurants(
    private val repository: RestaurantsRepositoryContract,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : () -> Flow<HomeViewState> {

    override fun invoke(): Flow<HomeViewState> =
        repository.retrieveRestaurants()
            .map {
                if (it.isEmpty()) {
                    HomeViewState.EmptyResult
                } else {
                    HomeViewState.Success(it)
                }
            }.catch {
                emit(HomeViewState.Failure(it))
            }
            .onStart { emit(HomeViewState.Loading) }
            .flowOn(dispatcher)

}


