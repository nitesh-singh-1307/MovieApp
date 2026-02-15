package com.example.bookapplication.di

import com.example.bookapplication.common.data.ApiMapper
import com.example.bookapplication.data.remote.api.MovieApiService
import com.example.bookapplication.data.remote.models.MovieDto
import com.example.bookapplication.domain.models.Movie
import com.example.bookapplication.movie_detail.data.mapper_impl.MovieDetailMapperImpl
import com.example.bookapplication.movie_detail.data.remote.api.MovieDetailApiService
import com.example.bookapplication.movie_detail.data.remote.models.MovieDetailDto
import com.example.bookapplication.movie_detail.data.repo_impl.MovieDetailRepositoryImpl
import com.example.bookapplication.movie_detail.domain.models.MovieDetail
import com.example.bookapplication.movie_detail.domain.repository.MovieDetailRepository
import com.example.bookapplication.utils.BaseUrl.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailMode {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideMovieDetailRepository(
        movieDetailApiService: MovieDetailApiService,
        apiMapper: ApiMapper<MovieDetail, MovieDetailDto>,
        movieMapper: ApiMapper<List<Movie>, MovieDto>
    ): MovieDetailRepository = MovieDetailRepositoryImpl(
        movieDetailsApiService = movieDetailApiService,
        mapperDetail = apiMapper,
        mapperMovie = movieMapper
    )

    @Provides
    @Singleton
    fun provideMovieDetailMapper(): ApiMapper<MovieDetail, MovieDetailDto> = MovieDetailMapperImpl()

    @Provides
    @Singleton
    fun provideMovieDetailsApiService(): MovieDetailApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieDetailApiService::class.java)
    }


}