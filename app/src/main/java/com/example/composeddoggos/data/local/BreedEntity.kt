package com.example.composeddoggos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreedEntity(
    val name: String,
    val subBreeds: String,
    @PrimaryKey val id: Int? = null
)