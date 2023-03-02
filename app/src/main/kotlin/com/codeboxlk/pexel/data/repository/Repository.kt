package com.codeboxlk.pexel.data.repository

import androidx.annotation.WorkerThread
import com.codeboxlk.pexel.data.model.PexelPhoto
import kotlinx.coroutines.flow.Flow

interface Repository {
    @WorkerThread
    fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<PexelPhoto>>
}