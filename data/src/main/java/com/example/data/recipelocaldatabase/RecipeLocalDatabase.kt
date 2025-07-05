package com.example.data.recipelocaldatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.entities.RecipeEntity


@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}
