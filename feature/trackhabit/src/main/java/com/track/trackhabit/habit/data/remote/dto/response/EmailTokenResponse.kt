package com.track.trackhabit.habit.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class EmailTokenResponse(
    @SerializedName("message")
    val message: String
)
