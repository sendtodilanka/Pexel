package com.codeboxlk.pexel.data.model

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import java.io.Serializable

sealed class PhotoCategory(val name: String, @ColorInt val color: Int? = null) : Serializable {
    // future categories for wallpapers, using like search request to API but with name string

    // base themes
    class ABSTRACT : PhotoCategory("abstract")
    class NATURE : PhotoCategory("nature")
    class ARCHITECTURE : PhotoCategory("architecture")
    class ANIMALS : PhotoCategory("animals")
    class PORTRAITS : PhotoCategory("portraits")
    class SEA : PhotoCategory("sea")
    class NIGHT : PhotoCategory("night")

    // colors
    class COLOR_BLACK : PhotoCategory("black", Color.BLACK)
    class COLOR_WHITE : PhotoCategory("white", Color.WHITE)
    class COLOR_RED : PhotoCategory("red", Color.RED)
    class COLOR_GREEN : PhotoCategory("green", Color.GREEN)
    class COLOR_BLUE : PhotoCategory("blue", Color.BLUE)
    class COLOR_YELLOW : PhotoCategory("yellow", Color.YELLOW)
    class COLOR_VIOLET : PhotoCategory("violet", Color.MAGENTA)
}