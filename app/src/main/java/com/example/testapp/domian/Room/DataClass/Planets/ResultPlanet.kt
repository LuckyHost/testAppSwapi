package com.example.testapp.domian.Room.DataClass.Planets

import androidx.room.*
import com.example.testapp.domian.Room.DataClass.*

@Entity("Planet")
data class ResultPlanet(

    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    @PrimaryKey
    override val name: String,
    val orbital_period: String,
    val population: String,
    val residents: List<String>,
    val rotation_period: String,
    val surface_water: String,
    val terrain: String,
    val url: String,
    override var isFavorites: Boolean = false,
):Favorite