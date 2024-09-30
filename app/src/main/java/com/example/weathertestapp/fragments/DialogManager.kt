package com.example.weathertestapp.fragments

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import com.example.weathertestapp.R

object DialogManager  {

    fun onSearchByNameDialog(context:Context,listener:Listener){
        val builder = AlertDialog.Builder(context, R.style.dialogTheme)
        val edName = EditText(context,)
        builder.setView(edName)
        val dialog = builder.create()
        dialog.setTitle("City name: ")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE,"OK"){
                _,_ ->
            listener.onClick(edName.text.toString())
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL"){_,_ ->
            dialog.dismiss()
        }
        dialog.show()
    }
    interface Listener {
        fun onClick(name:String)
    }
}