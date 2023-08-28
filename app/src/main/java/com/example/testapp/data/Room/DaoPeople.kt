package com.example.testapp.data.Room

import androidx.room.*
import androidx.room.Dao
import com.example.testapp.domian.Room.DataClass.*
import com.example.testapp.domian.Room.DataClass.Peoples.*
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.*

@Dao
interface DaoPeople:DaoMy {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPeople( people: List<ResultPeople>)
//    @Query("SELECT * FROM people WHERE name LIKE (:personName) || '%'  ")
    @Query("SELECT * FROM people ")
     fun getAllPeople(): Flow <List<ResultPeople>>
    @Update
    suspend fun updateForFavorite(person: ResultPeople)
    @Query("SELECT * FROM people WHERE name = :personName")
    suspend fun getPersonById(personName: String): ResultPeople?




}