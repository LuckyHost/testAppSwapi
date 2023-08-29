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


    suspend fun <T> loadData(
        callApi: suspend () -> ApiResponse<T>,
        daoInsert: suspend (T) -> Unit,
    )
    {
        callApi()
            .suspendOnSuccess { daoInsert(data) }
            .suspendOnException { Log.d("MyLog", "Repository.kt. getDataAllPeople: $message") }
    }

    suspend fun getDataAllPeopleNet() {
        loadData(
            callApi = { apiService.getDataAllPeople() },
            daoInsert = { daoPeople.insert(it.results) }
        )
 }

    suspend fun getDataAllPlanetNet() {
        loadData(
            callApi = { apiService.getDataPlanet() },
            daoInsert = { daoPlanet.insert(it.results) }
        )
    }

    suspend fun getDataAllStartShipNet() {
        loadData(
            callApi = { apiService.getDataStartship() },
            daoInsert = { daoStartShip.insert(it.results) }
        )
       }

    suspend fun getDataAllFilm() {
        loadData(
            callApi = { apiService.getDataFilm() },
            daoInsert = { daoFilm.insert(it.results) }
        )
    }

}