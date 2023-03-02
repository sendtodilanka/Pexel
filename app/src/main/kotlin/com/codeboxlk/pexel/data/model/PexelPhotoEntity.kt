package com.codeboxlk.pexel.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class PexelPhotoEntity(
    @PrimaryKey val id: Int,
    var page: Int,
    var query: String,
    val title: String,
    val width: Int,
    val height: Int,
    val url: String,
    val src: PexelPhotoUrl,
    val avgColor: String,
    val photographer: String,
    val photographerId: Int,
    val photographerUrl: String,
)