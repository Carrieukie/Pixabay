package com.karis.adalabs_pixabayapi.di

import android.app.Application
import androidx.room.Room
import com.karis.adalabs_pixabayapi.data.local.AppDatabase
import com.karis.adalabs_pixabayapi.data.local.ImagesDao
import com.karis.adalabs_pixabayapi.paging.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "images.db")
            .build()


    @Provides
    @Singleton
    fun providesImagesDao(appDatabase: AppDatabase): ImagesDao =
        appDatabase.imagesDao

    @Provides
    @Singleton
    fun providesRemoteKeysDao(appDatabase: AppDatabase): RemoteKeysDao =
        appDatabase.remoteKeysDao

}