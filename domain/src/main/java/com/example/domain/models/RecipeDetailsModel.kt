package com.example.domain.models

data class RecipeDetailsModel(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val ingredients: List<RecipeIngredients>,
    val instructions: List<String>,
    val calories: String?,
    val protein: String?,
    val sugar: String?,
    val fiber: String?,
    val carbohydrates: String?
)
