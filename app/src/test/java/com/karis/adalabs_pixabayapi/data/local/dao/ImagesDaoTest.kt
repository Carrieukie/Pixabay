package com.karis.adalabs_pixabayapi.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.karis.adalabs_pixabayapi.commons.getOrAwaitValue
import com.karis.adalabs_pixabayapi.data.local.database.AppDatabase
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ImagesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: ImagesDao


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.imagesDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertImageItems() = runBlockingTest {

        val imageItems = mutableListOf<HitsItem>()

        for(i in 1..10){
            imageItems.add(HitsItem(id = i))
        }

        dao.insertMultipleImages(imageItems.toList())
        val allImagesItems = dao.observeAllImagesItems().getOrAwaitValue()

        assertThat(allImagesItems.size).isEqualTo(10)
    }

    @Test
    fun deleteAllImageItem() = runBlockingTest {
        val imageItems = mutableListOf<HitsItem>()

        for(i in 1..10){
            imageItems.add(HitsItem(id = i))
        }

        dao.insertMultipleImages(imageItems.toList())
        dao.clearRepos()
        val allImagesItems = dao.observeAllImagesItems().getOrAwaitValue()

        assertThat(allImagesItems.size).isEqualTo(0)
    }


}