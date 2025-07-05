package com.example.domain.usecase

import com.example.domain.models.RecipeModel
import com.example.domain.recipesrepo.RecipeRepo
import kotlinx.coroutines.flow.Flow

class GetRecipesByType(private val recipeRepo: RecipeRepo) {
    operator fun invoke(type: String): Flow<List<RecipeModel>> =
        recipeRepo.getMealCategories(type)
}
