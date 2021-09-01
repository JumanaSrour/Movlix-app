package com.example.movlix.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movlix.R
import com.example.movlix.network.asp.models.CustomDialog
import com.example.movlix.network.asp.models.CustomDialog.CustomDialogListener
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var listener: CustomDialogListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)


        btn_send.setOnClickListener {
            createDialog()
        }

    }

    private fun createDialog() {
        val dialog = CustomDialog().newInstance(
            "Send Email",
            "Are you sure you want to use this email address to reset password",
            "Accept",
            "Cancel"
        )
        dialog.show(supportFragmentManager, "CustomDialogFragment")
        dialog.onClickListener(object : CustomDialogListener {
            override fun onDialogPositiveClick(str: String) {
                dialog.dismiss()
            }

            override fun onDialogNegativeClick(str: String) {
                dialog.dismiss()
            }

        })
    }

}