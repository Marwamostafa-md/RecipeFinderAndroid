package com.example.domain.usecase

import com.example.domain.models.RecipeModel
import com.example.domain.recipesrepo.RecipeRepo

class GetAllRecipes (private val recipeRepo: RecipeRepo) {
    suspend operator fun invoke(): List<RecipeModel> =
        recipeRepo.getAllRecipes()

}