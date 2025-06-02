package com.example.domain.recipesrepo

import com.example.domain.models.RecipeDetailsModel
import com.example.domain.models.RecipeModel

interface RecipeRepo {
    suspend fun getAllRecipes(): List<RecipeModel>
    suspend fun getRecipeDetails(id: Int): RecipeDetailsModel
    suspend fun searchRecipes(query:String): List<RecipeModel>
    suspend fun getCountryCategories(country:String): List<RecipeModel>
    suspend fun getMealCategories(type:String): List<RecipeModel>

}