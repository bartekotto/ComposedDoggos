package com.example.composeddoggos.di

import android.app.Application
import androidx.room.Room
import com.example.composeddoggos.data.local.BreedDatabase
import com.example.composeddoggos.data.remote.DoggosAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBreedApi(): DoggosAPI {
        return Retrofit.Builder().baseUrl(DoggosAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build().create()
    }

    @Provides
    @Singleton
    fun provideBreedDatabase(app: Application): BreedDatabase{
        return Room.databaseBuilder(
            app,
            BreedDatabase::class.java,
            "breeddb.db"
        ).build()
    }
}