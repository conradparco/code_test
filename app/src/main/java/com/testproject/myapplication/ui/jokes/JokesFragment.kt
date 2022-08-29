package com.testproject.myapplication.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.testproject.myapplication.R
import com.testproject.myapplication.databinding.FragmentJokesBinding
import com.testproject.myapplication.model.Joke
import com.testproject.myapplication.model.NetworkResponse
import com.testproject.myapplication.ui.jokes.adapter.JokeAdapter

class JokesFragment : Fragment() {

    private var _binding: FragmentJokesBinding? = null
    private val binding: FragmentJokesBinding by lazy {
        _binding!!
    }
    private val jokeAdapter: JokeAdapter by lazy { JokeAdapter() }
    private val viewModel: JokeViewModel by lazy {
        ViewModelProvider(requireActivity(), JokesViewModelFactory())[JokeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentJokesBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        observe()
        setOnFetchMoreButton()
    }

    private fun setOnFetchMoreButton() {
        binding.addJokesButton.setOnClickListener {
            viewModel.fetchJokes()
        }
    }

    private fun observe() {
        viewModel.jokeResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResponse.Loading -> showLoadingBar()
                is NetworkResponse.Error -> handleError()
                is NetworkResponse.Success -> handleSuccess(it)
            }
        }
    }

    private fun showLoadingBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoadingBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun handleError() {
        stopLoadingBar()
        Snackbar.make(
            binding.root,
            R.string.error_adding_joke,
            Snackbar.LENGTH_LONG
        ).apply {
            setAction(R.string.retry) {
                viewModel.fetchJokes()
            }
        }.show()
    }

    private fun handleSuccess(success: NetworkResponse.Success<List<Joke>>) {
        stopLoadingBar()
        jokeAdapter.updateItems(success.data)
    }

    private fun setViews() {
        binding.recyclerView.apply {
            adapter = jokeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = JokesFragment()
    }
}