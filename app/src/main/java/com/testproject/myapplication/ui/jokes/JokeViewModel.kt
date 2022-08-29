package com.testproject.myapplication.ui.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testproject.myapplication.model.Joke
import com.testproject.myapplication.model.JokeRequest
import com.testproject.myapplication.model.NetworkResponse
import com.testproject.myapplication.repository.JokesRepositoryContract
import kotlinx.coroutines.launch

class JokeViewModel(
    private val repo: JokesRepositoryContract
) : ViewModel() {

    private val _jokeResponse = MutableLiveData<NetworkResponse<List<Joke>>>()
    val jokeResponse: LiveData<NetworkResponse<List<Joke>>> get() = _jokeResponse
    private val _cachedJokes = mutableListOf<Joke>()

    fun fetchJokes() {
        // TODO replace with viewmodelscope when you have time
        _jokeResponse.postValue(NetworkResponse.Loading())
        viewModelScope.launch {
            try {
                val joke = repo.fetchJoke(JokeRequest("John","Doe"))
                if (joke == null) {
                    _jokeResponse.postValue(NetworkResponse.Error(NoJokeException()))
                } else {
                    _cachedJokes.add(joke)
                    _jokeResponse.postValue(NetworkResponse.Success(_cachedJokes))
                }
            } catch (e: Throwable) {
                _jokeResponse.postValue(NetworkResponse.Error(e))
            }
        }
    }
}

class NoJokeException : Throwable() {
    override val message: String = "No joke was found"
}