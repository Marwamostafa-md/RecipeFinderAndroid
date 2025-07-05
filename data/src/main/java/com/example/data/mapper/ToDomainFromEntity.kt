package com.example.data.mapper

import com.example.data.local.entities.RecipeEntity
import com.example.domain.models.RecipeModel

fun RecipeEntity.toDomain(): RecipeModel {
    return RecipeModel(
        id = id,
        title = title,
        image = image,
    );
}