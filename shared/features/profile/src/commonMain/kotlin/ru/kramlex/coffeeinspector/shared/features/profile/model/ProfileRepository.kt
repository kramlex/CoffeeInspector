package ru.kramlex.coffeeinspector.shared.features.profile.model

import kotlinx.coroutines.flow.StateFlow


interface ProfileRepository {
    fun getUserModel(): StateFlow<UserModel?>
    fun authorize(userName: String)
    fun logout()
}