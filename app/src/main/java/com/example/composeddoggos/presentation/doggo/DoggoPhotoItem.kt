package com.example.composeddoggos.presentation.doggo

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun DoggoPhotoItem(
    photoURL: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isFavourite: Boolean
) {
    var isFavourite by remember { mutableStateOf(isFavourite) }
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model  = photoURL,
            contentDescription = photoURL,
            modifier = Modifier
//                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(10.dp, Color.White)

        )
        IconToggleButton(
            checked = isFavourite,
            onCheckedChange = {
                isFavourite = !isFavourite

            },
            modifier = Modifier.padding(30.dp).align(Alignment.TopStart)
        ) {
            Icon(
                tint = Color.Red,
                modifier = Modifier.size(100.dp),
                imageVector = if (isFavourite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.size(50.dp))
    }
}



@Preview
@Composable
fun WaterPreview() {
    DoggoPhotoItem(
        photoURL = "https://images.dog.ceo/breeds/pyrenees/beau1.jpg",
        onClick = { /*TODO*/ },
        isFavourite = false
    )
}