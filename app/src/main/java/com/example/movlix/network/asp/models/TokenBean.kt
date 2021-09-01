package com.example.movlix.network.asp.models

class TokenBean() {
    var token_type: String = ""

    var expires_in: Int = 0

    var access_token: String = ""

    var refresh_token: String = ""
    override fun toString(): String {
        return "TokenBean(token_type=$token_type, expires_in=$expires_in, access_token=$access_token, refresh_token=$refresh_token)"
    }

}
