package com.codeboxlk.pexel.extension

import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable

/** Android specific unit conversion */

val Int.dp: Int // px to dp
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int // dp to px
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val String.firstCap: String
    get() = lowercase().replaceFirstChar(Char::uppercaseChar)


inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}


fun isTiramisuPlus() = SDK_INT >= Build.VERSION_CODES.TIRAMISU
fun isMarshmallowPlus() = SDK_INT >= Build.VERSION_CODES.M
fun isNougatPlus() = SDK_INT >= Build.VERSION_CODES.N
fun isNougatMR1Plus() = SDK_INT >= Build.VERSION_CODES.N_MR1
fun isOreoPlus() = SDK_INT >= Build.VERSION_CODES.O
fun isOreoMr1Plus() = SDK_INT >= Build.VERSION_CODES.O_MR1
fun isPiePlus() = SDK_INT >= Build.VERSION_CODES.P
fun isQPlus() = SDK_INT >= Build.VERSION_CODES.Q
fun isRPlus() = SDK_INT >= Build.VERSION_CODES.R
fun isSPlus() = SDK_INT >= Build.VERSION_CODES.S