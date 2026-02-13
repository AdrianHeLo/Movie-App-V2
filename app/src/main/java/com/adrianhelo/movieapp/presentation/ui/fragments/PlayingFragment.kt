package com.adrianhelo.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.databinding.FragmentPlayingBinding
import com.adrianhelo.movieapp.presentation.adapter.MovieAdapter
import com.adrianhelo.movieapp.presentation.viewmodel.MoviesViewModel

class PlayingFragment : Fragment() {

    private lateinit var binding: FragmentPlayingBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = MovieAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayingBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        displayRecyclerView()

        viewModel.movies.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        viewModel.getNowPlayingMovies(getString(R.string.api_key))

        swipeRefreshLayout = binding.playingSwipeRefreshContainer
        viewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeRefreshLayout.isRefreshing = isLoading
        }
        swipeRefreshLayout.setOnRefreshListener{
            viewModel.getNowPlayingMovies(getString(R.string.api_key))
        }
        return binding.root
    }

    private fun displayRecyclerView() {
        binding.playingFragmentRecyclerView.adapter = adapter
        binding.playingFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))
    }
}