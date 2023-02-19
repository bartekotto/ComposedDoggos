package com.example.composeddoggos.presentation.doggo

import com.example.composeddoggos.domain.model.DoggoURLs

data class DoggoPhotosState(
    val doggoURLs: DoggoURLs? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val favorites: List<String>? = null
) {
}
