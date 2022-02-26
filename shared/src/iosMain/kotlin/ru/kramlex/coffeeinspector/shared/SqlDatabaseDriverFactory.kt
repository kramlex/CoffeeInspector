package ru.kramlex.coffeeinspector.shared

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import ru.kramlex.coffeeinspector.db.generated.CoffeeInspectorDatabase

actual class SqlDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createMerchantsDatabaseDriver(): SqlDriver =
        NativeSqliteDriver(
            CoffeeInspectorDatabase.Schema,
            COFFEE_INSPECTOR_DATA_BASE_NAME
        )
}