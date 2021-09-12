package com.example.movlix.network.utils

import androidx.collection.ArrayMap
import com.example.movlix.network.asp.models.AppResponse
import com.example.movlix.network.utils.Constants.Companion.BASE_URL
import com.example.movlix.utils.storage.SharedPrefManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    var api: Api? = null

    constructor() {
        try {
            val interceptorToHeaderData = Interceptor { chain ->
                val builder = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        if (SharedPrefManager.isLoggedIn) "Bearer" + " " +
                                SharedPrefManager.token.access_token else ""
                    )
                    .build()
                chain.proceed(builder)
            }
            init(interceptorToHeaderData)
        } catch (e: Exception) {

        }
    }

    private fun init(interceptor: Interceptor) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(6000, TimeUnit.MILLISECONDS)
            .build()


        this.api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .build()
            .create(Api::class.java)
    }

    suspend fun createUser(map: ArrayMap<String, Any>): Response<AppResponse> {
        return api!!.createUser(map)
    }

    suspend fun loginUser(map: ArrayMap<String, Any>): Response<AppResponse> {
        return api!!.userLogin(map)
    }

    suspend fun forgetPassword(map: ArrayMap<String, Any>): Response<AppResponse> {
        return api!!.forgetPassword(map)
    }

    suspend fun userProfile(): Response<AppResponse> {
        return api!!.userProfile()
    }

    suspend fun listOfMovies(map: ArrayMap<String, Any>): Response<AppResponse>{
        return api!!.listOfMovies(map)
    }

    suspend fun addFavoriteItem(map:ArrayMap<String, Any>): Response<AppResponse>{
        return api!!.favoriteItem(map)
    }

}