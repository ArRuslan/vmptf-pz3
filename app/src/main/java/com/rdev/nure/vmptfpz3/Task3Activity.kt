package com.rdev.nure.vmptfpz3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.rdev.nure.vmptfpz3.ui.theme.VMPtFPz3Theme

class Task3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val movies = listOf(
            MovieEntity("Movie 1", "idk1", 1.0f),
            MovieEntity("Movie 2", "idk2", 3.0f),
            MovieEntity("Movie 3", "idk3", 4.5f),
            MovieEntity("Movie 4", "idk1", 1.0f),
            MovieEntity("Movie 5", "idk2", 3.0f),
            MovieEntity("Movie 6", "idk3", 4.5f),
            MovieEntity("Movie 7", "idk1", 1.0f),
            MovieEntity("Movie 8", "idk2", 3.0f),
            MovieEntity("Movie 9", "idk3", 4.5f),
            MovieEntity("Movie 10", "idk1", 1.0f),
            MovieEntity("Movie 11", "idk2", 3.0f),
            MovieEntity("Movie 12", "idk3", 4.5f),
            MovieEntity("Movie 13", "idk1", 1.0f),
            MovieEntity("Movie 14", "idk2", 3.0f),
            MovieEntity("Movie 15", "idk3", 4.5f),
        )

        setContent {
            VMPtFPz3Theme {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(
                        1,
                        contentType = { MovieEntity::class },
                        key = { it.hashCode() },
                    ) {
                        for (movie in movies) {
                            Movie(name = movie.name, genre = movie.genre, rating = movie.rating)
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

data class MovieEntity(val name: String, val genre: String, val rating: Float)

@Composable
fun Movie(name: String, genre: String, rating: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
    ) {
        Text(
            text = name,
            fontSize = 3.em,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = genre,
                fontSize = 2.5.em,
            )
            Text(
                text = "$rating/5",
                fontSize = 2.5.em,
                color = when {
                    rating <= 2 -> Color.Red
                    rating <= 4 -> Color(0xFFFF6E00)
                    else -> Color.Green
                }
            )
        }
    }
}

@Preview
@Composable
fun MoviesPreview() {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Movie("test", "idk", 1.0f)
        Movie("test", "idk", 3.0f)
        Movie("test", "idk", 4.5f)
    }
}