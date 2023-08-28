package com.example.testapp.data.Room

import androidx.room.*
import com.example.testapp.domian.Room.DataClass.*
import com.example.testapp.domian.Room.DataClass.Films.*
import kotlinx.coroutines.flow.*

@Dao
interface DaoFilm:DaoMy {
 //
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(people: List<ResultFilm>)
    //    @Query("SELECT * FROM people WHERE name LIKE (:personName) || '%'  ")
    @Query("SELECT * FROM Film ")
    fun getAll(): Flow<List<ResultFilm>>

    @Query("SELECT * FROM Film WHERE url = :filmId")
    suspend fun getById(filmId: String): ResultFilm?

}