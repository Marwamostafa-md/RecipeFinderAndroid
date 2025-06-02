package com.example.data.recipemodels

import com.google.gson.annotations.SerializedName

data class RecipeBySearchModel(
    @SerializedName("results")
    val result: List<RecipesModel>
)
