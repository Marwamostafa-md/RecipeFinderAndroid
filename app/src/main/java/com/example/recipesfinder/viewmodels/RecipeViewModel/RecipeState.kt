package com.example.recipesfinder.viewmodels.RecipeViewModel

import com.example.recipesfinder.uirecipesmodels.Recipe

data class RecipeState(
    val Loading: Boolean = false,
    val recipes: List<Recipe?> = emptyList(),
    val error: Throwable? = null
) {
    fun isLoading() = Loading && recipes.isEmpty()
    fun isSuccess() = recipes.isNotEmpty() && error == null
    fun isError() = error != null
}
