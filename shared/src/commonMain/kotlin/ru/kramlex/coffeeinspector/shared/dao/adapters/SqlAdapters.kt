package ru.kramlex.coffeeinspector.shared.dao.adapters

import com.squareup.sqldelight.ColumnAdapter
import kotlinx.datetime.LocalDateTime

val listOfIntegerAdapter = object: ColumnAdapter<List<Int>, String> {
    override fun decode(databaseValue: String): List<Int> =
        if (databaseValue.isEmpty()) emptyList()
        else databaseValue.split(",").map{ it.toInt() }

    override fun encode(value: List<Int>): String =
        value.joinToString(",")
}

val localDateTimeAdapter = object : ColumnAdapter<LocalDateTime, String> {
    override fun decode(databaseValue: String): LocalDateTime =
        LocalDateTime.parse(databaseValue)

    override fun encode(value: LocalDateTime): String =
        LocalDateTime.toString()
}