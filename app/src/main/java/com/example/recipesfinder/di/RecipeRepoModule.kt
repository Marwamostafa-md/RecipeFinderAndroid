package com.example.recipesfinder.di

import com.example.data.reciperepo.RecipeRepoImp
import com.example.data.remotservice.RemoteService
import com.example.domain.recipesrepo.RecipeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RecipeRepoModule {
    @Provides
    fun provideRepo(remoteService: RemoteService): RecipeRepo {
        return RecipeRepoImp (remoteService)
    }
}