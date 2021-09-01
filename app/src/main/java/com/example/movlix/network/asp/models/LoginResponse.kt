package com.example.movlix.network.asp.models

data class LoginResponse(
    var token: TokenBean,
    val user: User,
) {
}