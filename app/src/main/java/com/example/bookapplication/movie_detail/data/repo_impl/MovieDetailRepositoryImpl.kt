package com.example.bookapplication.movie_detail.data.repo_impl

import android.util.Log
import com.example.bookapplication.common.data.ApiMapper
import com.example.bookapplication.data.remote.models.MovieDto
import com.example.bookapplication.domain.models.Movie
import com.example.bookapplication.movie_detail.data.remote.api.MovieDetailApiService
import com.example.bookapplication.movie_detail.data.remote.models.MovieDetailDto
import com.example.bookapplication.movie_detail.domain.models.MovieDetail
import com.example.bookapplication.movie_detail.domain.repository.MovieDetailRepository
import com.example.bookapplication.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieDetailRepositoryImpl(
    private val movieDetailsApiService: MovieDetailApiService,
    private val mapperDetail: ApiMapper<MovieDetail, MovieDetailDto>,
    private val mapperMovie: ApiMapper<List<Movie>, MovieDto>
) : MovieDetailRepository {
    override fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>> = flow {
        emit(Response.Loading())
        val response = movieDetailsApiService.fetchMovieDetail(movieId)
        Log.d("TAG", "fetchMovieDetailRepositoryImp: $response")
        mapperDetail.mapToDomain(response).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun fetchMovie(movieId: Int): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val response = movieDetailsApiService.fetchMovies()
        mapperMovie.mapToDomain(response).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

}