package com.example.data.mapper

import com.example.data.local.entities.RecipeEntity
import com.example.domain.models.RecipeModel

fun RecipeModel.toEntity(): RecipeEntity {
    return RecipeEntity(
        id = id,
        title = title,
        image = image,
    )
}