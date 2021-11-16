package io.github.mohamedisoliman.delicious.domain.restaurants

import io.github.mohamedisoliman.delicious.data.local.DeliciousLocal
import io.github.mohamedisoliman.delicious.data.repos.RestaurantsRepository
import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import io.github.mohamedisoliman.delicious.fakeRestaurant
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.lang.RuntimeException


class RetrieveRestaurantsTest {

    @Test
    fun `interactor invoked THEN start with Loading state`(): Unit = runBlocking {
        val restaurant = fakeRestaurant()
        val local = mockk<DeliciousLocal>()
        val repo = RestaurantsRepository(local)
        val interactor = RetrieveRestaurants(repository = repo)


        every { local.retrieveRestaurants() } returns flowOf(listOf(restaurant,
            restaurant,
            restaurant)
        )

        val first = interactor.invoke().first()
        assertTrue(first is HomeViewState.Loading && first.isLoading)
    }

    @Test
    fun `interactor invoked With Local empty result THEN return Empty state`() = runBlocking {
        val local = mockk<DeliciousLocal>()
        val repo = RestaurantsRepository(local)
        val interactor = RetrieveRestaurants(repository = repo)

        val expectedSuccessful = emptyList<Restaurant>()

        every { local.retrieveRestaurants() } returns flowOf(expectedSuccessful)

        val state = interactor.invoke().drop(1).first()

        assertTrue(state is HomeViewState.EmptyResult)
        assertTrue(state.restaurants?.isEmpty() == true)
    }

    @Test
    fun `interactor invoked With Local result THEN return success state`() = runBlocking {
        val restaurant = fakeRestaurant()
        val local = mockk<DeliciousLocal>()
        val repo = RestaurantsRepository(local)
        val interactor = RetrieveRestaurants(repository = repo)


        val expectedSuccessful = listOf(restaurant, restaurant, restaurant)

        every { local.retrieveRestaurants() } returns flowOf(expectedSuccessful)

        val state = interactor.invoke().drop(1).first()

        assertTrue(state is HomeViewState.Success)
        assertTrue(state.restaurants?.size == expectedSuccessful.size)
    }

    @Test
    fun `interactor invoked With Exception THEN return Failure state`() = runBlocking {
        val local = mockk<DeliciousLocal>()
        val repo = RestaurantsRepository(local)
        val interactor = RetrieveRestaurants(repository = repo)
        val errorMessage = "Something wrong happened!"
        val runtimeException = RuntimeException(errorMessage)

        every { local.retrieveRestaurants() } returns flow { throw runtimeException }

        val state = interactor.invoke().drop(1).first()

        assertTrue(state is HomeViewState.Failure)
        assertTrue(state.throwable is RuntimeException)
        assertTrue(state.throwable?.message == errorMessage)
    }

}