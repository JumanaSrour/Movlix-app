package com.example.movlix.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movlix.R
import com.example.movlix.network.asp.models.CustomDialog
import com.example.movlix.network.asp.models.CustomDialog.CustomDialogListener
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        btn_back.setOnClickListener {
            startActivity(Intent(this, HomeMoviesActivity::class.java))
        }

        btn_signout.setOnClickListener {
            createDialog()
        }
    }

    private fun createDialog() {
        val dialog = CustomDialog().newInstance(
            "Sign out",
            "Are you sure you want to Sign out?",
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