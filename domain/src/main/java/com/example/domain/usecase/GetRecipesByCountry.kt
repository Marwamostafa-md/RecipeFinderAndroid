package com.example.domain.usecase

import com.example.domain.models.RecipeModel
import com.example.domain.recipesrepo.RecipeRepo
import kotlinx.coroutines.flow.Flow

class GetRecipesByCountry(private val recipeRepo: RecipeRepo) {
    operator fun invoke(country: String): Flow<List<RecipeModel>> =
        recipeRepo.getCountryCategories(country)
}
