package com.example.domain.recipesrepo

import com.example.domain.models.RecipeDetailsModel
import com.example.domain.models.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepo {
    fun getAllRecipes(): Flow<List<RecipeModel>>
    fun getRecipeDetails(id: Int): Flow<RecipeDetailsModel>
    fun searchRecipes(query: String): Flow<List<RecipeModel>>
    fun getCountryCategories(country: String): Flow<List<RecipeModel>>
    fun getMealCategories(type: String): Flow<List<RecipeModel>>
}
