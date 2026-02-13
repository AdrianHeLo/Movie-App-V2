package com.adrianhelo.movieapp.data.model

import android.util.Log
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    @Expose
    val movieId: Int,

    @SerializedName("overview")
    @Expose
    val movieOverview: String,

    @SerializedName("poster_path")
    @Expose
    val moviePosterPath: String,

    @SerializedName("release_date")
    @Expose
    val movieReleaseDate: String,

    @SerializedName("title")
    @Expose
    val movieTittle: String,

    @SerializedName("vote_average")
    @Expose
    val movieVoteAverage: Double
)
