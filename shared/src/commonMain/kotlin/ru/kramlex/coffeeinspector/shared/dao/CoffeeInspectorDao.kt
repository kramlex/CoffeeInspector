package ru.kramlex.coffeeinspector.shared.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDateTime
import ru.kramlex.coffeeinspector.db.generated.CoffeeInspectorDatabase
import ru.kramlex.coffeeinspector.shared.dao.mappers.toFeature
import ru.kramlex.coffeeinspector.shared.db.generated.UserTableQueries
import ru.kramlex.coffeeinspector.shared.entity.Coffee
import ru.kramlex.coffeeinspector.shared.entity.Syrup
import ru.kramlex.coffeeinspector.shared.entity.User

internal class CoffeeInspectorDao(database: CoffeeInspectorDatabase) {
    private val usersQueries: UserTableQueries = database.userTableQueries
    private val coffeeQueries = database.coffeeTableQueries
    private var syrupQueries = database.syrupTableQueries

    fun getAllUsers(): Flow<List<User>> =
        usersQueries.getAllUsers()
            .asFlow()
            .mapToList()
            .mapLatest { list ->
                list.map { it.toFeature() }
            }

    fun createUser(name: String, createdAt: LocalDateTime) =
        usersQueries.addUser(
            name = name,
            createdAt = createdAt
        )

    fun getAllSyrup(): Flow<List<Syrup>> =
        syrupQueries.getAllSyrup()
            .asFlow()
            .mapToList()
            .mapLatest { list -> list.map {it.toFeature()} }



    fun getUserCoffee(userId: Int): Flow<List<Coffee>> =
        combine(
            getAllSyrup(),
            coffeeQueries.getCoffeeByUserId(userId)
                .asFlow()
                .mapToList()
        ) { allSyrup, userCoffees ->
            userCoffees.map { coffee ->
                val coffeeSyrup = allSyrup.filter { syrup ->
                    val syrupIdList = coffee.syrups ?: return@filter false
                    syrupIdList.contains(syrup.id)
                }
                coffee.toFeature(coffeeSyrup)
            }
        }

    fun setCoffeeRatingById(coffeeId: Int, rating: Int) =
        coffeeQueries.setCoffeeRatingById(
            id = coffeeId,
            newRating = rating
        )



}