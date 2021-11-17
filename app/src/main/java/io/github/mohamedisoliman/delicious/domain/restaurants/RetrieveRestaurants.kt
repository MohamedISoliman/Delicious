package io.github.mohamedisoliman.delicious.domain.restaurants

import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RetrieveRestaurants @Inject constructor(
    private val repository: RestaurantsRepositoryContract,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : () -> Flow<HomeViewState> {

    override fun invoke(): Flow<HomeViewState> =
        repository.retrieveRestaurants()
            .map { list ->
                if (list.isEmpty()) {
                    HomeViewState.EmptyResult
                } else {
                    HomeViewState.Success(sort(list))
                }
            }.catch {
                emit(HomeViewState.Failure(it))
            }
            .catch {
                emit(HomeViewState.Failure(it))
            }
            .onStart { emit(HomeViewState.Loading) }
            .flowOn(dispatcher)

    private fun sort(list: List<Restaurant>) =
        list.sortedWith(compareBy { it.status.toStatusEnum().order })

}


