package com.example.movlix.network.utils

import android.provider.Settings
import androidx.collection.ArrayMap
import com.example.movlix.network.asp.models.AppResponse
import com.example.movlix.network.asp.models.LoginResponse
import com.example.movlix.network.asp.models.User
import com.example.movlix.network.utils.Constants.Companion.BASE_URL
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }
        val api by lazy {
            retrofit.create(Api::class.java)
        }

    }
    suspend fun createUser(map: ArrayMap<String, Any>): AppResponse{
        return api.createUser(map)
    }

    suspend fun loginUser(map: ArrayMap<String, Any>):Call<AppResponse>{
        return api.userLogin(map)
    }

}