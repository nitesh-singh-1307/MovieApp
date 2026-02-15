package com.example.bookapplication.data.mapper_impl

import com.example.bookapplication.common.data.ApiMapper
import com.example.bookapplication.data.remote.models.MovieDto
import com.example.bookapplication.domain.models.Movie
import com.example.bookapplication.utils.GenreConstants

class MovieApiMapperImpl : ApiMapper<List<Movie>, MovieDto> {
    override fun mapToDomain(apiDto: MovieDto): List<Movie> {
        if (apiDto.results.isNullOrEmpty()) return emptyList()
        return apiDto.results.map { result ->
            Movie(
                backdropPath = result?.backdropPath ?: "",
                genreIds =  formatGenre(result?.genreIds),
                id = result?.id ?: 0,
                originalLanguage = result?.originalLanguage ?: "",
                originalTitle = result?.originalTitle ?: "",
                overview = result?.overview ?: "",
                popularity = result?.popularity ?: 0.0,
                posterPath = result?.posterPath ?: "",
                releaseDate = result?.releaseDate ?: "",
                title = result?.title ?: "",
                video = result?.video ?: false,
                voteAverage = result?.voteAverage ?: 0.0,
                voteCount = result?.voteCount ?: 0
            )
        }

    }

    private fun formatEmptyValue(value: String, defaultValue: String): String{
        return value.ifEmpty { defaultValue }
    }

    private fun formatGenre(genreIds: List<Int?>?): List<String>{
        return genreIds?.map { GenreConstants.getGenreNameById(it ?: 0) } ?: emptyList()
    }

}