package com.example.data.mapper

import com.example.data.recipemodels.RecipeDetails
import com.example.domain.models.RecipeDetailsModel
import com.example.domain.models.RecipeIngredients

fun RecipeDetails.toDomainDetailRecipeModel(): RecipeDetailsModel {

    fun getNutrient(name: String, unit: String): String? {
        return this.nutrition?.nutrients
            ?.find { it.name.equals(name, ignoreCase = true) }
            ?.amount
            ?.let { "$it $unit" }
    }

    return RecipeDetailsModel(
        id = recipeId,
        title = title,
        image = image,
        readyInMinutes = readyInMinutes,
        servings = servings,
        ingredients = ingredients.map {
            RecipeIngredients(
                id = it.id,
                name = it.name,
                amount = it.amount,
                unit = it.unit
            )
        },
        instructions = instructions
            ?.split("\n")
            ?.map { it.trim() }
            ?.filter { it.isNotEmpty() }
            ?: emptyList(),
        calories = getNutrient("Calories", "kcal"),
        protein = getNutrient("Protein", "g"),
        sugar = getNutrient("Sugar", "g"),
        fiber = getNutrient("Fiber", "g"),
        carbohydrates = getNutrient("Carbohydrates", "g")
    )
}