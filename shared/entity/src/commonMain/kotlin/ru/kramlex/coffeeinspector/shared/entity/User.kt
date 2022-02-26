package ru.kramlex.coffeeinspector.shared.entity

import kotlinx.datetime.LocalDateTime


data class User(
    val id: Int,
    val name: String,
    val createdAt: LocalDateTime
)
