package com.example.data.mapper

import com.example.data.recipemodels.RecipesModel
import com.example.domain.models.RecipeModel


fun RecipesModel.toDomainRecipesModel(): RecipeModel {
    return RecipeModel(
        id = id,
        title = title,
        image = image


    )
}