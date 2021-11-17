package io.github.mohamedisoliman.delicious.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.mohamedisoliman.delicious.data.local.DeliciousLocal
import io.github.mohamedisoliman.delicious.data.local.DeliciousLocalContract
import io.github.mohamedisoliman.delicious.data.repos.RestaurantsRepository
import io.github.mohamedisoliman.delicious.domain.restaurants.RestaurantsRepositoryContract
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun providesLocal(@ApplicationContext context: Context): DeliciousLocalContract {
        return DeliciousLocal(context)
    }


    @Provides
    fun providesRepository(local: DeliciousLocalContract): RestaurantsRepositoryContract {
        return RestaurantsRepository(local)
    }

}