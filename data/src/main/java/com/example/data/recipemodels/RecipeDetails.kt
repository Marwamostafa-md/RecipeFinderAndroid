package com.example.data.recipemodels

import com.google.gson.annotations.SerializedName

data class RecipeDetails(
    @SerializedName("id")
    val recipeId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,
    @SerializedName("servings")
    val servings: Int,
    @SerializedName("extendedIngredients")
    val ingredients: List<RecipeIngredient>,
    @SerializedName("instructions")
    val instructions: String?,
    @SerializedName("nutrition")
    val nutrition: RecipeNutrition?
)
