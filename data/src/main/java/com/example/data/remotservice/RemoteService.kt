package com.example.data.remotservice

import com.example.data.recipemodels.AllRecipes
import com.example.data.recipemodels.RecipeBySearchModel
import com.example.data.recipemodels.RecipeDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {
    @GET("recipes/random")
    suspend fun getRecipe(
        @Query("apiKey") apiKey: String = "5248a6198809483bb7b85b94e28e0f1e",
        @Query("number") number: Int = 100,
    ): AllRecipes

    @GET("recipes/{id}/information")
    suspend fun getDetails(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean = true,
        @Query("apiKey") apiKey: String = "5248a6198809483bb7b85b94e28e0f1e",
    ): RecipeDetails

    @GET("recipes/complexSearch")
    suspend fun getRecipeDependOnType(
        @Query("type") type: String,
        @Query("number") number: Int = 30,
        @Query("apiKey") apiKey: String = "5248a6198809483bb7b85b94e28e0f1e",
    ): RecipeBySearchModel

    @GET("recipes/complexSearch")
    suspend fun getRecipeDependOnCountry(
        @Query("cuisine") cuisine: String,
        @Query("number") number: Int = 30,
        @Query("apiKey") apiKey: String = "5248a6198809483bb7b85b94e28e0f1e",
    ): RecipeBySearchModel

    @GET("recipes/complexSearch")
    suspend fun searchAboutRecipe(
        @Query("query") query: String,
        @Query("number") number: Int = 30,
        @Query("apiKey") apiKey: String = "5248a6198809483bb7b85b94e28e0f1e",
    ): RecipeBySearchModel
}