package ru.kramlex.coffeeinspector.shared

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.kramlex.coffeeinspector.db.generated.CoffeeInspectorDatabase
import ru.kramlex.coffeeinspector.shared.dao.CoffeeInspectorDao
import ru.kramlex.coffeeinspector.shared.dao.adapters.listOfIntegerAdapter
import ru.kramlex.coffeeinspector.shared.dao.adapters.localDateTimeAdapter
import ru.kramlex.coffeeinspector.shared.db.generated.CoffeeTable
import ru.kramlex.coffeeinspector.shared.db.generated.UserTable
import ru.kramlex.coffeeinspector.shared.features.profile.ProfileUseCase
import ru.kramlex.coffeeinspector.shared.repositories.UserRepository
import ru.kramlex.coffeeinspector.shared.storage.KeyValueStorage
import kotlin.native.concurrent.ThreadLocal

@OptIn(ExperimentalSettingsApi::class)
class SharedFactory(
    settings: ObservableSettings,
    databaseDriverFactory: DatabaseDriverFactory
) {
    private val coffeeInspectorDao = CoffeeInspectorDao(
        database = CoffeeInspectorDatabase(
            driver = databaseDriverFactory.createMerchantsDatabaseDriver(),
            CoffeeTableAdapter = CoffeeTable.Adapter(
                createdAtAdapter = localDateTimeAdapter,
                syrupsAdapter = listOfIntegerAdapter
            ),
            UserTableAdapter = UserTable.Adapter(
                createdAtAdapter = localDateTimeAdapter
            )
        )
    )

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