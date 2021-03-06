package io.github.mohamedisoliman.delicious.data.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import io.github.mohamedisoliman.delicious.domain.entities.SortingValues
import io.github.mohamedisoliman.delicious.fakeRestaurant
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class AssetsHelperKtTest {


    val context = ApplicationProvider.getApplicationContext<Context>()


    @Test
    fun readJsonFromAssets_SHOULD_return_valid_Kotlin_object_WHEN_Valid_Json_response_available() {

        val expected = fakeRestaurant()

        val response = context.readResponseFromAssets()

        val first = response?.restaurants?.firstOrNull()

        assertTrue(first == expected)


    }

}