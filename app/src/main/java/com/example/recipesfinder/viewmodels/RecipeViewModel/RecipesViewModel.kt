package com.example.recipesfinder.viewmodels.RecipeViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetAllRecipes
import com.example.domain.usecase.GetRecipesByCountry
import com.example.domain.usecase.GetRecipesByType
import com.example.recipesfinder.mapper.RecipesDomainToRecipeAppMpdel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetAllRecipes,
    private val getRecipesByType: GetRecipesByType,
    private val getRecipeByCountry: GetRecipesByCountry,
    @Named("IO") private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _recipeState = MutableStateFlow(RecipeState())
    val recipeState: StateFlow<RecipeState> = _recipeState

    fun getRecipes(
        isCategory: Boolean = false,
        typeOrCountry: String? = null,
        category: String = ""
    ) {
        _recipeState.value = _recipeState.value.copy(Loading = true)

        val flow = when {
            isCategory && typeOrCountry == "type" -> getRecipesByType(category)
            isCategory && typeOrCountry == "country" -> getRecipeByCountry(category)
            else -> getRecipesUseCase()
        }

        flow
            .flowOn(dispatcherIO)
            .map { list -> list.map { it.RecipesDomainToRecipeAppMpdel() } }
            .onEach { recipes ->
                _recipeState.value = _recipeState.value.copy(
                    Loading = false,
                    recipes = recipes,
                    error = null
                )
            }
            .catch { e ->
                Log.e("RecipesViewModel", e.message ?: "Unknown Error")
                _recipeState.value = _recipeState.value.copy(
                    Loading = false,
                    error = e
                )
            }
            .launchIn(viewModelScope)
    }
}
