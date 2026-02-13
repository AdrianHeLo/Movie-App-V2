package com.adrianhelo.movieapp.data.repository

import android.app.Application
import androidx.core.R
import com.adrianhelo.movieapp.data.remote.RetrofitInstance

class MoviesRepository {
        suspend fun getPopularMovies(apiKey: String) = RetrofitInstance.api.getPopularMovies(apiKey)
        suspend fun getUpcomingMovies(apiKey: String) = RetrofitInstance.api.getUpcomingMovies(apiKey)
        suspend fun getNowPlayingMovies(apiKey: String) = RetrofitInstance.api.getNowPlayingMovies(apiKey)
        suspend fun getTopRatedMovies(apiKey: String) = RetrofitInstance.api.getTopRatedMovies(apiKey)
}