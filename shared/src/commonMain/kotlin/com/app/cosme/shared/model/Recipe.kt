package com.app.cosme.shared.model

import kotlinx.datetime.LocalDate

data class Recipe(
    val id: Long,
    val title: String,
    val cosmeticIds: List<String>,
    val memo: String,
    val createdAt: LocalDate
)