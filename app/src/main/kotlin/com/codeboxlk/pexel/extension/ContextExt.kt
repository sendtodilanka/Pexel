package com.codeboxlk.pexel.extension

import android.content.Context

/** Screen dimension */
val Context.screenWidthInDp: Int
    get() = resources.displayMetrics.run { (widthPixels / density).toInt() }

val Context.screenHeightInDp: Int
    get() = resources.displayMetrics.run { (heightPixels / density).toInt() }

val Context.screenWidthInPx: Int
    get() = resources.displayMetrics.run { (widthPixels) }

val Context.screenHeightInPx: Int
    get() = resources.displayMetrics.run { (heightPixels) }


