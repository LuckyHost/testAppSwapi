package com.example.testapp.domian.Room

import androidx.room.*
import com.example.testapp.data.Room.*
import com.example.testapp.domian.Room.DataClass.*
import com.example.testapp.domian.Room.DataClass.Films.*
import com.example.testapp.domian.Room.DataClass.Peoples.*
import com.example.testapp.domian.Room.DataClass.Planets.*
import com.example.testapp.domian.Room.DataClass.Starships.*

@Database([ResultPeople::class,ResultStarShip::class,ResultPlanet::class, ResultFilm::class], version = 1)
@TypeConverters(Converter::class)
abstract class BaseBD: RoomDatabase(){

    abstract fun daoPeople():DaoPeople
    abstract fun daoPlanet():DaoPlanet
    abstract fun daoStartShip():DaoStartShip
    abstract fun daoFilm():DaoFilm

}