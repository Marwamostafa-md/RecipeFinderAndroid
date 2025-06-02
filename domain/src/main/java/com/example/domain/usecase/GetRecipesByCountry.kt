package com.example.domain.usecase

import com.example.domain.models.RecipeModel
import com.example.domain.recipesrepo.RecipeRepo

class GetRecipesByCountry(private val recipeRepo: RecipeRepo) {
    suspend operator fun invoke(country: String): List<RecipeModel> =
        recipeRepo.getCountryCategories(country)


}