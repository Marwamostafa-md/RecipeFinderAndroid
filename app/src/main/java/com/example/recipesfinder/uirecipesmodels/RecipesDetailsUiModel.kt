package com.example.recipesfinder.uirecipesmodels

data class RecipesDetailsUiModel(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val ingredients: List<Ingredients>,
    val instructions: List<String>,
    val calories: String?,
    val protein: String?,
    val sugar: String?,
    val fiber: String?,
    val carbohydrates: String?
)
