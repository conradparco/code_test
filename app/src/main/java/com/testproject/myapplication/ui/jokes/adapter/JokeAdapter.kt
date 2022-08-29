package com.testproject.myapplication.ui.jokes.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.testproject.myapplication.model.Joke

class JokeAdapter : RecyclerView.Adapter<JokeViewHolder>() {

    private val items = mutableListOf<Joke>()

    fun updateItems(jokes: List<Joke>) {
        val utils = JokeDiffUtils(items, jokes)
        val diffCallback = DiffUtil.calculateDiff(utils)
        this.items.clear()
        this.items.addAll(jokes)
        diffCallback.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = JokeViewHolder(parent)

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bindModelToView(items[position])
    }

    override fun getItemCount() = items.size
}