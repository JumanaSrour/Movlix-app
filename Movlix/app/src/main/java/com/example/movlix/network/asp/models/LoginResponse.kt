package com.example.movlix.network.asp.models

data class LoginResponse(
    val token: Token,
    val user: User,
) {
}