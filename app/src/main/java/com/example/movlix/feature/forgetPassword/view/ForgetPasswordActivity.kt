package com.example.movlix.feature.forgetPassword.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.movlix.R
import com.example.movlix.feature.forgetPassword.presenter.ForgetPasswordPresenter
import com.example.movlix.network.asp.models.CustomDialog.CustomDialogListener
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : AppCompatActivity(), ForgetPasswordView {
    private lateinit var mPresenter: ForgetPasswordPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        mPresenter = ForgetPasswordPresenter(this, this)
        btn_send.setOnClickListener {
            mPresenter.createDialog(ed_email_forget_password)
        }

    }
    override fun showErrorMsg(msg: String?) {
        Log.e("------", "showErrorMsg: $msg", )
    }

}