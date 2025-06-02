package com.example.data.reciperepo

import com.example.data.mapper.toDomainDetailRecipeModel
import com.example.data.mapper.toDomainRecipesModel
import com.example.data.remotservice.RemoteService
import com.example.domain.models.RecipeDetailsModel
import com.example.domain.models.RecipeModel
import com.example.domain.recipesrepo.RecipeRepo

class RecipeRepoImp (private val remoteService: RemoteService) : RecipeRepo {

    override suspend fun getAllRecipes(): List<RecipeModel> {
        return remoteService.getRecipe().recipes.map { it -> it.toDomainRecipesModel() }
    }

    override suspend fun getRecipeDetails(id: Int): RecipeDetailsModel {
        return remoteService.getDetails(id).toDomainDetailRecipeModel()
    }

    override suspend fun searchRecipes(query: String): List<RecipeModel> {
        return remoteService.searchAboutRecipe(query).result.map { it -> it.toDomainRecipesModel()}
    }

    override suspend fun getCountryCategories(country: String): List<RecipeModel> {
        return remoteService.getRecipeDependOnCountry(country).result.map {it->it.toDomainRecipesModel()  }
    }

    override suspend fun getMealCategories(type: String): List<RecipeModel> {
        return remoteService.getRecipeDependOnType(type).result.map {it->it.toDomainRecipesModel()  }

    }


}