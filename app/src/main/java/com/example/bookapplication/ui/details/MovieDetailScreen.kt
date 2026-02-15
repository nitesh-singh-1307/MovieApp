package com.example.bookapplication.ui.details

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
    Box(modifier = modifier.fillMaxWidth()) {
        AnimatedVisibility(
            state.error != "",
            modifier = modifier.align(Alignment.TopCenter)
        ) {
            Text(
                state.error,
                color = MaterialTheme.colorScheme.error,
                maxLines = 2
            )
        }

        AnimatedVisibility(visible = !state.isLoading && state.error == null) {
            BoxWithConstraints(
                modifier = modifier.fillMaxWidth()
            ) {
                val boxHeight = this.maxHeight
                val topItemHeight = boxHeight * 0.6f
                val bodyItemHeight = boxHeight * 0.4f
                state.movieDetail?.let { movieDetail ->
                    DetailTopContent(
                        movieDetail = movieDetail,
                        modifier = Modifier
                            .height(topItemHeight)
                            .align(Alignment.TopCenter)
                    )
                    DetailBodyContent(
                        movieDetail = movieDetail,
                        movie = state.movies,
                        isMovieLoading = state.isLoading,
                        fetchMovies = movieDetailViewModel::fetchMovie,
                        onMovieClick = onMovieClick,
                        onActorClick = onActorClick,
                        modifier = Modifier
                            .height(bodyItemHeight)
                            .align(Alignment.BottomCenter)
                    )
                }

            }
        }

        IconButton(onClick = onNavigateUp, modifier = Modifier.align(Alignment.TopStart)) {

            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
        }
    }
    LoadingView(isLoading = state.isLoading)

}