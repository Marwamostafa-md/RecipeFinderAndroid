package com.example.recipesfinder.viewmodels.searchviewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetRecipesBySearch
import com.example.recipesfinder.mapper.RecipesDomainToRecipeAppMpdel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecipeSearch: GetRecipesBySearch,
    @Named("IO") private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _searchState: MutableStateFlow<SearchState?> = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState?> = _searchState

    fun searchRecipe(recipe: String) {
        _searchState.value = _searchState.value?.copy(Loading = true)

        getRecipeSearch(recipe)
            .flowOn(dispatcherIO)
            .map { list -> list.map { it.RecipesDomainToRecipeAppMpdel() } }
            .onEach { result ->
                _searchState.value = _searchState.value?.copy(
                    Loading = false,
                    recipes = result,
                    error = null
                )
            }
            .catch { e ->
                Log.e("SearchViewModel", e.message ?: "Unknown error")
                _searchState.value = _searchState.value?.copy(Loading = false, error = e)
            }
            .launchIn(viewModelScope)
    }
}
