package com.codeboxlk.pexel.data.client

import com.codeboxlk.pexel.data.model.PexelPhotoEntity
import com.codeboxlk.pexel.data.model.PexelResponse
import com.skydoves.sandwich.ApiResponse

interface ClientInterface {

    suspend fun searchPhotos(query: String, page: Int, perPage: Int): ApiResponse<PexelResponse>
    suspend fun getCuratedPhotos(page: Int, perPage: Int): ApiResponse<PexelResponse>

    suspend fun insertPexelsList(pokemonList: List<PexelPhotoEntity>)
    suspend fun getPexelsList(page_: Int, query: String): List<PexelPhotoEntity>
    suspend fun getAllPexelsList(page_: Int, query: String): List<PexelPhotoEntity>
}
