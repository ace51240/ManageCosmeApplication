package com.app.cosme.shared.usecase

import RecipeCosmetic
import com.app.cosme.shared.repository.RecipeCosmeticRepository

class AddRecipeCosmeticUseCase(private val recipeCosmeticRepository: RecipeCosmeticRepository) {
    suspend operator fun invoke(recipeId: Long, cosmeticId: Long) {
        recipeCosmeticRepository.insertRecipeCosmetic(RecipeCosmetic(recipeId, cosmeticId))
    }
}