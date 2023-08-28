package com.example.testapp.data.NetWork

import com.example.testapp.domian.Room.DataClass.Films.*
import com.example.testapp.domian.Room.DataClass.Peoples.*
import com.example.testapp.domian.Room.DataClass.Planets.*
import com.example.testapp.domian.Room.DataClass.Starships.*
import com.skydoves.sandwich.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @GET("people")
    suspend fun getDataAllPeople():ApiResponse<AllPeoples>

    @GET("planets")
    suspend fun getDataPlanet():ApiResponse<AllPlanets>

    @GET("starships")
    suspend fun getDataStartship():ApiResponse<AllStarships>
    @GET("films")
    suspend fun getDataFilm():ApiResponse<AllFilms>
}