package com.codeboxlk.pexel.data.database

import androidx.room.*
import com.codeboxlk.pexel.data.model.PexelPhotoEntity

@Dao
interface PexelsPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPexelsList(pexelPhotoEntities: List<PexelPhotoEntity>)

    @Query("SELECT * FROM PexelPhotoEntity WHERE page = :page_ AND query = :query_")
    suspend fun getPexelsList(page_: Int, query_: String): List<PexelPhotoEntity>

    @Query("SELECT * FROM PexelPhotoEntity WHERE page <= :page_ AND query = :query_")
    suspend fun getAllPexelsList(page_: Int, query_: String): List<PexelPhotoEntity>
}
