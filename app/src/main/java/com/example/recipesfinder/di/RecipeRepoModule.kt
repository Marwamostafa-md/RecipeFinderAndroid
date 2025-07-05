package com.example.recipesfinder.di

import com.example.data.recipelocaldatabase.RecipesDao
import com.example.data.remotservice.RecipeLocalSource
import com.example.data.remotservice.RecipesLocalDataSourceImpl
import com.example.data.remotservice.RemoteService
import com.example.data.reciperepo.RecipeRepoImp
import com.example.domain.recipesrepo.RecipeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeRepoModule {

    @Provides
    @Singleton
    fun provideRecipeLocalSource(dao: RecipesDao): RecipeLocalSource =
        RecipesLocalDataSourceImpl(dao)

    @Provides
    @Singleton
    fun provideRecipeRepo(
        remoteService: RemoteService,
        localSource: RecipeLocalSource
    ): RecipeRepo {
        return RecipeRepoImp(remoteService, localSource)
    }
}
