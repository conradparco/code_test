package com.testproject.myapplication.di

import com.testproject.myapplication.repository.JokesRepository
import com.testproject.myapplication.repository.JokesRepositoryContract


// TODO replace with dagger
object AppModule {

    val jokesRepository: JokesRepositoryContract by lazy {
        JokesRepository(NetworkModule.jokeApi)
    }
}