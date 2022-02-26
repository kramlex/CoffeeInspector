package ru.kramlex.coffeeinspector.shared.storage

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getIntFlow
import com.russhwolf.settings.coroutines.getIntOrNullFlow
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import ru.kramlex.coffeeinspector.shared.features.profile.model.UserModel

@OptIn(ExperimentalSettingsApi::class,
    ExperimentalSerializationApi::class)
internal class KeyValueStorage(
    settings: ObservableSettings,
) {
    val user: DecodedItem<UserModel> = DecodedItem("user", settings, UserModel.serializer())

    val activeUserId: Flow<Int?> = settings.getIntOrNullFlow("activeUserId")

    class DecodedItem<T> internal constructor(
        private val key: String,
        private val settings: ObservableSettings,
        private val serializer: KSerializer<T>
    ) {
        var value: T?
            get() {
                return settings.decodeValueOrNull(key = key, serializer = serializer)
            }
            set(newValue) {
                _mutableStateFlow.value = newValue

                if (newValue == null) {
                    settings[key] = null
                    return
                }
                settings.encodeValue(serializer, key, newValue)
            }

        private val _mutableStateFlow: MutableStateFlow<T?> = MutableStateFlow(value)
        val stateFlow: StateFlow<T?> = _mutableStateFlow.asStateFlow()
    }
}