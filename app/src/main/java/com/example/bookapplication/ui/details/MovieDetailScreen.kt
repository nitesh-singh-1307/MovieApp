package com.example.bookapplication.ui.details

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bookapplication.ui.components.LoadingView
import com.example.bookapplication.ui.details.components.DetailBodyContent
import com.example.bookapplication.ui.details.components.DetailTopContent

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movieDetailViewModel: DetailViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onActorClick: (Int) -> Unit
) {

    val state by movieDetailViewModel.state.collectAsStateWithLifecycle()
    Log.d("MovieDetailScreen", "state:::: ${state.movieDetail}")

    Box(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = state.error.isNotEmpty(),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 56.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                maxLines = 2
            )
        }

        state.movieDetail?.let { movieDetail ->
            AnimatedVisibility(visible = !state.isLoading && state.error.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    DetailTopContent(
                        movieDetail = movieDetail,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                    )
                    DetailBodyContent(
                        movieDetail = movieDetail,
                        movie = state.movies,
                        isMovieLoading = state.isMovieLoading,
                        fetchMovies = movieDetailViewModel::fetchMovie,
                        onMovieClick = onMovieClick,
                        onActorClick = onActorClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(top = 240.dp)
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = !state.isLoading && state.error.isEmpty() && state.movieDetail == null,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Movie details unavailable",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        IconButton(
            onClick = onNavigateUp,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
    }

    LoadingView(isLoading = state.isLoading)
}
