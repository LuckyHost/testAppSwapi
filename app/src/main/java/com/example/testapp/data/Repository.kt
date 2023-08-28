package com.example.testapp.data

import android.content.*
import android.util.*
import android.widget.*
import com.example.testapp.data.NetWork.*
import com.example.testapp.data.Room.*
import com.example.testapp.domian.Room.DataClass.Films.*
import com.example.testapp.domian.Room.DataClass.Peoples.*
import com.example.testapp.domian.Room.DataClass.Planets.*
import com.example.testapp.domian.Room.DataClass.Starships.*
import com.example.testapp.present.Screens.*
import com.skydoves.sandwich.*
import kotlinx.coroutines.flow.*
import javax.inject.*


class Repository @Inject constructor(
    private val apiService: ApiService,
    private val daoPeople: DaoPeople,
    private val daoPlanet: DaoPlanet,
    private val daoStartShip: DaoStartShip,
    private val daoFilm: DaoFilm,
) {


    suspend fun getDataAllPeopleNet() {
        var result = emptyList<ResultPeople>()
        val resonse = apiService.getDataAllPeople()
        resonse.suspendOnSuccess { daoPeople.insertPeople(data.results)  }
            .suspendOnException { Log.d("MyLog", "Repository.kt. getDataAllPeopleNet: $message") }
        }

    fun getDataAllPlanetNet(): Flow<AllPlanets> = flow {
        try {
            val resonse2 = apiService.getDataPlanet()

            if (resonse2.isSuccessful) {
                emit(resonse2.body()!!)
            }
        } catch (e: Throwable) {
            Log.d("MyLog", "Repository.kt. getDataAllPeopleNet: $e")

        }


    }

    fun getDataAllStartShipnNet(): Flow<AllStarships> = flow {
        try {
            val resonse3 = apiService.getDataStartship()

            if (resonse3.isSuccessful) {
                emit(resonse3.body()!!)
            }
        } catch (e: Throwable) {
            Log.d("MyLog", "Repository.kt. getDataAllPeopleNet: $e")

        }

    }

    fun getDataAllFilm(): Flow<AllFilms> = flow {

        try {
            val resonse4 = apiService.getDataFilm()
            if (resonse4.isSuccessful) {
                emit(resonse4.body()!!)
            }
        } catch (e: Throwable) {
            Log.d("MyLog", "Repository.kt. getDataAllPeopleNet: $e")

        }

    }


}