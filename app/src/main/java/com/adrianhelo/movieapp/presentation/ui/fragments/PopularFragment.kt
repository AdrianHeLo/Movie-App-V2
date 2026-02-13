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
import com.adrianhelo.movieapp.databinding.FragmentPopularBinding
import com.adrianhelo.movieapp.presentation.adapter.MovieAdapter
import com.adrianhelo.movieapp.presentation.viewmodel.MoviesViewModel

class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var swipeToRefreshLayout: SwipeRefreshLayout
    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        displayRecyclerView()

        viewModel.movies.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        viewModel.getPopularMovies(getString(R.string.api_key))

        swipeToRefreshLayout = binding.popularSwipeRefreshContainer
        viewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeToRefreshLayout.isRefreshing = isLoading
        }
        swipeToRefreshLayout.setOnRefreshListener{
            viewModel.getPopularMovies(getString(R.string.api_key))
        }
        return binding.root
    }

    private fun displayRecyclerView() {
        binding.popularFragmentRecyclerView.adapter = adapter
        binding.popularFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))
    }
}