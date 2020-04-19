package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.ActorDetail
import com.google.gson.annotations.SerializedName

internal data class ActorDetailResponse (
    @SerializedName("name")
    val name:String,

    @SerializedName("biography")
    val bio:String,

    @SerializedName("profile_path")
    val photo:String?
)

internal fun ActorDetailResponse.toActorDetail() = ActorDetail(
    name = name,
    bio = bio,
    photo = photo
)