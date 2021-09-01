package com.example.movlix.network.asp.models

data class ProfileResponse (
    val user: User,
    val favorites: Movie
        ){
}