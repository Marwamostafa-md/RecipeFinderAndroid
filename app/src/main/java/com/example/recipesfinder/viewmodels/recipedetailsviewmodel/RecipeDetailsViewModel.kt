package com.example.recipesfinder.viewmodels.recipedetailsviewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetRecipesDetails
import com.example.recipesfinder.mapper.toAppDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipesDetails: GetRecipesDetails,
    @Named("IO") private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _recipeDetailsState: MutableStateFlow<RecipeDetailsState?> =
        MutableStateFlow(RecipeDetailsState())
    val recipeDetailsState: StateFlow<RecipeDetailsState?> = _recipeDetailsState

    fun getDetails(id: Int) {
        _recipeDetailsState.value = _recipeDetailsState.value?.copy(Loading = true)

        getRecipesDetails(id)
            .flowOn(dispatcherIO)
            .map { it.toAppDetails() }
            .onEach { details ->
                _recipeDetailsState.value = _recipeDetailsState.value?.copy(
                    Loading = false,
                    recipeDetails = details,
                    error = null
                )
            }
            .catch { e ->
                Log.e("RecipeDetailsViewModel", e.message ?: "Unknown Error")
                _recipeDetailsState.value = _recipeDetailsState.value?.copy(
                    Loading = false,
                    error = e
                )
            }
            .launchIn(viewModelScope)
    }
}
