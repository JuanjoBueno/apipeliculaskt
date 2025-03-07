package com.example.tmdb.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.tmdb.data.model.Result
import com.example.tmdb.ui.viewmodel.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowPlayingScreen(viewModel: MoviesViewModel = viewModel()) {
    val movies by viewModel.movies.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Películas en Cartelera") })
        }
    ) { paddingValues ->
        MovieListScreen(movies = movies, Modifier.padding(paddingValues))
    }
}

@Composable
fun MovieListScreen(movies: List<Result>, modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie, isPortrait)
            }
        }
    } else {
        LazyRow(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie, isPortrait)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Result, isPortrait: Boolean) {
    val posterUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .then(if (!isPortrait) Modifier.width(250.dp) else Modifier)
            .graphicsLayer(rotationZ = if (!isPortrait) 90f else 0f),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(rotationZ = if (!isPortrait) -90f else 0f)
        ) {
            MovieImage(posterUrl, Modifier.fillMaxWidth().height(280.dp))
            MovieTitle(movie.title)
        }
    }
}

@Composable
fun MovieImage(url: String, modifier: Modifier) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = "Carátula de la película",
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
    )
}

@Composable
fun MovieTitle(title: String, modifier: Modifier = Modifier.padding(8.dp)) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodySmall,
        fontSize = 14.sp,
        modifier = modifier
    )
}

