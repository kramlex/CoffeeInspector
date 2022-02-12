package ru.kramlex.coffeeinspector.features.profile.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val userName: String,
    val coffeeConsumedAmount: Int
)
