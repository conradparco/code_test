package com.testproject.myapplication.ui.jokes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.testproject.myapplication.model.Joke

class JokeDiffUtils(
    private val oldItems: List<Joke>,
    private val newItems: List<Joke>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition] == newItems[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition].id == newItems[newItemPosition].id &&
                oldItems[oldItemPosition].joke == newItems[newItemPosition].joke
}