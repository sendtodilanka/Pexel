package com.codeboxlk.pexel.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PexelResponse(
    @field:Json(name = "next_page") val nextPage: String?,
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "per_page") val perPage: Int,
    @field:Json(name = "photos") val photos: List<PexelPhoto>,
    @field:Json(name = "total_results") val totalResults: Int
)
