package com.karis.adalabs_pixabayapi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem

@Database(entities = [HitsItem::class], version = 0, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val imagesDao : ImagesDao

}