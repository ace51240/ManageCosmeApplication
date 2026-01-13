package com.app.cosme.shared.repository

import Cosmetic
import CosmeticDao
import kotlinx.coroutines.flow.Flow

class CosmeticRepositoryImpl(private val cosmeticDao: CosmeticDao) : CosmeticRepository {
    override fun getAllCosmetics(): Flow<List<Cosmetic>> = cosmeticDao.getAllCosmetics()

    override fun getCosmeticById(id: Long): Flow<Cosmetic> = cosmeticDao.getCosmeticById(id)

    override suspend fun insertCosmetic(cosmetic: Cosmetic) = cosmeticDao.insertCosmetic(cosmetic)

    override suspend fun updateCosmetic(cosmetic: Cosmetic) = cosmeticDao.updateCosmetic(cosmetic)

    override suspend fun deleteCosmetic(cosmetic: Cosmetic) = cosmeticDao.deleteCosmetic(cosmetic)
}