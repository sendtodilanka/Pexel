package com.codeboxlk.pexel.data.model

import androidx.annotation.DrawableRes

data class CategoryModel(
    val category: PhotoCategory,
    @DrawableRes val categoryPhotoUrl: Int
)