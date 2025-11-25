package com.app.cosme.shared.usecase

import com.app.cosme.shared.repository.CosmeticRepository
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class RegisterCosmeticUseCase(
    private val cosmeticRepository: CosmeticRepository
) {
    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(
        brandName: String?,
        productName: String?,
        categoryName: String?,
        memo: String?,
        imagePath: String?
    ) {
        // TODO: createdAtはDIコンテナなどで注入するClockクラスなどから取得するのが望ましい
        cosmeticRepository.insertCosmetic(
            brandName = brandName,
            productName = productName,
            categoryName = categoryName,
            memo = memo,
            imagePath = imagePath,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            isFavorite = false
        )
    }
}