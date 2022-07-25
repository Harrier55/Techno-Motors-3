package com.example.techno_motors_3.one.util

import android.app.AlertDialog
import android.content.Context
import com.example.techno_motors_3.R

class MyDialogs {

    companion object{
        /**Диаложка уведомления успеха/ошибки отправки формы*/
         fun notificationOfSending(context: Context, send: String) {
            AlertDialog.Builder(context, R.style.AlertDialogCustom)
                .setTitle("")
                .setMessage(send)
                .setPositiveButton("Ok",null)
                .show()
        }

        fun notificationOfSending(context: Context, send: Int) {
            AlertDialog.Builder(context, R.style.AlertDialogCustom)
                .setTitle("")
                .setMessage(send)
                .setPositiveButton("Ok",null)
                .show()
        }
    }



}