package com.example.recipesfinder.di

import com.example.domain.recipesrepo.RecipeRepo
import com.example.domain.usecase.GetAllRecipes
import com.example.domain.usecase.GetRecipesByCountry
import com.example.domain.usecase.GetRecipesBySearch
import com.example.domain.usecase.GetRecipesByType
import com.example.domain.usecase.GetRecipesDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RecipeUseCaseModule {

    @Provides
    fun provideAllRecipe(recipeRepo: RecipeRepo): GetAllRecipes {
        return GetAllRecipes(recipeRepo)
    }

    @Provides
    fun provideRecipeDetails(recipeRepo: RecipeRepo): GetRecipesDetails {
        return GetRecipesDetails(recipeRepo)
    }

    @Provides
    fun provideRecipeByCountry(recipeRepo: RecipeRepo): GetRecipesByCountry {
        return GetRecipesByCountry(recipeRepo)
    }

    @Provides
    fun provideRecipeSearch(recipeRepo: RecipeRepo): GetRecipesBySearch {
        return GetRecipesBySearch(recipeRepo)
    }

    @Provides
    fun provideRecipeByType(recipeRepo: RecipeRepo): GetRecipesByType {
        return GetRecipesByType(recipeRepo)
    }
}

