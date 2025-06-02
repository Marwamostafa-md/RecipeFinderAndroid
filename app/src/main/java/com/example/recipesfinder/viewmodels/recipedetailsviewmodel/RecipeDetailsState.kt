package com.example.recipesfinder.viewmodels.recipedetailsviewmodel

import com.example.recipesfinder.uirecipesmodels.RecipesDetailsUiModel

data class RecipeDetailsState(
    val Loading: Boolean = false,
    val recipeDetails: RecipesDetailsUiModel? = null,
    val error: Throwable? = null
) {
    fun isLoading() = Loading && recipeDetails == null
    fun isSuccess() = recipeDetails != null && error == null
    fun isError() = error != null


}