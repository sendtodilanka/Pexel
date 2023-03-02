package com.codeboxlk.pexel.data.network

import com.codeboxlk.pexel.data.model.PexelResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelService {
    @GET("v1/search")
    suspend fun searchPhotoByQuery(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiResponse<PexelResponse>

    @GET("v1/curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiResponse<PexelResponse>

    /*@GET("photos/{id}pexels-photo-{id}.jpeg")
    fun getPhotoById(
        @Path("id") id: String,
        @Query("h") height: Int,
        @Query("w") width: Int
    )*/
}