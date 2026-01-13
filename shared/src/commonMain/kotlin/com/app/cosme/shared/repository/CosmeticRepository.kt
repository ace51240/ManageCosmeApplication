package com.app.cosme.shared.repository

import Cosmetic
import kotlinx.coroutines.flow.Flow

interface CosmeticRepository {
    fun getAllCosmetics(): Flow<List<Cosmetic>>

    fun getCosmeticById(id: Long): Flow<Cosmetic>

    suspend fun insertCosmetic(cosmetic: Cosmetic)

    suspend fun updateCosmetic(cosmetic: Cosmetic)

    suspend fun deleteCosmetic(cosmetic: Cosmetic)
}