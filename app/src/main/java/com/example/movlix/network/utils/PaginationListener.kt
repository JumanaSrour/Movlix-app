package com.example.movlix.network.utils

interface PaginationListener<T>{
    fun onSuccess(data: T, num:Int)
    fun onFailure(message:String)
}