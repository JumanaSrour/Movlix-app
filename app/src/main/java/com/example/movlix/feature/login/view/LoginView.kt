package com.example.movlix.feature.login.view

import com.example.movlix.network.asp.models.User

interface LoginView {
    fun returnUser(user: User)
    fun showErrorMsg(msg: String?)
}