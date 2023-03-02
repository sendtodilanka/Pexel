package com.codeboxlk.pexel.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PexelPhoto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "alt") val title: String,
    @field:Json(name = "width") val width: Int,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "src") val src: PexelPhotoUrl,
    @field:Json(name = "avg_color") val avgColor: String,
    @field:Json(name = "photographer") val photographer: String,
    @field:Json(name = "photographer_id") val photographerId: Int,
    @field:Json(name = "photographer_url") val photographerUrl: String,
) : Parcelable