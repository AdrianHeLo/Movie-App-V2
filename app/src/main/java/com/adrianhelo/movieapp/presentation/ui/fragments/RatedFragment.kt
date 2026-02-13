package com.adrianhelo.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.databinding.FragmentRatedBinding
import com.adrianhelo.movieapp.presentation.adapter.MovieAdapter
import com.adrianhelo.movieapp.presentation.viewmodel.MoviesViewModel

class RatedFragment : Fragment() {

    private lateinit var binding: FragmentRatedBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewModel: MoviesViewModel by viewModels()
    private val movieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRatedBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        displayRecyclerView()
        viewModel.movies.observe(viewLifecycleOwner){
            movieAdapter.submitList(it)
        }
        viewModel.getTopRatedMovies(getString(R.string.api_key))

        swipeRefreshLayout = binding.ratedSwipeRefreshContainer
        viewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeRefreshLayout.isRefreshing = isLoading
        }
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getTopRatedMovies(getString(R.string.api_key))
        }

        return binding.root
    }

    private fun displayRecyclerView() {
        binding.ratedFragmentRecyclerView.adapter = movieAdapter
        binding.ratedFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))
    }
}