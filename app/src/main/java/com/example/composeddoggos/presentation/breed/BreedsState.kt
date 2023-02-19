package com.example.composeddoggos.presentation.breed

import com.example.composeddoggos.domain.model.Breed
import com.example.composeddoggos.util.Resource

data class BreedsState(
    val breeds: List<Breed> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
