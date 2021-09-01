package com.example.movlix.network.asp.models


data class Token(
    @SerializedName("token_type")
    var tokenType: String,
    @SerializedName("expires_in")
    var expiresIn: Int,
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("refresh_token")
    var refreshToken: String
) {
}