package com.example.composeddoggos.data.mapper

import com.example.composeddoggos.data.remote.DoggoURLsDto
import com.example.composeddoggos.domain.model.DoggoURLs

fun DoggoURLsDto.toDoggoURLs(): DoggoURLs{
    return DoggoURLs(UrlListString)
}