package com.codeboxlk.pexel.di

import com.codeboxlk.pexel.data.client.PexelClient
import com.codeboxlk.pexel.data.network.HttpRequestInterceptor
import com.codeboxlk.pexel.data.network.PexelService
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val PEXELS_EDNPOINT = "https://api.pexels.com/"

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(PEXELS_EDNPOINT)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePexelService(retrofit: Retrofit): PexelService {
        return retrofit.create(PexelService::class.java)
    }

    @Provides
    @Singleton
    fun providePexelClient(pexelService: PexelService): PexelClient {
        return PexelClient(pexelService)
    }
}
