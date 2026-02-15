package com.example.bookapplication.domain.repository

import com.example.bookapplication.domain.models.Movie
import com.example.bookapplication.utils.Response
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun  fetchDiscoverMovie(): Flow<Response<List<Movie>>>
    fun  fetchTrendingMovie(): Flow<Response<List<Movie>>>
}