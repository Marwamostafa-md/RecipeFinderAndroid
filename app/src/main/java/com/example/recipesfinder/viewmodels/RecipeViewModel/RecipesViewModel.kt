package com.example.recipesfinder.viewmodels.RecipeViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetAllRecipes
import com.example.domain.usecase.GetRecipesByCountry
import com.example.domain.usecase.GetRecipesByType
import com.example.recipesfinder.mapper.RecipesDomainToRecipeAppMpdel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    val getRecipesUseCase: GetAllRecipes,
    val getRecipesByType: GetRecipesByType,
    val getRecipeByCountry: GetRecipesByCountry
) : ViewModel() {
    private val _recipeState: MutableStateFlow<RecipeState?> = MutableStateFlow(RecipeState())
    val recipeState: StateFlow<RecipeState?> = _recipeState
    fun getRecipes(
        isCategory: Boolean = false,
        typeOrCountry: String? = null,
        category: String = ""
    ) {
        viewModelScope.launch {
            try {
                _recipeState.value = _recipeState.value?.copy(Loading = true)
                if (isCategory) {
                    when (typeOrCountry) {
                        "type" -> {
                            val Recipes =
                                getRecipesByType(category).map { it -> it.RecipesDomainToRecipeAppMpdel() }
                            _recipeState.value =
                                _recipeState.value?.copy(Loading = false, recipes = Recipes)
                        }

                        "country" -> {
                            val Recipes =
                                getRecipeByCountry(category).map { it -> it.RecipesDomainToRecipeAppMpdel() }
                            _recipeState.value =
                                _recipeState.value?.copy(Loading = false, recipes = Recipes)
                        }
                    }
                } else {
                    val recipe =
                        getRecipesUseCase().map { it -> it.RecipesDomainToRecipeAppMpdel() }
                    _recipeState.value =
                        _recipeState.value?.copy(Loading = false, recipes = recipe)
                    Log.e("Recipes///////////", recipe.toString())
                    recipe.forEach { print(it) }
                }
            } catch (e: Exception) {
                Log.e("RecipeDetailsViewModel", e.message.toString())
                _recipeState.value = _recipeState.value?.copy(Loading = false, error = e)
            }
        }
    }
}