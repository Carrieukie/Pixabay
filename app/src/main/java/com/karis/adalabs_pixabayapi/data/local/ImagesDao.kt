package com.karis.adalabs_pixabayapi.data.local

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import com.karis.adalabs_pixabayapi.data.network.responses.ImagesResponse

interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleImages(list: List<HitsItem>)

    @Query("SELECT * FROM images_table")
    fun getImages(): PagingSource<Int, HitsItem>

    @Query("DELETE FROM images_table")
    suspend fun clearRepos()

    @Query("SELECT COUNT(id) from images_table")
    suspend fun count(): Int

}