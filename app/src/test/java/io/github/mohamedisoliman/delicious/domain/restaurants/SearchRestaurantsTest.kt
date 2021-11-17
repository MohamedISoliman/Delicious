package io.github.mohamedisoliman.delicious.domain.restaurants

import io.github.mohamedisoliman.delicious.fakeRestaurant
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


internal class SearchRestaurantsTest {

    @Test
    fun `SearchRestaurants with Empty Query SHOULD return latest Restaurants list`() = runBlocking {
        val query = ""
        val expected = listOf(fakeRestaurant(), fakeRestaurant())

        val result = SearchRestaurants().invoke(query, expected).drop(1).first()

        Assert.assertTrue(result.restaurants?.size == expected.size)
    }

    @Test
    fun `SearchRestaurants with Query and empty list SHOULD return empty list`() = runBlocking {
        val query = "Shawerma"
        val expected = listOf(fakeRestaurant(), fakeRestaurant())

        val result = SearchRestaurants().invoke(query, expected).drop(1).first()

        Assert.assertTrue(result.restaurants?.isEmpty() == true)
        Assert.assertTrue(result.searchText == query)
    }

    @Test
    fun `SearchRestaurants SHOULD always start with SearchResult holding the query`() =
        runBlocking {
            val query = "Sochi"
            val expected = listOf(fakeRestaurant(), fakeRestaurant())

            val result = SearchRestaurants().invoke(query, expected).first()

            Assert.assertTrue(result.searchText == query)
        }

    @Test
    fun `SearchRestaurants SHOULD filter restaurants by name`() = runBlocking {
        val query = "Kabab"
        val element0 = fakeRestaurant(name = "Kabab")
        val element1 = fakeRestaurant(name = "Burgeriza")
        val element2 = fakeRestaurant(name = "Pizza hut")
        val element3 = fakeRestaurant(name = "Bazoka")

        val expected = listOf(element0, element1, element2, element3)

        val result = SearchRestaurants().invoke(query, expected).drop(1).first()

        Assert.assertTrue(result.restaurants?.size == 1)
    }
}