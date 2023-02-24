package com.codeboxlk.pexel.service

import com.codeboxlk.pexel.model.PexelResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val STANDARD_PAGE_SIZE = 15

interface PexelService {
    @GET("v1/search")
    fun searchPhotoByQuery(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = STANDARD_PAGE_SIZE
    ): ApiResponse<PexelResponse>
}