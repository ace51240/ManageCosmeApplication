package com.app.cosme.shared.repository

import com.app.cosme.shared.db.Cosmetic
import kotlinx.coroutines.flow.Flow

interface CosmeticRepository {
    fun getAllCosmetics(): Flow<List<Cosmetic>>
    suspend fun getCosmeticById(id: Long): Cosmetic?
    suspend fun insertCosmetic(
        brandName: String?,
        productName: String?,
        categoryName: String?,
        memo: String?,
        imagePath: String?,
        createdAt: Long,
        isFavorite: Boolean
    )
    suspend fun updateCosmetic(
        id: Long,
        brandName: String?,
        productName: String?,
        categoryName: String?,
        memo: String?,
        imagePath: String?,
        isFavorite: Boolean
    )
    suspend fun deleteCosmetic(id: Long)
}