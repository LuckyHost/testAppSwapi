package com.example.testapp.domian.Room.DataClass.Films

import androidx.room.*
@Entity("Film")
data class ResultFilm(
    @PrimaryKey
    val title: String,
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    val episode_id: Int,
    val opening_crawl: String,
    val planets: List<String>,
    val producer: String,
    val release_date: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>,
)