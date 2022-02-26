package ru.kramlex.coffeeinspector.shared.dao.mappers

import ru.kramlex.coffeeinspector.shared.db.generated.CoffeeTable
import ru.kramlex.coffeeinspector.shared.db.generated.SyrupTable
import ru.kramlex.coffeeinspector.shared.db.generated.UserTable
import ru.kramlex.coffeeinspector.shared.entity.Coffee
import ru.kramlex.coffeeinspector.shared.entity.Syrup
import ru.kramlex.coffeeinspector.shared.entity.User

internal fun UserTable.toFeature() =
    User(
        id = id,
        name = name,
        createdAt = createdAt
    )

internal fun CoffeeTable.toFeature(
    listOfSyrup: List<Syrup>
) =
    Coffee(
        id = id,
        amount = amount,
        name = name,
        createdAt = createdAt,
        rating = rating?.toUByte(),
        userId = userId,
        syrups = listOfSyrup
    )

internal fun SyrupTable.toFeature() =
    Syrup(
        id = id,
        name = name
    )