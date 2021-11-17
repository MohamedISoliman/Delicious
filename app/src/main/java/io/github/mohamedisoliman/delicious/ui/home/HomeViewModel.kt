package io.github.mohamedisoliman.delicious.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mohamedisoliman.delicious.domain.restaurants.HomeViewState
import io.github.mohamedisoliman.delicious.domain.restaurants.RetrieveRestaurants
import io.github.mohamedisoliman.delicious.domain.restaurants.SearchRestaurants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val retrieveRestaurants: RetrieveRestaurants,
    private val searchRestaurants: SearchRestaurants,
) : ViewModel() {

    private val state = MutableStateFlow<HomeViewState>(HomeViewState.Idle)
    fun state() = state.asLiveData()

    private val searchQuery = MutableStateFlow("")


    init {
        setupRetrieveRestaurantsInteractor()
        setupSearchInteractor()
    }

    private fun setupRetrieveRestaurantsInteractor() {
        retrieveRestaurants.invoke()
            .onEach { state.value = it }
            .launchIn(viewModelScope)
    }

    private fun setupSearchInteractor() {
        searchQuery.combine(retrieveRestaurants.invoke()) { query, st -> query to st }
            .flatMapLatest { searchRestaurants.invoke(it.first, it.second.restaurants) }
            .onEach { state.value = it }
            .launchIn(viewModelScope)
    }

    fun search(query: String) {
        searchQuery.value = query
    }

}