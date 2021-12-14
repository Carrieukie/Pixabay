package com.karis.adalabs_pixabayapi.di

import com.karis.adalabs_pixabayapi.data.network.api.ImagesApi
import com.karis.adalabs_pixabayapi.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() : HttpLoggingInterceptor{
       return  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    @Singleton
    fun providesokHttpClient(loggingInterceptor : HttpLoggingInterceptor ) : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient : OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideImagesApi(retrofit: Retrofit): ImagesApi = retrofit.create(ImagesApi::class.java)

}