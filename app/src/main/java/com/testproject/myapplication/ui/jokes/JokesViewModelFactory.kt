package com.testproject.myapplication.ui.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testproject.myapplication.di.AppModule

class JokesViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokeViewModel::class.java)) {
            return JokeViewModel(AppModule.jokesRepository) as T
        }
        throw IllegalArgumentException("Model class must be assignable from JokeViewModel")
    }
}