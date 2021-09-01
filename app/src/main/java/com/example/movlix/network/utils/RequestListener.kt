package com.example.movlix.network.utils

import androidx.lifecycle.LiveData

interface RequestListener<T> {
    fun onSuccess(data: T)
    fun onFailure(message: String)
}