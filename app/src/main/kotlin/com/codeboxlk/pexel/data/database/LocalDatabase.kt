package com.codeboxlk.pexel.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codeboxlk.pexel.data.model.PexelPhotoEntity

@Database(
    entities = [PexelPhotoEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun localPhotoDao(): PexelsPhotoDao
}
