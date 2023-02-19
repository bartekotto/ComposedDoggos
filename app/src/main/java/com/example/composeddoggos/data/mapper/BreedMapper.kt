package com.example.composeddoggos.data.mapper

import com.example.composeddoggos.data.local.BreedEntity
import com.example.composeddoggos.data.local.BreedsList
import com.example.composeddoggos.domain.model.Breed

fun BreedEntity.toBreed(): Breed {
    return Breed(
        name = name,
        subBreeds = subBreeds.split(",")
    )
}

fun Breed.toBreedEntity(): BreedEntity {
    return BreedEntity(
        name = name,
        subBreeds = subBreeds.toString()
    )
}

fun BreedsList.toBreedArrayList(): ArrayList<Breed> {
    val breeds: ArrayList<Breed> = ArrayList()
    message.forEach { (key, value) ->
        breeds.add(
            Breed(
                key,
                if (value.equals("[]")) emptyList() else value
            )
        )
    }
    return breeds
}