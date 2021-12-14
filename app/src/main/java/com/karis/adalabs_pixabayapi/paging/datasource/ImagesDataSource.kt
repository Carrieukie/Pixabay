package com.karis.adalabs_pixabayapi.paging.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karis.adalabs_pixabayapi.data.network.api.ImagesApi
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import com.karis.adalabs_pixabayapi.utils.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

//
//class ImagesDataSource(private val imagesApi: ImagesApi) : PagingSource<Int, HitsItem>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HitsItem> {
//
//        val page = params.key ?: STARTING_PAGE_INDEX
//
//        return try {
//
//            val response = imagesApi.getImages(
//                per_page = params.loadSize,
//                page = page,
//                searchQuery = searchQuery
//            )
//
//            val images = response.hits
//            LoadResult.Page(
//                data = images,
//                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
//                nextKey = if (images.isNotEmpty()) page + 1 else null
//            )
//
//        } catch (exception: IOException) {
//            val error = IOException("Please Check Internet Connection")
//            LoadResult.Error(error)
//        } catch (exception: HttpException) {
//            LoadResult.Error(exception)
//        }
//
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, HitsItem>): Int? {
//        return state.anchorPosition
//    }
//}