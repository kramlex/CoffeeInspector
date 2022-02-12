package ru.kramlex.coffeeinspector.features.profile.model

sealed class ProfileModel (

) {
    data class Unauthorized(
        val authorize: (String) -> Unit
    ): ProfileModel()

    data class Authorized(
        val userModel: UserModel,
        val logout: () -> Unit
    ): ProfileModel()
}