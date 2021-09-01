package com.example.movlix.feature.login.presenter

import android.app.Activity
import android.os.Build
import android.util.Log
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap

import androidx.lifecycle.*
import com.example.movlix.R

import com.example.movlix.feature.login.view.LoginView
import com.example.movlix.network.asp.features.UserRequest
import com.example.movlix.network.asp.models.LoginResponse
import com.example.movlix.network.utils.Constants.Companion.clientId
import com.example.movlix.network.utils.Constants.Companion.clientSecret
import com.example.movlix.network.utils.Constants.Companion.deviceToken
import com.example.movlix.network.utils.Constants.Companion.deviceType
import com.example.movlix.network.utils.Constants.Companion.grantType
import com.example.movlix.network.utils.RequestListener
import com.example.movlix.utils.AppSharedFunctions
import com.example.movlix.utils.AppSharedFunctions.Companion.checkEditTextIsEmpty
import com.example.movlix.utils.AppSharedFunctions.Companion.getStringFromEditText
import com.example.movlix.utils.AppSharedFunctions.Companion.networkIsConnected
import com.example.movlix.utils.AppSharedFunctions.Companion.showErrorField

class LoginPresenter(val mActivity: Activity, var mView: LoginView,val lifecycle: LifecycleOwner):
    AppCompatActivity(), LifecycleObserver {
     @RequiresApi(Build.VERSION_CODES.M)
     public fun login(
         username: EditText,
         password: EditText
     ) {
         if (checkEditTextIsEmpty(username)) {
             showErrorField(username, "This field is required")
             return
         }
         if (checkEditTextIsEmpty(password)) {
             showErrorField(password, getString(R.string.required))
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

             UserRequest().loginUser(params, object : RequestListener<LoginResponse>{
                 override fun onSuccess(data: LoginResponse) {
                     // save object useer
                     // save is login
                     Log.e("-------------", "$data ", )
                     mView.returnUser(data.user)
                 }

                 override fun onFailure(message: String) {
                     Log.e("------------", message, )
                     mView.showErrorMsg(message)
                 }

             })
         }
     }
 }




