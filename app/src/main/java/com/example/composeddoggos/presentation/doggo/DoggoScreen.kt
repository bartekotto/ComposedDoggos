package com.example.composeddoggos.presentation.doggo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun DoggoScreen(
    name: String,
    viewModel: DoggoPhotosViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    if (state.doggoURLs?.message?.isNotEmpty() == true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn() {
                items(state.doggoURLs!!.message) { photoURL ->
                    DoggoPhotoItem(
                        photoURL = photoURL,
                        onClick = {

                        },
                        isFavourite = state.favorites?.contains(photoURL) == true
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error != null) {
            Text(
                text = state.error,
                color = Color.Red
            )
        }
    }
}