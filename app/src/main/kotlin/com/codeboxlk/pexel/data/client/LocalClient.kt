package com.codeboxlk.pexel.data.client

import com.codeboxlk.pexel.data.database.PexelsPhotoDao
import com.codeboxlk.pexel.data.model.PexelPhotoEntity
import com.codeboxlk.pexel.data.model.PexelResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class LocalClient @Inject constructor(private val dao: PexelsPhotoDao) : ClientInterface {

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): ApiResponse<PexelResponse> {
        throw Exception("Method only for LocalDataSource realization")
    }

    override suspend fun getCuratedPhotos(
        page: Int,
        perPage: Int
    ): ApiResponse<PexelResponse> {
        throw java.lang.Exception("Method only for LocalDataSource realization")
    }

    override suspend fun insertPexelsList(pokemonList: List<PexelPhotoEntity>) {
        dao.insertPexelsList(pokemonList)
    }

    override suspend fun getPexelsList(page_: Int, query: String): List<PexelPhotoEntity> {
        return dao.getPexelsList(page_, query)
    }

    override suspend fun getAllPexelsList(page_: Int, query: String): List<PexelPhotoEntity> {
        return dao.getAllPexelsList(page_, query)
    }
}