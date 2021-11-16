package io.github.mohamedisoliman.delicious.domain.restaurants

import io.github.mohamedisoliman.delicious.domain.entities.Restaurant

sealed class HomeViewState(
    val restaurants: List<Restaurant>? = null,
    val isLoading: Boolean = false,
    val throwable: Throwable? = null,
) {
    object Idle : HomeViewState()
    object Loading : HomeViewState(isLoading = true)
    object EmptyResult : HomeViewState(restaurants = emptyList())
    data class Success(val result: List<Restaurant>) : HomeViewState(restaurants = result)
    data class Failure(val error: Throwable) : HomeViewState(throwable = error)

}