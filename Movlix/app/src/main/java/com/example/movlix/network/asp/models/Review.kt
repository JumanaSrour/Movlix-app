package com.example.movlix.network.asp.models

data class Review (
    val reviewId: Int,
    val reviewDesc: String,
    val userId: Int,
    val userImage: Int,
    val username : String
        ){
}