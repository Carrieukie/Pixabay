package dev.ronnie.allplayers.data.remotediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.karis.adalabs_pixabayapi.data.local.AppDatabase
import com.karis.adalabs_pixabayapi.data.network.api.ImagesApi
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import com.karis.adalabs_pixabayapi.paging.remotekeys.RemoteKeys
import com.karis.adalabs_pixabayapi.utils.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException


@ExperimentalPagingApi
class ImagesRemoteMediator(
    private val service: ImagesApi,
    private val db: AppDatabase
) : RemoteMediator<Int, HitsItem>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, HitsItem>): MediatorResult {

        val key = when (loadType) {

            LoadType.REFRESH -> {
                if (db.imagesDao.count() > 0) return MediatorResult.Success(false)
                null
            }
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                getKey()
            }
        }

        try {
            if (key != null) {
                if (key.isEndReached) return MediatorResult.Success(endOfPaginationReached = true)
            }

            val page: Int = key?.nextKey ?: STARTING_PAGE_INDEX
            val apiResponse = service.getImages(state.config.pageSize, page)

            val imagesList = apiResponse.hits

            val endOfPaginationReached =
                apiResponse.meta.next_page == null || apiResponse.meta.current_page == apiResponse.meta.total_pages

            db.withTransaction {
                val nextKey = page + 1

                db.remoteKeysDao.insertKey(
                    RemoteKeys(
                        0,
                        nextKey = nextKey,
                        isEndReached = endOfPaginationReached
                    )
                )
                db.imagesDao.insertMultipleImages(imagesList)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKey(): RemoteKeys? {
        return db.remoteKeysDao.getKeys().firstOrNull()
    }


}