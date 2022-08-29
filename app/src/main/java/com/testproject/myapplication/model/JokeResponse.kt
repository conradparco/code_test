package com.testproject.myapplication.model

import com.google.gson.annotations.SerializedName

data class JokeResponse(
    val type: String,
    @SerializedName("value") val joke: Joke
)