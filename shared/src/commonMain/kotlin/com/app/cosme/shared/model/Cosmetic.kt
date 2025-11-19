package com.app.cosme.shared.model

import kotlinx.datetime.LocalDate

data class Cosmetic(
    val id: Long,
    val name: String?,
    val brand: String?,
    val category: String?,
    val imagePath: String?,
    val memo: String?,
    val favorite: Boolean,
    val createdAt: LocalDate
)