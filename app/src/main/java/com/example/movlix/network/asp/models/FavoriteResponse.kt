package com.example.movlix.network.asp.models

data class FavoriteResponse (
    var token: TokenBean,
    val isFavorite: Boolean,
    val items: Any
    ){
}