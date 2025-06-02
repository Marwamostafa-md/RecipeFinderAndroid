package com.example.domain.usecase

import com.example.domain.models.RecipeModel
import com.example.domain.recipesrepo.RecipeRepo

class GetRecipesBySearch(private val recipeRepo: RecipeRepo) {
    suspend operator fun invoke(recipe: String): List<RecipeModel> =
        recipeRepo.searchRecipes(recipe)

}