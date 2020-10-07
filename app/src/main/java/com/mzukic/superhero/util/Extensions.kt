package com.mzukic.superhero.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

/**
 * Helper function to parse [TextWatcher.onTextChanged] event of [TextWatcher] only.
 * @receiver EditText
 * @param event (input: String) -> Unit
 */
inline fun EditText.onTextChange(crossinline event: (input: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            // Left empty on purpose
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Left empty on purpose
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val response = s?.toString() ?: ""
            event(response)
        }
    })
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}
