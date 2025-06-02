package com.example.data.recipemodels

import com.google.gson.annotations.SerializedName

data class RecipeIngredient(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("unit")
    val unit: String
)
