package com.example.recipesfinder.viewmodels.searchviewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetRecipesBySearch
import com.example.recipesfinder.mapper.RecipesDomainToRecipeAppMpdel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    val getRecipeSearch: GetRecipesBySearch

) : ViewModel() {
    private val _searchState: MutableStateFlow<SearchState?> = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState?> = _searchState
    fun searchRecipe(recipe: String) {

        viewModelScope.launch {
            try {
                _searchState.value = _searchState.value?.copy(Loading = true)
                val recipedetails =
                    getRecipeSearch(recipe).map { it -> it.RecipesDomainToRecipeAppMpdel() }
                _searchState.value =
                    _searchState.value?.copy(Loading = false, recipes = recipedetails)

            } catch (e: Exception) {
                Log.e("RecipeDetailsViewModel", e.message.toString())
                _searchState.value = _searchState.value?.copy(Loading = false, error = e)
            }
        }

    }


}