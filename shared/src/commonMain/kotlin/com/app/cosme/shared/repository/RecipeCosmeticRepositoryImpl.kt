package com.app.cosme.shared.repository

import Cosmetic
import RecipeCosmetic
import RecipeCosmeticDao
import kotlinx.coroutines.flow.Flow

class RecipeCosmeticRepositoryImpl(private val recipeCosmeticDao: RecipeCosmeticDao) : RecipeCosmeticRepository {
    override suspend fun insertRecipeCosmetic(recipeCosmetic: RecipeCosmetic) = recipeCosmeticDao.insertRecipeCosmetic(recipeCosmetic)

    override suspend fun insertAll(recipeCosmetics: List<RecipeCosmetic>) = recipeCosmeticDao.insertAll(recipeCosmetics)

    override fun getCosmeticsForRecipe(recipeId: Long): Flow<List<Cosmetic>> = recipeCosmeticDao.getCosmeticsForRecipe(recipeId)

    override suspend fun deleteCosmeticsForRecipe(recipeId: Long) = recipeCosmeticDao.deleteCosmeticsForRecipe(recipeId)
}