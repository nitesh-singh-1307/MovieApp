package com.example.bookapplication.movie_detail.data.mapper_impl

import com.example.bookapplication.common.data.ApiMapper
import com.example.bookapplication.movie_detail.data.remote.models.CastDto
import com.example.bookapplication.movie_detail.data.remote.models.Credits
import com.example.bookapplication.movie_detail.data.remote.models.MovieDetailDto
import com.example.bookapplication.movie_detail.domain.models.Cast
import com.example.bookapplication.movie_detail.domain.models.MovieDetail
import com.example.bookapplication.movie_detail.domain.models.Review
import java.text.SimpleDateFormat
import java.util.Locale

class MovieDetailMapperImpl : ApiMapper<MovieDetail, MovieDetailDto> {

    private fun fromatCast(castDto: List<CastDto>?): List<Cast>{
        return castDto?.map {
            val genderRole =  if (it.gender == 2) "Actor" else "Actress"
            Cast(
                id = it.id ?: 0,
                name = it.name ?: "",
                genderRole = genderRole ,
                character = it.character ?: "",
                profilePath = it.profilePath ?: "",
            )

        }?: emptyList()
    }

    private fun formatTimeStamp(pattern: String = "dd.MM.yy", timestamp: String): String {

        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        // Parse the input date string into a Date object
        val date = inputDateFormat.parse(timestamp)
        // Format the Date object into the desired output string
        val formattedDate = date?.let { outputDateFormat.format(it) } ?: timestamp
        return formattedDate
    }

    private fun convertMinutesToHoursAndMinutes(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return "${hours}h:${remainingMinutes}m"
    }

    private fun formatEmptyValue(value: String, defaultValue: String): String{
        return value.ifEmpty { defaultValue }
    }

    override fun mapToDomain(apiDto: MovieDetailDto): MovieDetail {
        return MovieDetail(
            backdropPath = apiDto.backdropPath!!,
            budget = apiDto.budget!!,
            homepage = apiDto.homepage!!,
            id = apiDto.id,
            imdbId = formatEmptyValue(apiDto.imdbId ?: "", ""),
            originalLanguage = apiDto.originalLanguage!!,
            originalTitle = formatEmptyValue(apiDto.originalTitle ?: "", ""),
            overview = formatEmptyValue(apiDto.overview ?: "", ""),
            popularity = apiDto.popularity ?: 0.0,
            posterPath = formatEmptyValue(apiDto.posterPath ?: "", ""),
            productionCompanies = apiDto.productionCompanies ?: emptyList(),
            productionCountries = apiDto.productionCountries ?: emptyList(),
            releaseDate = formatTimeStamp(timestamp = apiDto.releaseDate ?: ""),
            revenue = apiDto.revenue!!,
            runtime = apiDto.runtime!!,
            spokenLanguages = apiDto.spokenLanguages ?: emptyList(),
            status = formatEmptyValue(apiDto.status ?: "", ""),
            tagline = formatEmptyValue(apiDto.tagline ?: "", ""),
            title = formatEmptyValue(apiDto.title ?: "", ""),
            video = apiDto.video ?: false,
            voteAverage = apiDto.voteAverage ?: 0.0,
            voteCount = apiDto.voteCount ?: 0,
            genres =  apiDto.genres ?: emptyList(),
            credits = apiDto.credits ?: Credits(),
            reviews = apiDto.reviews?.results?.map {
                Review(
                    author = it?.authorDetails?.name ?: "",
                    content = it?.content ?: "",
                    id = it?.id ?: "",
                    createdAt = formatTimeStamp(timestamp = it?.createdAt ?: ""),

                    rating = it?.authorDetails?.rating ?: 0.0
                )
            } ?: emptyList(),
            adult = apiDto.adult ?: false,
            cast = fromatCast(apiDto.credits?.cast),
            language = apiDto.spokenLanguages?.map { it?.name ?: "" } ?: emptyList(),

        )
    }


}