package com.example.movlix.network.asp.models

import com.google.gson.Gson


data class MovieResponse(
    val data: Any,
    val total_pages: Int,
    val current_page: Int,
    val total_records: Int
) {
    fun getResult(): String {
        return Gson().toJson(data).toString()
    }
}