package com.example.bookapplication.movie_detail.domain.repository

import com.example.bookapplication.domain.models.Movie
import com.example.bookapplication.movie_detail.domain.models.MovieDetail
import com.example.bookapplication.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>>
    fun fetchMovie(movieId: Int): Flow<Response<List<Movie>>>
}



