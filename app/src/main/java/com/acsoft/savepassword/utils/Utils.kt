package com.acsoft.savepassword.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.acsoft.savepassword.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import java.text.SimpleDateFormat
import java.util.*


fun View.hideKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


fun getDateFormat() : String {
    val date = Date()
    val formatter = SimpleDateFormat("MMMM dd, yyyy HH:mm", Locale.forLanguageTag("es-ES"))
    return formatter.format(date)
}


fun upperCase(text: String) : String {
    return text.capitalize()
}

fun copyToClipboard(view: View,context: Context,text: String)  {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("text", text)
    clipboardManager.setPrimaryClip(clipData)
    make(view,context.getString(R.string.copied_clipboard),Snackbar.LENGTH_LONG).show()
}

fun clickVibration(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(50)
    }
}

fun wrongPasswordVibration(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.EFFECT_HEAVY_CLICK))
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(200)
    }
}
