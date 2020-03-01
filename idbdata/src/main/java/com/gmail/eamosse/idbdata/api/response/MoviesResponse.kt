package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.google.gson.annotations.SerializedName

internal data class MoviesResponse(
    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("original_title")
        val original_title:String,

        @SerializedName("id")
        val id:Int,

        @SerializedName("poster_path")
        val poster:String
    )
}

internal fun MoviesResponse.Result.toMovie() = Movie(
    title = original_title,
    id = id,
    poster = poster
)