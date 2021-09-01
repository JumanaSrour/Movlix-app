package com.example.movlix.feature.signup.view

import com.example.movlix.network.asp.models.User

interface SignupView {
    fun returnUser(field: User)
    fun showErrorMsg(msg: String?)
}