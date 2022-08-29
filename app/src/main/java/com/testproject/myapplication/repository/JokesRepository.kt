package com.testproject.myapplication.repository

import com.testproject.myapplication.api.JokeApi
import com.testproject.myapplication.model.Joke
import com.testproject.myapplication.model.JokeRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface JokesRepositoryContract {

    suspend fun fetchJoke(jokeRequest: JokeRequest): Joke?

}

class JokesRepository(
    private val api: JokeApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : JokesRepositoryContract {

    override suspend fun fetchJoke(jokeRequest: JokeRequest) = withContext(dispatcher) {
        return@withContext api.fetchRandomJoke(jokeRequest.firstName, jokeRequest.lastName).joke
    }
}
