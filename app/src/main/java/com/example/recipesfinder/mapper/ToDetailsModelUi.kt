package com.example.recipesfinder.mapper

import com.example.domain.models.RecipeDetailsModel
import com.example.domain.models.RecipeIngredients
import com.example.recipesfinder.uirecipesmodels.Ingredients
import com.example.recipesfinder.uirecipesmodels.RecipesDetailsUiModel

fun RecipeDetailsModel.toAppDetails(): RecipesDetailsUiModel {
    return RecipesDetailsUiModel(
        id = id,
        title = title,
        image = image,
        readyInMinutes = readyInMinutes,
        servings = servings,
        ingredients = ingredients.map { it.toUi() },
        instructions = instructions,
        calories = calories,
        protein = protein,
        sugar = sugar,
        fiber = fiber,
        carbohydrates = carbohydrates
    )
}


fun RecipeIngredients.toUi(): Ingredients {
    return Ingredients(
        id=id,
        name = name,
        amount = amount,
        unit = unit

    )
}

