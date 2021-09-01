package com.example.movlix.network.asp.models


data class User(
    var id:Int,
    var name: String,
    var email: String,
    @SerializedName("email_verified_at")
    var emailVerifiedAt: String,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("deleted_at")
    var deletedAt: String
){}

annotation class SerializedName(val value: String)
