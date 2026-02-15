package com.example.bookapplication.data.remote.api

import com.example.bookapplication.BuildConfig
import com.example.bookapplication.data.remote.models.MovieDto
import com.example.bookapplication.utils.BaseUrl.MOVIE_ENDPOINT
import com.example.bookapplication.utils.BaseUrl.TRENDING_MOVIE_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET(MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMovies(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false,
    ): MovieDto

    @GET(TRENDING_MOVIE_ENDPOINT)
    suspend fun fetchTrendingMovies(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false,
    ): MovieDto

}