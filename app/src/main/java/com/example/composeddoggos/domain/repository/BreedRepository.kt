package com.example.composeddoggos.domain.repository

import com.example.composeddoggos.domain.model.Breed
import com.example.composeddoggos.domain.model.DoggoURLs
import com.example.composeddoggos.util.Resource
import kotlinx.coroutines.flow.Flow

interface BreedRepository {
    suspend fun getBreeds(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Breed>>>

    suspend fun getDoggoURLs(
        breed: String
    ): Resource<DoggoURLs>
}