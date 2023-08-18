package com.example.testapp.domian.Room.DataClass.Films

data class AllFilms(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<ResultFilm>
)