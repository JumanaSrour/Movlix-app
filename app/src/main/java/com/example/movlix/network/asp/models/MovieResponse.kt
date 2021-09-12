package com.example.movlix.network.asp.models

data class MovieResponse(
    val items: Movie,
    val total_pages: Int,
    val current_page: Int,
    val total_records: Int
) {
}