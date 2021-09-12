package com.example.movlix.network.asp.models

import kotlinx.serialization.Serializable


@Serializable
class TokenBean() {
    var token_type: String = ""

    var expires_in: Int = 0

    var access_token: String = ""

    var refresh_token: String = ""

}
