package com.karis.adalabs_pixabayapi.ui.fragments.homefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karis.adalabs_pixabayapi.data.ImagesRepository
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ImagesRepository
) : ViewModel() {
    private var currentResult: Flow<PagingData<HitsItem>>? = null

    @ExperimentalPagingApi
    fun searchImages(searchQuery : String): Flow<PagingData<HitsItem>> {
        val newResult: Flow<PagingData<HitsItem>> =
            repository.getImages(searchQuery).cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }

}