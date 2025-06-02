package com.example.data.recipemodels

import com.google.gson.annotations.SerializedName

data class RecipeNutrition(
    @SerializedName("nutrients")
    val nutrients: List<RecipeNutrient>
)
