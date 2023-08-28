package com.example.testapp.data.Room

import androidx.room.*
import androidx.room.Dao
import com.example.testapp.domian.Room.DataClass.*
import com.example.testapp.domian.Room.DataClass.Planets.*
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.*

@Dao
interface DaoPlanet:DaoMy {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPlanet( people: List<ResultPlanet>?)
    @Query("SELECT * FROM Planet")
    fun getAllPlanet(): Flow <List<ResultPlanet>?>
    @Update
    suspend fun updateFaforitePlanet(person: ResultPlanet)
    @Query("SELECT * FROM Planet WHERE name = :id")
    suspend fun getplanetById(id: String): ResultPlanet?

    @Query("SELECT * FROM Planet WHERE isFavorites = 1")
    fun getFavoritePeople(): Flow <List<ResultPlanet>>



}