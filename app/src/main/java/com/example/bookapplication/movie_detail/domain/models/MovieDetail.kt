package com.example.bookapplication.movie_detail.domain.models

import com.example.bookapplication.movie_detail.data.remote.models.Credits
import com.example.bookapplication.movie_detail.data.remote.models.Genre
import com.example.bookapplication.movie_detail.data.remote.models.ProductionCompany
import com.example.bookapplication.movie_detail.data.remote.models.ProductionCountry
import com.example.bookapplication.movie_detail.data.remote.models.SpokenLanguage

data class MovieDetail(
    val id: Int?,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val genres: List<Genre>,
    val runtime: Int,
    val voteAverage: Double,
    val voteCount: Int,
    val budget: Int,
    val revenue: Int,
    val homepage: String,
    val originalLanguage: String,
    val status: String,
    val tagline: String,
    val popularity: Double,
    val backdropPath: String,
    val imdbId: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val spokenLanguages: List<SpokenLanguage>,
    val credits: Credits,
    val reviews: List<Review>,
    val adult: Boolean,
    val originalTitle: String,
    val video: Boolean,
    val cast: List<Cast>,
    val language: List<String>
)

data class Cast(
    val id: Int,
    val name: String,
    val genderRole: String,
    val character: String,
    val profilePath: String,
) {
    private val nameParts = name.split(" ", limit = 2)
    val firstName = nameParts.first()
    val lastName = nameParts.last()
}

data class Review(
    val author: String,
    val content: String,
    val id: String,
    val createdAt:String,
    val rating: Double
)