package com.example.domain.usecase

import com.example.domain.models.RecipeDetailsModel
import com.example.domain.recipesrepo.RecipeRepo
import kotlinx.coroutines.flow.Flow

class GetRecipesDetails(private val recipeRepo: RecipeRepo) {
    operator fun invoke(id: Int): Flow<RecipeDetailsModel> =
        recipeRepo.getRecipeDetails(id)
}
