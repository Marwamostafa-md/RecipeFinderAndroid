package com.example.domain.usecase

import com.example.domain.models.RecipeDetailsModel
import com.example.domain.recipesrepo.RecipeRepo

class GetRecipesDetails(private val recipeRepo: RecipeRepo) {
    suspend operator fun invoke(id: Int): RecipeDetailsModel =
        recipeRepo.getRecipeDetails(id)

}