package com.example.bookapplication.data.repository_Imp

import com.example.bookapplication.common.data.ApiMapper
import com.example.bookapplication.data.remote.api.MovieApiService
import com.example.bookapplication.data.remote.models.MovieDto
import com.example.bookapplication.domain.models.Movie
import com.example.bookapplication.domain.repository.MovieRepository
import com.example.bookapplication.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val apiMapper: ApiMapper<List<Movie>, MovieDto>
): MovieRepository {

    override fun fetchDiscoverMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieApiService.fetchDiscoverMovies()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }

    }.catch { e ->
        emit(Response.Error(e))
    }

    override fun fetchTrendingMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieApiService.fetchTrendingMovies()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }

    }.catch { e ->
        emit(Response.Error(e))
    }
}