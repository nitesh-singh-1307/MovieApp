package com.example.bookapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapplication.domain.models.Movie
import com.example.bookapplication.domain.repository.MovieRepository
import com.example.bookapplication.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {

        fetchDiscoverMovie()
    }

    init {
        fetchTrendingMovie()
    }

    private fun fetchDiscoverMovie() = viewModelScope.launch {
        repository.fetchDiscoverMovie().collectAndHandle(
            onError = { error ->
                _state.update {
                    it.copy(isLoading = false, errors = error?.message)
                }
            },
            onLoading = {
                _state.update {
                    it.copy(isLoading = true, errors = null)
                }
            },
        ) { movies ->
            _state.update {
                it.copy(
                    isLoading = false,
                    errors = null,
                    discoverMovies = movies
                )
            }
        }
    }

    private fun fetchTrendingMovie() = viewModelScope.launch {
        repository.fetchTrendingMovie().collectAndHandle(
            onError = { error ->
                _state.update {
                    it.copy(isLoading = false, errors = error?.message)
                }
            },
            onLoading = {
                _state.update {
                    it.copy(isLoading = true, errors = null)
                }
            },
        ) { movies ->
            _state.update {
                it.copy(
                    isLoading = false,
                    errors = null,
                    trendingMovies = movies
                )
            }
        }
    }
}

data class HomeState(
    val discoverMovies: List<Movie> = emptyList(),
    val trendingMovies: List<Movie> = emptyList(),
    val errors: String? = null,
    val isLoading: Boolean = false
)


