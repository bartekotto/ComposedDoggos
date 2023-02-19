package com.example.composeddoggos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreeds(
        breedEntities: List<BreedEntity>
    )

    @Query("DELETE FROM breedEntity")
    suspend fun clearBreeds()

    @Query("""SELECT * FROM breedEntity WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' 
        """)
//    OR UPPER(:query) == subBreeds

    suspend fun searchBreed(query: String): List<BreedEntity>
}
