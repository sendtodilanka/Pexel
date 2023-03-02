package com.codeboxlk.pexel.data.repository

import com.codeboxlk.pexel.data.client.LocalClient
import com.codeboxlk.pexel.data.client.PexelClient
import com.codeboxlk.pexel.data.model.mapper.asDomain
import com.codeboxlk.pexel.data.model.mapper.asEntity
import com.skydoves.sandwich.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val pexelClient: PexelClient,
    private val localClient: LocalClient,
    @Dispatcher(IoDispatchers.IO) private val ioDispatchers: CoroutineDispatcher
) : Repository {

    override fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var pexelPhotoEntities = localClient.getPexelsList(page, query)
        if (pexelPhotoEntities.isEmpty()) {
            pexelClient.searchPhotos(query, page, perPage).suspendOnSuccess {
                pexelPhotoEntities = data.photos.asEntity(page, query)
                localClient.insertPexelsList(pexelPhotoEntities)
                emit(localClient.getAllPexelsList(page, query).asDomain())
            }.onFailure { onError(message()) }
        } else {
            emit(localClient.getAllPexelsList(page, query).asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatchers)
}