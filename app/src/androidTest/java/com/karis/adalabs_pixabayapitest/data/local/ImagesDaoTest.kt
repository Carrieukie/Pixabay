package com.karis.adalabs_pixabayapitest.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.ExperimentalPagingApi
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.karis.adalabs_pixabayapi.data.local.dao.ImagesDao
import com.karis.adalabs_pixabayapi.data.local.database.AppDatabase
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import com.karis.adalabs_pixabayapitest.getOrAwaitValue
import com.karis.adalabs_pixabayapitest.launchFragmentInHiltContainer
import com.karis.adalabs_pixabayapi.ui.fragments.homefragment.HomeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class ImagesDaoTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var dao: ImagesDao


    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.imagesDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @ExperimentalPagingApi
    @Test
    fun testLaunchFragmentInHiltContainer() {
        launchFragmentInHiltContainer<HomeFragment> {

        }
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