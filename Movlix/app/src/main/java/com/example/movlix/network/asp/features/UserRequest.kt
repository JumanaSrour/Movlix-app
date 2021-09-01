package com.example.movlix.network.asp.features

import android.util.Log
import androidx.collection.ArrayMap
import com.example.movlix.network.utils.Resource
import com.example.movlix.network.utils.RetrofitClient
import androidx.lifecycle.liveData
import io.reactivex.internal.util.HalfSerializer.onError
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class UserRequest{
    private val retrofitClient = RetrofitClient();
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Exception handled:", " ${throwable.localizedMessage}")

        fun createUser(map: ArrayMap<String, Any>) = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
                (Resource.loading(data = null))
                try {
                    (Resource.success(data = retrofitClient.createUser(map)))
                } catch (exception: Exception) {
                    (Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
                }
            }
    }

         suspend fun loginUser(map: ArrayMap<String, Any>) = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
                try {
                    Log.e("Error", "Success:")
                    (Resource.success(data = retrofitClient.loginUser(map)))
                } catch (e: java.lang.Exception) {
                    Log.e("Error", "loginUser: , ${e.message}")
                    (Resource.error(data = null, message = e.message ?: "Error Occurred!"))
                }
            }

        }
    }
}
