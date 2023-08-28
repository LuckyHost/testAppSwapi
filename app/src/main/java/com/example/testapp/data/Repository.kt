package com.example.testapp.data

import android.util.*
import com.example.testapp.data.NetWork.*
import com.example.testapp.data.Room.*
import com.skydoves.sandwich.*
import javax.inject.*


class Repository @Inject constructor(
    private val apiService: ApiService,
    private val daoPeople: DaoPeople,
    private val daoPlanet: DaoPlanet,
    private val daoStartShip: DaoStartShip,
    private val daoFilm: DaoFilm,
) {


    suspend fun getDataAllPeopleNet() {
        apiService.getDataAllPeople()
            .suspendOnSuccess { daoPeople.insert(data.results) }
            .suspendOnException { Log.d("MyLog", "Repository.kt. getDataAllPeople: $message") }
    }

    suspend fun getDataAllPlanetNet() {
        apiService.getDataPlanet()
            .suspendOnSuccess { daoPlanet.insert(data.results) }
            .suspendOnException { Log.d("MyLog", "Repository.kt. getDataAllPlanet: $message") }
    }

    suspend fun getDataAllStartShipnNet() {
        apiService.getDataStartship()
            .suspendOnSuccess { daoStartShip.insert(data.results) }
            .suspendOnException { Log.d("MyLog", "Repository.kt. getDataAllStartShip: $message") }
    }

    suspend fun getDataAllFilm() {
        apiService.getDataFilm()
            .suspendOnSuccess { daoFilm.insert(data.results) }
            .suspendOnException { Log.d("MyLog", "Repository.kt. getDataAllFilm: $message") }
    }

}