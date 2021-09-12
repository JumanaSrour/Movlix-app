package com.example.movlix.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movlix.R
import com.example.movlix.feature.homeMovies.view.HomeMoviesActivity
import com.example.movlix.feature.login.view.LoginActivity
import com.example.movlix.network.asp.models.CustomDialog
import com.example.movlix.network.asp.models.CustomDialog.CustomDialogListener
import com.example.movlix.utils.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        btn_back.setOnClickListener {
            startActivity(Intent(this, HomeMoviesActivity::class.java))
            finish()
        }

        btn_signout.setOnClickListener {
            createDialog()
        }
    }

    private fun createDialog() {
        val dialog = CustomDialog().newInstance(
            getString(R.string.sign_out),
            getString(R.string.are_you_sure_signout),
            getString(R.string.accept),
            getString(R.string.cancel)
        )
        dialog.show(supportFragmentManager, "CustomDialogFragment")
        dialog.onClickListener(object : CustomDialogListener {
            override fun onDialogPositiveClick(str: String) {
                SharedPrefManager.clear()
                dialog.dismiss()
                startActivity(LoginActivity.newInstance(mActivity = this@SettingsActivity))
            }

            override fun onDialogNegativeClick(str: String) {
                dialog.dismiss()
            }

        })
    }

}