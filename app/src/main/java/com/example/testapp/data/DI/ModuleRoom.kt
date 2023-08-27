package com.example.testapp.data.DI

import android.content.*
import androidx.room.*
import com.example.testapp.data.Room.*
import com.example.testapp.domian.Room.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.qualifiers.*
import dagger.hilt.components.*
import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
object ModuleRoom {
    @Singleton
    @Provides
    fun provideMyDataBase(@ApplicationContext context: Context): BaseBD {
        return  Room.databaseBuilder (
            context =  context.applicationContext,
            klass = BaseBD::class.java,
            name = "BD.bd"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDaoPeople(myDataBase: BaseBD): DaoPeople {
        return myDataBase.daoPeople()
    }

    @Singleton
    @Provides
    fun provideDaoPlanet(myDataBase: BaseBD): DaoPlanet {
        return myDataBase.daoPlanet()
    }

    @Singleton
    @Provides
    fun provideDaoStartShip(myDataBase: BaseBD): DaoStartShip {
        return myDataBase.daoStartShip()
    }

    @Singleton
    @Provides
    fun providesDaoFilm(myDataBase: BaseBD):DaoFilm {
        return myDataBase.daoFilm()
    }

}