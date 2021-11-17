package io.github.mohamedisoliman.delicious.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mohamedisoliman.delicious.domain.restaurants.HomeViewState
import io.github.mohamedisoliman.delicious.domain.restaurants.RetrieveRestaurants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val retrieveRestaurants: RetrieveRestaurants,
) : ViewModel() {

    private val state = MutableStateFlow<HomeViewState>(HomeViewState.Idle)
    fun state() = state.asLiveData()


    init {
        retrieveRestaurants.invoke()
            .onEach { state.value = it }
            .launchIn(viewModelScope)
    }

}