package com.example.composeddoggos.presentation.breed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeddoggos.domain.model.Breed
import com.example.composeddoggos.presentation.destinations.DoggoScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun BreedItem(
    breed: Breed,
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
) {
    var isPressed by remember { mutableStateOf(false) }

    Button(
        onClick = {
            if (breed.subBreeds.isNotEmpty() && breed.subBreeds[0] != "[]") {
                isPressed = !isPressed
            } else {
                navigator.navigate(
                    DoggoScreenDestination(breed.name)
                )
            }
        },
        modifier = modifier,
    ) {
        Text(text = breed.name.uppercase())
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                Row() {
                    Spacer(modifier = Modifier.size(10.dp))
                    Column() {
                        breed.subBreeds.forEach { message ->
                            Button({navigator.navigate(
                                DoggoScreenDestination(breed.name)
                            )}, border = BorderStroke(1.dp, Color.Black)) {
                                Text(text = message.uppercase().replace("[", "").replace("]", ""))
                            }
                        }
                    }
                }


            }
        }
    }
}