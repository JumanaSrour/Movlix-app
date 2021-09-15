package com.example.movlix.feature.homeMovies.view

import com.example.movlix.network.asp.models.Movie

interface MovieView {
    fun returnMovie(movie: Movie?)
    fun showErrorMsg(msg: String?)
}