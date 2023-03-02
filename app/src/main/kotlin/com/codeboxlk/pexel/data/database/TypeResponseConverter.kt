package com.codeboxlk.pexel.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.codeboxlk.pexel.data.model.PexelPhotoUrl
import com.squareup.moshi.Moshi
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(private val moshi: Moshi) {
    @TypeConverter
    fun toPexelPhotoUrl(input: String): PexelPhotoUrl? =
        input.let { Moshi.Builder().build().adapter(PexelPhotoUrl::class.java).fromJson(it) }

    @TypeConverter
    fun fromPexelPhotoUrl(input: PexelPhotoUrl): String? =
        Moshi.Builder().build().adapter(PexelPhotoUrl::class.java).toJson(input)
}
