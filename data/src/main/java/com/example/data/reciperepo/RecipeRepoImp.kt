package com.example.data.reciperepo

import com.example.data.mapper.toDomainRecipesModel
import com.example.data.mapper.toEntity
import com.example.data.mapper.toDomain
import com.example.data.mapper.toDomainDetailRecipeModel
import com.example.data.remotservice.RemoteService
import com.example.data.remotservice.RecipeLocalSource
import com.example.domain.models.RecipeDetailsModel
import com.example.domain.models.RecipeModel
import com.example.domain.recipesrepo.RecipeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RecipeRepoImp(
    private val remoteService: RemoteService,
    private val localSource: RecipeLocalSource
) : RecipeRepo {

    override fun getAllRecipes(): Flow<List<RecipeModel>> = flow {
        try {
            val remoteResponse = remoteService.getRecipe()
            val mapped = remoteResponse.recipes.map { it.toDomainRecipesModel() }
            localSource.insertRecipe(mapped.map { it.toEntity() })

            emit(mapped)
        } catch (e: Exception) {
            try {
                emitAll(localSource.getAllRecipes().map { list -> list.map { it.toDomain() } })
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override fun getRecipeDetails(id: Int): Flow<RecipeDetailsModel> = flow {
        val response = remoteService.getDetails(id).toDomainDetailRecipeModel()
        emit(response)
    }

    override fun searchRecipes(query: String): Flow<List<RecipeModel>> = flow {
        val response = remoteService.searchAboutRecipe(query).result.map { it.toDomainRecipesModel() }
        emit(response)
    }

    override fun getCountryCategories(country: String): Flow<List<RecipeModel>> = flow {
        val response = remoteService.getRecipeDependOnCountry(country).result.map { it.toDomainRecipesModel() }
        emit(response)
    }

    override fun getMealCategories(type: String): Flow<List<RecipeModel>> = flow {
        val response = remoteService.getRecipeDependOnType(type).result.map { it.toDomainRecipesModel() }
        emit(response)
    }
}
