package ru.kramlex.coffeeinspector.features.profile

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import ru.kramlex.coffeeinspector.features.profile.model.ProfileModel
import ru.kramlex.coffeeinspector.features.profile.model.ProfileRepository

class ProfileUseCase(
    private val coroutineScope: CoroutineScope,
    private val profileRepository: ProfileRepository
) {

    val observeProfileModel: StateFlow<ProfileModel> =
        profileRepository.getUserModel().mapLatest { userModel ->
            if (userModel != null) {
                ProfileModel.Authorized(userModel, ::logout)
            } else {
                ProfileModel.Unauthorized(::authorize)
            }
        }.stateIn(coroutineScope, SharingStarted.Lazily, ProfileModel.Unauthorized(::authorize))


    private fun authorize(name: String) {
        print(" *** Authorize: (userName = $name)")
        profileRepository.authorize(name)
    }

    private fun logout() {
        profileRepository.logout()
    }
}