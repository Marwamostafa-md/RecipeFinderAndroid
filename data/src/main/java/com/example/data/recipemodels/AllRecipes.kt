package com.example.data.recipemodels

import com.google.gson.annotations.SerializedName

data class AllRecipes(
    @SerializedName("recipes")
    val recipes: List<RecipesModel>
)
