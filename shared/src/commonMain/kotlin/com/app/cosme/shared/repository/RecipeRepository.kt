package com.app.cosme.shared.repository

import com.app.cosme.shared.db.Cosmetic
import com.app.cosme.shared.db.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getAllRecipes(): Flow<List<Recipe>>
    suspend fun getRecipeById(id: Long): Recipe?
    suspend fun insertRecipe(title: String, memo: String?, createdAt: Long): Long
    suspend fun updateRecipe(id: Long, title: String, memo: String?)
    suspend fun deleteRecipe(id: Long)
    suspend fun addCosmeticToRecipe(recipeId: Long, cosmeticId: Long)
    suspend fun removeCosmeticsFromRecipe(recipeId: Long)
    fun getCosmeticsForRecipe(recipeId: Long): Flow<List<Cosmetic>>
}