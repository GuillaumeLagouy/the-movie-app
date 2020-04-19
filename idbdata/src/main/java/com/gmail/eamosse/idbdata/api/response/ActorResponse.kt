package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Actor
import com.google.gson.annotations.SerializedName

internal data class ActorResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("id")
        val id:Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("profile_path")
        val photo:String?
    )
}

internal fun ActorResponse.Result.toActor() = Actor(
    id = id,
    name = name,
    photo = photo
)