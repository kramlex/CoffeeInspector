package ru.kramlex.coffeeinspector.shared

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import ru.kramlex.coffeeinspector.db.generated.CoffeeInspectorDatabase

actual class SqlDatabaseDriverFactory(
    private val context: Context
): DatabaseDriverFactory {
    override fun createMerchantsDatabaseDriver(): SqlDriver =
        AndroidSqliteDriver(
            context = context,
            schema = CoffeeInspectorDatabase.Schema,
            name = COFFEE_INSPECTOR_DATA_BASE_NAME
        )
}