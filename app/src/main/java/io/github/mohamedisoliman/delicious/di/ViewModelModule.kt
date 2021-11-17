package io.github.mohamedisoliman.delicious.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@InstallIn(ViewModelComponent::class)
@Module
object ViewModelModule {


    @Provides
    fun provideIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }


}