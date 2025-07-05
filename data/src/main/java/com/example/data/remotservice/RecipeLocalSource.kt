package com.example.data.remotservice

import com.example.data.local.entities.RecipeEntity
import com.example.data.recipelocaldatabase.RecipesDao
import kotlinx.coroutines.flow.Flow

interface RecipeLocalSource {
    suspend fun insertRecipe(recipes: List<RecipeEntity>)
    fun getAllRecipes(): Flow<List<RecipeEntity>>
}
class RecipesLocalDataSourceImpl(private val dao: RecipesDao):RecipeLocalSource{
    override suspend fun insertRecipe(recipes: List<RecipeEntity>) =dao.insertAll(recipes);

    override fun getAllRecipes(): Flow<List<RecipeEntity>> =dao.getAllRecipes();

}