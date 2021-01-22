package com.acsoft.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat
import java.util.*


fun View.hideKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


fun getDate() : String {
    var date = Date()
    val formatter = SimpleDateFormat("MMMM dd, yyyy HH:mm", Locale.forLanguageTag("es-ES"))
    return formatter.format(date)
}

fun upperCase(text: String) : String {
    return text.capitalize()
}
