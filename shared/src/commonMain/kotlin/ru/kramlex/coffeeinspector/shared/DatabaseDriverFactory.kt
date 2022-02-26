package ru.kramlex.coffeeinspector.shared

import com.squareup.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun createMerchantsDatabaseDriver(): SqlDriver
}