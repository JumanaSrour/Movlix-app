package com.example.movlix.network.asp.models

import android.app.Dialog
import android.content.DialogInterface

class DialogOnClickListener: DialogInterface {

    private lateinit var dialog:Dialog
    override fun cancel() {
        dialog.cancel()
    }

    override fun dismiss() {
        dialog.dismiss()
        dismiss()
    }

}
