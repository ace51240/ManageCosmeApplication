package com.app.cosme.shared.usecase

import RecipeCosmetic
import com.app.cosme.shared.repository.RecipeCosmeticRepository

class RegisterNewRecipeCosmeticUseCase(
    private val recipeCosmeticRepository: RecipeCosmeticRepository) {
    suspend operator fun invoke(recipeId: Long, cosmeticIds: List<Long>) {
        recipeCosmeticRepository.insertAll(
            cosmeticIds.map { cosmeticId ->
                RecipeCosmetic(recipe_id = recipeId, cosmetic_id = cosmeticId)
            }
        )
    }
}