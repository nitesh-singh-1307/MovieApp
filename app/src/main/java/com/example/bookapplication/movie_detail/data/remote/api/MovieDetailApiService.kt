package com.example.bookapplication.movie_detail.data.remote.api

import com.example.bookapplication.BuildConfig
import com.example.bookapplication.data.remote.models.MovieDto
import com.example.bookapplication.movie_detail.data.remote.models.MovieDetailDto
import com.example.bookapplication.utils.BaseUrl.MOVIE_DETAIL_ENDPOINT
import com.example.bookapplication.utils.BaseUrl.MOVIE_ENDPOINT
import com.example.bookapplication.utils.BaseUrl.MOVIE_ID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val MOVIE_IDS = "movie_id"

interface MovieDetailApiService {
    @GET("${MOVIE_DETAIL_ENDPOINT}/{$MOVIE_IDS}")
    suspend fun fetchMovieDetail(
        @Path(MOVIE_IDS) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("append_to_response") appendToResponse: String = "credits,reviews"
    ): MovieDetailDto

    @GET(MOVIE_ENDPOINT)
    suspend fun fetchMovies(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false,
    ): MovieDto

}