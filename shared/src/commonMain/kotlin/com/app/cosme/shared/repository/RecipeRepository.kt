package com.app.cosme.shared.repository

import Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getAllRecipes(): Flow<List<Recipe>>

    fun getRecipeById(id: Long): Flow<Recipe>

    suspend fun insertRecipe(recipe: Recipe): Long

    suspend fun updateRecipe(recipe: Recipe)

    suspend fun deleteRecipe(recipe: Recipe)
}