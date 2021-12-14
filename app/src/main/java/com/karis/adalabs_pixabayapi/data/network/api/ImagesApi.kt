package com.karis.adalabs_pixabayapi.data.network.api

import com.karis.adalabs_pixabayapi.data.network.responses.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET("api")
    suspend fun getImages(
        @Query("per_page") per_page: Int?,
        @Query("page") page: Int?,
    ): ImagesResponse

}