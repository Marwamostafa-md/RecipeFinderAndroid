package com.example.data.recipemodels

import com.google.gson.annotations.SerializedName

data class RecipesModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String
)
