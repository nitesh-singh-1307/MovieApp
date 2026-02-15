package com.example.bookapplication.di

import com.example.bookapplication.common.data.ApiMapper
import com.example.bookapplication.data.mapper_impl.MovieApiMapperImpl
import com.example.bookapplication.data.remote.api.MovieApiService
import com.example.bookapplication.data.remote.models.MovieDto
import com.example.bookapplication.data.repository_Imp.MovieRepositoryImpl
import com.example.bookapplication.domain.models.Movie
import com.example.bookapplication.domain.repository.MovieRepository
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
object MovieModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }


    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        apiMapper: ApiMapper<List<Movie>, MovieDto>
    ): MovieRepository = MovieRepositoryImpl(movieApiService, apiMapper)

    @Provides
    @Singleton
    fun provideApiMapper(): ApiMapper<List<Movie>, MovieDto> = MovieApiMapperImpl()

    @Provides
    @Singleton
    fun provideMovieApiService(): MovieApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieApiService::class.java)
    }
}


