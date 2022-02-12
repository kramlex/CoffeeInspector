package ru.kramlex.coffeeinspector.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import ru.kramlex.coffeeinspector.features.profile.model.ProfileRepository
import ru.kramlex.coffeeinspector.features.profile.model.UserModel
import ru.kramlex.coffeeinspector.storage.KeyValueStorage

internal class UserRepository(
    private val keyValueStorage: KeyValueStorage,
    private val coroutineScope: CoroutineScope
) : ProfileRepository {
    override fun getUserModel(): StateFlow<UserModel?> {
        return keyValueStorage.user.stateFlow
    }

    override fun authorize(userName: String) {
        keyValueStorage.user.value = UserModel(
            userName = userName,
            coffeeConsumedAmount = 0
        )
    }

    override fun logout() {
        keyValueStorage.user.value = null
    }
}