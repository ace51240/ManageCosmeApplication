package com.app.cosme.shared.repository

import Recipe
import RecipeDao
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(private val recipeDao: RecipeDao) : RecipeRepository {
    override fun getAllRecipes(): Flow<List<Recipe>> = recipeDao.getAllRecipes()

    override fun getRecipeById(id: Long): Flow<Recipe> = recipeDao.getRecipeById(id)

    override suspend fun insertRecipe(recipe: Recipe): Long = recipeDao.insertRecipe(recipe)

    override suspend fun updateRecipe(recipe: Recipe) = recipeDao.updateRecipe(recipe)

    override suspend fun deleteRecipe(recipe: Recipe) = recipeDao.deleteRecipe(recipe)
}