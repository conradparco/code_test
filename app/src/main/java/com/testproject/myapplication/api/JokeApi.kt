package com.testproject.myapplication.api

import com.testproject.myapplication.model.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApi {

    @GET("jokes/random")
    suspend fun fetchRandomJoke(
        @Query("firstName") firstName: String,
        @Query("lastName") lastName: String
    ): JokeResponse
}