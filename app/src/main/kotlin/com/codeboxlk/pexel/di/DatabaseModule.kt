package com.codeboxlk.pexel.di

import android.app.Application
import androidx.room.Room
import com.codeboxlk.pexel.data.client.LocalClient
import com.codeboxlk.pexel.data.database.LocalDatabase
import com.codeboxlk.pexel.data.database.PexelsPhotoDao
import com.codeboxlk.pexel.data.database.TypeResponseConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
        typeResponseConverter: TypeResponseConverter
    ): LocalDatabase {
        return Room
            .databaseBuilder(application, LocalDatabase::class.java, "Pexel.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeResponseConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalClient(pexelsPhotoDao: PexelsPhotoDao): LocalClient {
        return LocalClient(pexelsPhotoDao)
    }

    @Provides
    @Singleton
    fun provideLocalPhotoDao(appDatabase: LocalDatabase): PexelsPhotoDao {
        return appDatabase.localPhotoDao()
    }

    @Provides
    @Singleton
    fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
        return TypeResponseConverter(moshi)
    }
}
