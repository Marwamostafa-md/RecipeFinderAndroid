package com.example.recipesfinder.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

const val DISPATCHER_IO = "IO"
const val DISPATCHER_MAIN = "Main"
const val DISPATCHER_DEFAULT = "Default"

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Singleton
    @Named(DISPATCHER_IO)
    fun provideIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    @Named(DISPATCHER_MAIN)
    fun provideMain(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Provides
    @Singleton
    @Named(DISPATCHER_DEFAULT)
    fun provideDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}
