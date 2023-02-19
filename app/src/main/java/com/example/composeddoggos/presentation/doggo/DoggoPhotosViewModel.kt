package com.example.composeddoggos.presentation.doggo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeddoggos.domain.repository.BreedRepository
import com.example.composeddoggos.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoggoPhotosViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BreedRepository
) : ViewModel() {
    var state by mutableStateOf(DoggoPhotosState())

    init {
        viewModelScope.launch {
            val breed = savedStateHandle.get<String>("name") ?: return@launch
            state = state.copy(isLoading = true)
            val doggoURLsResult = async { repository.getDoggoURLs(breed)}
            when (val result = doggoURLsResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        doggoURLs = result.data,
                        isLoading = false,
                        error = null

                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        doggoURLs = null
                    )
                }
                else -> Unit
            }
        }
    }

}