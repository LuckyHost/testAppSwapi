package com.example.testapp.data.DI

import android.app.*
import android.content.*
import com.example.testapp.data.*
import com.example.testapp.data.NetWork.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleRepository {



    @Singleton
    @Provides
    fun providerrep(apiService: ApiService):Repository{
        return  Repository(apiService, )
    }


}