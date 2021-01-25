package com.acsoft.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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


fun getDate() : String {
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
