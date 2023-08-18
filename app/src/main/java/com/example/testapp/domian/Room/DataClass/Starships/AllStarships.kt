package com.example.testapp.domian.Room.DataClass.Starships

data class AllStarships(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<ResultStarShip>
)