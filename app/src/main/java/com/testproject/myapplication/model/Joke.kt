package com.testproject.myapplication.model

data class Joke(
    val id: Long,
    val joke: String,
    val categories: List<String>? = null
)