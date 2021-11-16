package io.github.mohamedisoliman.delicious.data.remote

import android.content.Context
import com.squareup.moshi.Moshi
import io.github.mohamedisoliman.delicious.domain.entities.DeliciousResponse

private const val JSON_FILE_NAME = "restaurants_response.json"

fun Context.readResponseFromAssets(): DeliciousResponse? {
    val moshi = Moshi.Builder()
        .build()

    val adapter = moshi.adapter(DeliciousResponse::class.java)

    val myjson = assets.open(JSON_FILE_NAME).bufferedReader().use { it.readText() }

    return adapter.fromJson(myjson)
}