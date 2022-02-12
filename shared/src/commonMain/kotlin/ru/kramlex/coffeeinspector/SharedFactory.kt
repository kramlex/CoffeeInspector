package ru.kramlex.coffeeinspector

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.kramlex.coffeeinspector.features.profile.ProfileUseCase
import ru.kramlex.coffeeinspector.repositories.UserRepository
import ru.kramlex.coffeeinspector.storage.KeyValueStorage
import kotlin.native.concurrent.ThreadLocal

@OptIn(ExperimentalSettingsApi::class)
class SharedFactory(
    settings: ObservableSettings
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val keyValueStorage = KeyValueStorage(settings)


    private val userRepository: UserRepository by lazy {
        UserRepository(
            keyValueStorage = keyValueStorage,
            coroutineScope = coroutineScope
        )
    }

    val profileUseKeys: ProfileUseCase by lazy {
        ProfileUseCase(
            profileRepository = userRepository,
            coroutineScope = coroutineScope
        )
    }

    @ThreadLocal
    companion object {
        lateinit var instance: SharedFactory
    }
}