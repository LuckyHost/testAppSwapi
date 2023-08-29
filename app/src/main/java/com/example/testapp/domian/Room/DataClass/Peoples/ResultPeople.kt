package com.example.testapp.domian.Room.DataClass.Peoples

import androidx.room.*
import com.example.testapp.domian.Room.DataClass.*

@Entity("People")
data class ResultPeople(
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    @PrimaryKey
     val name: String,
    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>,
     var isFavorites: Boolean = false,
)