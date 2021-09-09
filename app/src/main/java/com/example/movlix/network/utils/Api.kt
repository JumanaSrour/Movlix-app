package com.example.movlix.network.utils

import androidx.collection.ArrayMap
import com.example.movlix.network.asp.models.AppResponse
import com.example.movlix.network.asp.models.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("signup")
    suspend fun createUser(
        @FieldMap map: ArrayMap<String, Any>
    ): Response<AppResponse>

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @FieldMap map: ArrayMap<String, Any>
    ): Response<AppResponse>

    @GET("profile")
    suspend fun userProfile(): Response<AppResponse>

    @FormUrlEncoded
    @POST("forget")
    suspend fun forgetPassword(
        @FieldMap map: ArrayMap<String, Any>
    ): Response<AppResponse>


    // Movies Api
    @FormUrlEncoded
    @POST("movies")
    suspend fun listOfMovies(
        @FieldMap map: ArrayMap<String, Any>
    ): Response<AppResponse>
}
