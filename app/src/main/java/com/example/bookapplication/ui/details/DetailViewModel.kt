package com.example.bookapplication.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapplication.domain.models.Movie
import com.example.bookapplication.movie_detail.domain.models.MovieDetail
import com.example.bookapplication.movie_detail.domain.repository.MovieDetailRepository
import com.example.bookapplication.utils.BaseUrl.MOVIE_ID
import com.example.bookapplication.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val error: String = "",
    val movies: List<Movie> = emptyList(),
    val isMovieLoading: Boolean = false
)


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieDetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    val movieId:Int = savedStateHandle.get<Int>(MOVIE_ID) ?: -1

    init {
        fetchDetailsById()
    }

    private fun fetchDetailsById() = viewModelScope.launch {

        if (movieId == -1) {
            _state.update { it.copy(isLoading = false, error = "Movie not found") }
        } else {
            repository.fetchMovieDetail(movieId).collectAndHandle(
                onError = { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error?.message ?: "Failed to load movie details"
                        )
                    }
                },
                onLoading = {
                    _state.update {
                        it.copy(
                            isLoading = true,
                            error = ""
                        )

                    }
                }
            ) { movieDetail ->
                Log.d("DetailViewModel:::", "fetchDetailsById: $movieDetail")
                _state.update {
                    it.copy(
                        isLoading = false,
                        isMovieLoading = false,
                        movieDetail = movieDetail,
                        error = ""
                    )
                }
            }
        }

    }
    public fun fetchMovie() = viewModelScope.launch {
        repository.fetchMovie(movieId).collectAndHandle(
            onError = { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "${error?.message}"
                    )

                }
            },
            onLoading = {
                _state.update {
                    it.copy(
                        isMovieLoading = true,
                        error = ""
                    )

                }
            }
        ) { movieDetail ->
            _state.update {
                it.copy(
                    isLoading = false,
                    isMovieLoading = false,
                    movies = movieDetail,
                    error = ""
                )

            }
        }

    }
}
