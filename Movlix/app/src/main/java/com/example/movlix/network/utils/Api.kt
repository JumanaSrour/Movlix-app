package com.example.movlix.network.utils

import androidx.collection.ArrayMap
import com.example.movlix.network.asp.models.AppResponse
import com.example.movlix.network.asp.models.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("signup")
    suspend fun createUser(
        @FieldMap map: ArrayMap<String, Any>
    ): AppResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @FieldMap map: ArrayMap<String, Any>
    ): Call<AppResponse>

    @GET("profile")
    fun userProfile(): Call<AppResponse>


    @POST("forgetpassword")
    fun forgetPassword(
        @Field("email") status: String,
    ): Call<AppResponse>
}
