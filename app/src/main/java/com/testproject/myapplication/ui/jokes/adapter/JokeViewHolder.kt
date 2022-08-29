package com.testproject.myapplication.ui.jokes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testproject.myapplication.databinding.RowJokeItemBinding
import com.testproject.myapplication.model.Joke

class JokeViewHolder(
    val parent: ViewGroup,
    private val binding: RowJokeItemBinding = RowJokeItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bindModelToView(model: Joke) {
        binding.jokeTextView.text = model.joke
    }
}