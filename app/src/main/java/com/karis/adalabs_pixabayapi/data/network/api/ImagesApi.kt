package com.karis.adalabs_pixabayapi.data.network.api

import com.karis.adalabs_pixabayapi.data.network.responses.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET("api/")
    suspend fun getImages(
        @Query("key") key: String = "24807794-768c04e2a5e30599133aac816",
        @Query("per_page") per_page: Int?,
        @Query("page") page: Int?,
        @Query("q") searchQuery: String = "dog",
    ): ImagesResponse

}