package com.codeboxlk.pexel.di

import com.codeboxlk.pexel.data.repository.Repository
import com.codeboxlk.pexel.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindsRepository(repositoryImpl: RepositoryImpl): Repository
}
