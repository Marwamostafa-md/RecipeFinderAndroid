package com.example.domain.usecase

import com.example.domain.models.RecipeModel
import com.example.domain.recipesrepo.RecipeRepo
import kotlinx.coroutines.flow.Flow

class GetRecipesBySearch(private val recipeRepo: RecipeRepo) {
    operator fun invoke(recipe: String): Flow<List<RecipeModel>> =
        recipeRepo.searchRecipes(recipe)
}
