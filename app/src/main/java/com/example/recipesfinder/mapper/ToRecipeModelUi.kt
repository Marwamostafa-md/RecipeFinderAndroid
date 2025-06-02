package com.example.recipesfinder.mapper

import com.example.domain.models.RecipeModel
import com.example.recipesfinder.uirecipesmodels.Recipe

fun RecipeModel.RecipesDomainToRecipeAppMpdel(): Recipe {
    return Recipe(
        id = id,
        title = title,
        image = image
    )
}