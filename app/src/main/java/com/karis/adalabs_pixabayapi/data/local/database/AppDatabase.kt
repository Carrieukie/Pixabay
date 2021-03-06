package com.karis.adalabs_pixabayapi.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karis.adalabs_pixabayapi.data.local.dao.ImagesDao
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import com.karis.adalabs_pixabayapi.paging.RemoteKeysDao
import com.karis.adalabs_pixabayapi.paging.remotekeys.RemoteKeys

@Database(entities = [HitsItem::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val imagesDao : ImagesDao
    abstract val remoteKeysDao: RemoteKeysDao

}