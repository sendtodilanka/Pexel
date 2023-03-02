package com.codeboxlk.pexel.di

import com.codeboxlk.pexel.data.repository.Dispatcher
import com.codeboxlk.pexel.data.repository.IoDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

    @Provides
    @Dispatcher(IoDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
