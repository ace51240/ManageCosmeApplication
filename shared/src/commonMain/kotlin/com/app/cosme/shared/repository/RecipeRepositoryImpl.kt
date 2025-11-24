package com.app.cosme.shared.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.app.cosme.shared.db.CosmeDatabase
import com.app.cosme.shared.db.Cosmetic
import com.app.cosme.shared.db.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(private val database: CosmeDatabase) : RecipeRepository {
    private val queries = database.cosmeDatabaseQueries
    override fun getAllRecipes(): Flow<List<Recipe>> {
        return queries.selectAllRecipes().asFlow().mapToList(Dispatchers.Default)
    }

    override suspend fun getRecipeById(id: Long): Recipe? {
        return queries.selectRecipeById(id).executeAsOneOrNull()
    }

    override suspend fun insertRecipe(title: String, memo: String?, createdAt: Long): Long {
        queries.insertRecipe(null, title, memo, createdAt)
        return queries.lastInsertRowId().executeAsOne()
    }

    override suspend fun updateRecipe(id: Long, title: String, memo: String?) {
        queries.updateRecipe(title, memo, id)
    }

    override suspend fun deleteRecipe(id: Long) {
        queries.deleteRecipe(id)
    }

    override suspend fun addCosmeticToRecipe(recipeId: Long, cosmeticId: Long) {
        queries.insertRecipeCosmetic(recipeId, cosmeticId)
    }

    override suspend fun removeCosmeticsFromRecipe(recipeId: Long) {
        queries.deleteRecipeCosmetics(recipeId)
    }

    override fun getCosmeticsForRecipe(recipeId: Long): Flow<List<Cosmetic>> {
        return queries.selectCosmeticsForRecipe(recipeId)
            .asFlow().mapToList(Dispatchers.Default)
    }
}