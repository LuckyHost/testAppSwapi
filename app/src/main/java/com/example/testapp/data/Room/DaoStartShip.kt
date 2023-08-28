package com.example.testapp.data.Room

import androidx.room.*
import androidx.room.Dao
import com.example.testapp.domian.Room.DataClass.Starships.ResultStarShip
import com.example.testapp.domian.Room.DataClass.*
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.*

@Dao
interface DaoStartShip:DaoMy {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(people: List<ResultStarShip>)
    @Query("SELECT * FROM Startship")
    fun getAll(): Flow <List<ResultStarShip>>?
    @Update
    suspend fun updateFaforiteStartships(person: ResultStarShip)


    @Query("SELECT * FROM Startship WHERE isFavorites = 1")
    fun getFavoritePeople(): Flow <List<ResultStarShip>>

    @Query("SELECT * FROM Startship WHERE name = :id")
    suspend fun getById(id: String): ResultStarShip?



}