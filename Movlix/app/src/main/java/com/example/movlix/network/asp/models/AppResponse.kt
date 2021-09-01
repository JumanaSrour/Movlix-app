package com.example.movlix.network.asp.models


data class AppResponse(
    var status: Boolean,
    @SerializedName("status_code")
    var statusCode: Int,
    var message: String,
    var items: Any
) {
}