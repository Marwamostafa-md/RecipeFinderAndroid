package com.example.recipesfinder.viewmodels.recipedetailsviewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetRecipesDetails
import com.example.recipesfinder.mapper.toAppDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    val getRecipesDetails: GetRecipesDetails
) : ViewModel() {
    private val _recipeDetailsState: MutableStateFlow<RecipeDetailsState?> =
        MutableStateFlow(RecipeDetailsState())
    val recipeDetailsState: StateFlow<RecipeDetailsState?> = _recipeDetailsState
    fun getDetails(id: Int) {

        viewModelScope.launch {
            try {
                _recipeDetailsState.value = _recipeDetailsState.value?.copy(Loading = true)
                val recipedetails =
                    getRecipesDetails(id).toAppDetails()
                _recipeDetailsState.value =
                    _recipeDetailsState.value?.copy(
                        Loading = false,
                        recipeDetails = recipedetails
                    )

            } catch (e: Exception) {
                Log.e("RecipeDetailsViewModel", e.message.toString())
                _recipeDetailsState.value =
                    _recipeDetailsState.value?.copy(Loading = false, error = e)
            }
        }

    }


}