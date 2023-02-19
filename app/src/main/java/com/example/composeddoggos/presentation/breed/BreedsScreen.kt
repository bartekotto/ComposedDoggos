package com.example.composeddoggos.presentation.breed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun BreedsScreen(
    navigator: DestinationsNavigator,
    viewModel: BreedsViewModel = hiltViewModel(),

    ) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = state.searchQuery, onValueChange = {
                viewModel.onEvent(BreedsEvent.OnSearchQueryChange(it))
            }, modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            }, maxLines = 1,
            singleLine = true
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(BreedsEvent.Refresh) }) {
        }
        LazyVerticalGrid(modifier = Modifier.fillMaxWidth(), columns = GridCells.Adaptive(256.dp)) {
            items(state.breeds.size) { i ->
                val breed = state.breeds[i]
                BreedItem(breed = breed, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), navigator
                )
                if (i < state.breeds.size) {
                    Divider(
                        modifier = Modifier.padding(
                            horizontal = 16.dp
                        )
                    )
                }
            }

        }

    }
}