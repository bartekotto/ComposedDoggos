package com.example.composeddoggos.data.remote

import com.squareup.moshi.Json

data class DoggoURLsDto(
    @field:Json(name = "message") val UrlListString: List<String>
) {
}