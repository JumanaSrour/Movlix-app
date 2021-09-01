package com.example.movlix.feature.profile.view

import com.example.movlix.network.asp.models.User

interface ProfileView {
    fun returnUser(user: User)
    fun showErrorMsg(msg: String?)
}