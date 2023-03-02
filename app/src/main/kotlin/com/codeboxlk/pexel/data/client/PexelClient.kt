package com.codeboxlk.pexel.data.client

import com.codeboxlk.pexel.data.model.PexelPhotoEntity
import com.codeboxlk.pexel.data.model.PexelResponse
import com.codeboxlk.pexel.data.network.PexelService
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class PexelClient @Inject constructor(private val pexelService: PexelService) : ClientInterface {

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): ApiResponse<PexelResponse> = pexelService.searchPhotoByQuery(query, page, perPage)

    override suspend fun getCuratedPhotos(
        page: Int,
        perPage: Int
    ): ApiResponse<PexelResponse> = pexelService.getCuratedPhotos(page, perPage)

    override suspend fun insertPexelsList(pokemonList: List<PexelPhotoEntity>) {
        throw Exception("Method only for LocalDataSource realization")
    }

    override suspend fun getPexelsList(page_: Int, query: String): List<PexelPhotoEntity> {
        throw Exception("Method only for LocalDataSource realization")
    }

    override suspend fun getAllPexelsList(page_: Int, query: String): List<PexelPhotoEntity> {
        throw Exception("Method only for LocalDataSource realization")
    }
}