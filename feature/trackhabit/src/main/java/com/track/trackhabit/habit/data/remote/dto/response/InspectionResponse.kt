package com.track.trackhabit.habit.data.remote.dto.response


import com.google.gson.annotations.SerializedName

data class InspectionResponse(
    @SerializedName("createdAt")
    val isCheck: String?,
)