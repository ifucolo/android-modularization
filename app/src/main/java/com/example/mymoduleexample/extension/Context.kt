package com.example.mymoduleexample.extension

import android.content.Context
import android.widget.Toast

/**
 * Created by bruno on 07/07/17.
 */
inline fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}