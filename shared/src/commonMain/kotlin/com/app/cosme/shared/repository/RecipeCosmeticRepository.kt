package com.app.cosme.shared.repository

import Cosmetic
import RecipeCosmetic
import kotlinx.coroutines.flow.Flow

interface RecipeCosmeticRepository {
    suspend fun insertRecipeCosmetic(recipeCosmetic: RecipeCosmetic)

    suspend fun insertAll(recipeCosmetics: List<RecipeCosmetic>)

    fun getCosmeticsForRecipe(recipeId: Long): Flow<List<Cosmetic>>

    suspend fun deleteCosmeticsForRecipe(recipeId: Long)
}