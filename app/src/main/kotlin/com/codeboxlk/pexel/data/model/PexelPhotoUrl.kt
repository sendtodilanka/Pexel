package com.codeboxlk.pexel.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PexelPhotoUrl(
    @field:Json(name = "landscape") val landscape: String,
    @field:Json(name = "large") val large: String,
    @field:Json(name = "large2x") val large2x: String,
    @field:Json(name = "medium") val medium: String,
    @field:Json(name = "original") val original: String,
    @field:Json(name = "portrait") val portrait: String,
    @field:Json(name = "small") val small: String,
    @field:Json(name = "tiny") val tiny: String
) : Parcelable
