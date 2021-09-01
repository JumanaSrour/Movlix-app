package com.example.movlix.feature.login.presenter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap

import androidx.lifecycle.*
import com.example.movlix.activities.HomeMoviesActivity

import com.example.movlix.feature.login.view.LoginView
import com.example.movlix.network.asp.features.UserRequest
import com.example.movlix.network.asp.models.AppResponse
import com.example.movlix.network.asp.models.LoginResponse
import com.example.movlix.network.asp.models.User
import com.example.movlix.network.utils.Constants.Companion.clientId
import com.example.movlix.network.utils.Constants.Companion.clientSecret
import com.example.movlix.network.utils.Constants.Companion.deviceToken
import com.example.movlix.network.utils.Constants.Companion.deviceType
import com.example.movlix.network.utils.Constants.Companion.grantType
import com.example.movlix.network.utils.Resource
import com.example.movlix.network.utils.Status
import com.example.movlix.utils.AppSharedFunctions
import com.example.movlix.utils.AppSharedFunctions.Companion.checkEditTextIsEmpty
import com.example.movlix.utils.AppSharedFunctions.Companion.getStringFromEditText
import com.example.movlix.utils.AppSharedFunctions.Companion.networkIsConnected
import com.example.movlix.utils.AppSharedFunctions.Companion.showErrorField
import com.example.movlix.utils.MovlixApp

import com.example.movlix.utils.storage.SharedPrefManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

 class LoginPresenter(val mActivity: Activity, var mView: LoginView,val lifecycle: LifecycleOwner):
    AppCompatActivity(), LifecycleObserver{
    @RequiresApi(Build.VERSION_CODES.M)
    public fun login(
        username: EditText,
        password: EditText
    ) {
        if (checkEditTextIsEmpty(username)) {
            showErrorField(username, "this field is required")
            return
        }
        if (checkEditTextIsEmpty(password)) {
            showErrorField(password, "this field is required")
            return
        }

        loginAccount(
            getStringFromEditText(username),
            getStringFromEditText(password)
        )

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loginAccount(
        username: String,
        password: String
    ) {

        if (networkIsConnected(mActivity)) {



            val params = ArrayMap<String, Any>()
            params["username"] = username
            params["password"] = password
            params["device_token"] = deviceToken
            params["device_id"] = AppSharedFunctions.getDeviceId(mActivity)
            params["device_type"] = deviceType
            params["client_id"] = clientId
            params["client_secret"] = clientSecret
            params["grant_type"] = grantType

// observer problem
//            UserRequest().loginUser(params).observe(lifecycle, Observer<Resource<AppResponse>> {
//               GlobalScope.launch {
//                   it?.let { resource ->
//                       when (resource.status) {
//                           Status.SUCCESS -> {
//                               resource.data?.let { appresponse ->
//                                   {
//                                       Log.e("jumana", "loginUser: $appresponse")
//
//                                       if (appresponse.status) {
//                                           val loginResponse = appresponse.items as LoginResponse
//                                           SharedPrefManager.saveUser(loginResponse.user)
//                                           SharedPrefManager.saveToken(loginResponse.token)
//                                           mView.returnUser(loginResponse.user)
//                                       } else {
//                                           mView.showErrorMsg(appresponse.message)
//                                       }
//                                   }
//                               }
//                           }
//                           Status.ERROR -> {
//                               Log.e("Error", "loginUser: ")
//
//                               mView.showErrorMsg(it.message)
//                           }
//
//                           Status.LOADING -> {
//                           }
//                       }
//                   }
//               }
//            })

        }
    }


}


