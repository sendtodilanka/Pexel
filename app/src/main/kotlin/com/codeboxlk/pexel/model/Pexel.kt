package com.codeboxlk.pexel.model

data class Pexel(
    val height: Int,
    val id: Int,
    val photographer: String,
    val photographerId: Int,
    val photographerUrl: String,
    val src: PexelSource,
    val url: String,
    val width: Int
)