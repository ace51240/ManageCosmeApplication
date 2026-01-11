package com.app.cosme.shared.usecase

import Recipe
import com.app.cosme.shared.repository.RecipeRepository
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class RegisterRecipeUseCase(private val recipeRepository: RecipeRepository) {
    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(
        title: String,
        memo: String?
    ) {
        recipeRepository.insertRecipe(
            Recipe(
                id = 0,
                title = title,
                memo = memo,
                created_at = Clock.System.now().toEpochMilliseconds()
            )
        )
    }
}
