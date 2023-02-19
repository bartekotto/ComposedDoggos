package com.example.composeddoggos.di

import com.example.composeddoggos.data.repository.BreedRepositoryImpl
import com.example.composeddoggos.domain.repository.BreedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: BreedRepositoryImpl
    ): BreedRepository
}