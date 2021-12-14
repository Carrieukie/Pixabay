package com.karis.adalabs_pixabayapi.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.karis.adalabs_pixabayapi.data.local.AppDatabase
import com.karis.adalabs_pixabayapi.data.network.api.ImagesApi
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import dev.ronnie.allplayers.data.remotediator.ImagesRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val imagesApi: ImagesApi,
    private val db: AppDatabase
) {

    @ExperimentalPagingApi
    fun getImages(searchQuery : String): Flow<PagingData<HitsItem>> {

        val pagingSourceFactory = { db.imagesDao.getImages(searchQuery) }

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = ImagesRemoteMediator(
                imagesApi,
                db,
                searchQuery
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}