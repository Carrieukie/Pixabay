package com.karis.adalabs_pixabayapi.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleImages(list: List<HitsItem>)

    @Query("SELECT * FROM images_table WHERE tags like '%' || :searchQuery || '%'")
    fun getImages(searchQuery : String): PagingSource<Int, HitsItem>

    @Query("DELETE FROM images_table")
    suspend fun clearRepos()

    @Query("SELECT COUNT(id) from images_table")
    suspend fun count(): Int

}