package com.example.movlix.feature.signup.presenter

import android.app.Activity
import android.os.Build
import android.util.Log
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.collection.ArrayMap
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.movlix.feature.login.view.LoginView
import com.example.movlix.network.asp.features.UserRequest
import com.example.movlix.network.asp.models.LoginResponse
import com.example.movlix.network.utils.RequestListener
import com.example.movlix.utils.AppSharedFunctions
import com.example.movlix.utils.AppSharedFunctions.Companion.getStringFromEditText

data class SignupPresenter(val mActivity: Activity, var mView: LoginView, val lifecycle: LifecycleOwner) : LifecycleObserver{
    @RequiresApi(Build.VERSION_CODES.M)
    public fun signup(
        email: EditText,
        password: EditText,
        confirmPassword: EditText
    ) {
        if (AppSharedFunctions.checkEditTextIsEmpty(email)) {
            AppSharedFunctions.showErrorField(email, "this field is required")
            return
        }
        if (AppSharedFunctions.checkEditTextIsEmpty(password)) {
            AppSharedFunctions.showErrorField(password, "this field is required")
            return
        }
        if (AppSharedFunctions.checkEditTextIsEmpty(confirmPassword)) {
            AppSharedFunctions.showErrorField(confirmPassword, "this field is required")
            return
        }

        singupUser(
            getStringFromEditText(email),
            getStringFromEditText(password),
            getStringFromEditText(confirmPassword)
        )
    }
        @RequiresApi(Build.VERSION_CODES.M)
        private fun singupUser(
            email: String,
            password: String,
            confirmPassword: String
        ) {
            if (AppSharedFunctions.networkIsConnected(mActivity)) {

                val params = ArrayMap<String, Any>()
                params["email"] = email
                params["password"] = password
                params["confirmPassword"] = confirmPassword

                UserRequest().loginUser(params, object : RequestListener<LoginResponse> {
                    override fun onSuccess(data: LoginResponse) {
                        Log.e("-------------", "$data ",)
                        mView.returnUser(data.user)
                    }

                    override fun onFailure(message: String) {
                        Log.e("------------", message,)
                        mView.showErrorMsg(message)
                    }

                })
            }

        }
}