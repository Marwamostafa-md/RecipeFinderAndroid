package com.example.domain.usecase

import com.example.domain.models.RecipeModel
import com.example.domain.recipesrepo.RecipeRepo

class GetRecipesByType (private val recipeRepo: RecipeRepo) {
    suspend operator fun invoke(type: String): List<RecipeModel> =
        recipeRepo.getMealCategories(type)


}