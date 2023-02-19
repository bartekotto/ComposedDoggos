package com.example.composeddoggos.presentation.breed

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeddoggos.domain.repository.BreedRepository
import com.example.composeddoggos.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val repository: BreedRepository
) : ViewModel() {
    var state by mutableStateOf(BreedsState())
    private var searchJob: Job? = null

    init {
        getBreeds("")
    }

    fun onEvent(event: BreedsEvent) {
        when (event) {
            is BreedsEvent.Refresh -> {
                getBreeds(fetchFromRemote = true)
            }
            is BreedsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch{
                    delay(500L)
                    getBreeds()
                }
            }
        }
    }

    private fun getBreeds(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getBreeds(fetchFromRemote, query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { breeds ->
                                state = state.copy(
                                    breeds = breeds
                                )
                            }

                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }

    }
}