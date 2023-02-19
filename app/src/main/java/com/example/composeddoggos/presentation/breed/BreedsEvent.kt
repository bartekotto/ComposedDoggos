package com.example.composeddoggos.presentation.breed

sealed class BreedsEvent{
    object Refresh: BreedsEvent()
    data class OnSearchQueryChange(val query: String): BreedsEvent()
}
