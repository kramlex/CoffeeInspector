package ru.kramlex.coffeeinspector.shared.entity

import kotlinx.datetime.LocalDateTime

data class Coffee(
    val id: Int ,
    val name: String?,
    val createdAt: LocalDateTime,
    val amount: Long,
    val rating: UByte?,
    val userId: Int,
    val syrups: List<Syrup>
)
