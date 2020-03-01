package com.gmail.eamosse.idbdata.api.response
import com.gmail.eamosse.idbdata.data.TrendingMovie
import com.google.gson.annotations.SerializedName

internal data class TrendingMoviesResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("adult")
        val adult: Boolean?,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("genre_ids")
        val genreIds: List<Int>?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("original_language")
        val originalLanguage: String?,
        @SerializedName("original_title")
        val originalTitle: String?,
        @SerializedName("overview")
        val overview: String?,
        @SerializedName("popularity")
        val popularity: Double?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("release_date")
        val releaseDate: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("video")
        val video: Boolean?,
        @SerializedName("vote_average")
        val voteAverage: Double?,
        @SerializedName("vote_count")
        val voteCount: Int?
    )
}

internal fun TrendingMoviesResponse.Result.toTrending() = TrendingMovie(
    adult = adult ?: false,
    original_title = originalTitle ?: "default",
    title = title ?: "default",
    backdrop_path = backdropPath ?: "default",
    poster_path = posterPath ?: "default",
    id = id ?: 0,
    overview = overview ?: "default",
    release_date = releaseDate ?: "default"
)

