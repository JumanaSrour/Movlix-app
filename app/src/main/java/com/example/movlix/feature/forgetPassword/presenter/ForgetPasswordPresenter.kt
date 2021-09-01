package com.example.movlix.feature.forgetPassword.presenter

import android.app.Activity
import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.manager.Lifecycle
import com.example.movlix.R
import com.example.movlix.feature.forgetPassword.view.ForgetPasswordView
import com.example.movlix.network.asp.features.UserRequest
import com.example.movlix.network.asp.models.CustomDialog
import com.example.movlix.network.asp.models.ForgetPasswordResponse
import com.example.movlix.network.utils.RequestListener
import com.example.movlix.utils.AppSharedFunctions
import kotlinx.android.synthetic.main.activity_forget_password.*

data class ForgetPasswordPresenter(val mActivity: Activity, val mView: ForgetPasswordView) {

    @RequiresApi(Build.VERSION_CODES.M)
    public fun send(
        email: EditText
    ) {
        sendEmailUser(
            AppSharedFunctions.getStringFromEditText(email)
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun sendEmailUser(
        email: String
    ) {
        if (AppSharedFunctions.networkIsConnected(mActivity)) {
            val params = ArrayMap<String, Any>()
            params["email"] = email

            Log.e("-------", "$params")
            UserRequest().resetPassword(
                params,
                object : RequestListener<ForgetPasswordResponse> {
                    override fun onSuccess(data: ForgetPasswordResponse) {
                        Log.e("-----", "$data")
                    }

                    override fun onFailure(message: String) {
                        Log.e("------", "$message")
                        mView.showErrorMsg(message)
                    }


                })
        }
    }

    fun createDialog(ed_email_forget_password: EditText) {

        if (AppSharedFunctions.checkEditTextIsEmpty(ed_email_forget_password)) {
            AppSharedFunctions.showErrorField(ed_email_forget_password, Resources.getSystem().getString(R.string.required))
            return
        }

        val dialog = CustomDialog().newInstance(
           title =  Resources.getSystem().getString(R.string.send_email),
            msg = Resources.getSystem().getString(R.string.sure_to_use_this_email),
            btnCancel = Resources.getSystem().getString(R.string.accept),
            btnOk = Resources.getSystem().getString(R.string.cancel)
        )
        dialog.show(
            (mActivity as AppCompatActivity).supportFragmentManager,
            "CustomDialogFragment"
        )
        dialog.onClickListener(object : CustomDialog.CustomDialogListener {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onDialogPositiveClick(str: String) {
                send(ed_email_forget_password)
                dialog.dismiss()
            }

            override fun onDialogNegativeClick(str: String) {
                dialog.dismiss()
            }

        })
    }
}