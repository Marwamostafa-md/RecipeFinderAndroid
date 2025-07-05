package com.example.recipesfinder.di

import android.content.Context
import androidx.room.Room
import com.example.data.recipelocaldatabase.LocalDataBase
import com.example.data.recipelocaldatabase.RecipesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeLocalDatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDataBase {
        return Room.databaseBuilder(
            context,
            LocalDataBase::class.java,
            "recipe_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecipesDao(localDataBase: LocalDataBase): RecipesDao {
        return localDataBase.recipesDao()
    }
}
