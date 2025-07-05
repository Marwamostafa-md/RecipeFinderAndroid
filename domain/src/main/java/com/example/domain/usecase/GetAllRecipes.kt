package com.example.domain.usecase

import com.example.domain.models.RecipeModel
import com.example.domain.recipesrepo.RecipeRepo
import kotlinx.coroutines.flow.Flow

class GetAllRecipes(private val recipeRepo: RecipeRepo) {
    operator fun invoke(): Flow<List<RecipeModel>> =
        recipeRepo.getAllRecipes()
}
