package com.example.testapp.domian.Room.DataClass.Starships

import androidx.room.*
@Entity("Startship")
data class ResultStarShip(
    val MGLT: String,
    val cargo_capacity: String,
    val consumables: String,
    val cost_in_credits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    val hyperdrive_rating: String,
    val length: String,
    val manufacturer: String,
    val max_atmosphering_speed: String,
    val model: String,
    @PrimaryKey
    val name: String,
    val passengers: String,
    val pilots: List<String>,
    val starship_class: String,
    val url: String,
    var isFavorites: Boolean = false,
)