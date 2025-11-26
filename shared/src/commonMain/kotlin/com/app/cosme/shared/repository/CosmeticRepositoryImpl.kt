package com.app.cosme.shared.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.app.cosme.shared.db.CosmeDatabase
import com.app.cosme.shared.db.Cosmetic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class CosmeticRepositoryImpl(private val database: CosmeDatabase) : CosmeticRepository {
    private val queries = database.cosmeDatabaseQueries

    override fun getAllCosmetics(): Flow<List<Cosmetic>> {
        return queries.selectAllCosmetics().asFlow().mapToList(Dispatchers.Default)
    }

    override suspend fun getCosmeticById(id: Long): Cosmetic? {
        return queries.selectCosmeticById(id).executeAsOneOrNull()
    }

    override suspend fun insertCosmetic(
        brandName: String?,
        productName: String?,
        categoryName: String?,
        memo: String?,
        imagePath: String?,
        createdAt: Long,
        isFavorite: Boolean
    ) {
        queries.insertCosmetic(
            id = null,
            brand_name = brandName,
            product_name = productName,
            category_name = categoryName,
            memo = memo,
            image_path = imagePath,
            created_at = createdAt,
            is_favorite = if (isFavorite) 1L else 0L
        )
    }

    override suspend fun updateCosmetic(
        id: Long,
        brandName: String?,
        productName: String?,
        categoryName: String?,
        memo: String?,
        imagePath: String?,
        isFavorite: Boolean
    ) {
        queries.updateCosmetic(
            id = id,
            brand_name = brandName,
            product_name = productName,
            category_name = categoryName,
            memo = memo,
            image_path = imagePath,
            is_favorite = if (isFavorite) 1L else 0L
        )
    }

    override suspend fun deleteCosmetic(id: Long) {
        queries.deleteCosmetic(id)
    }
}