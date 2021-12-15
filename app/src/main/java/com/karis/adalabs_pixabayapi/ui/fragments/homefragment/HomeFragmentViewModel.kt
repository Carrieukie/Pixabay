package com.karis.adalabs_pixabayapi.ui.fragments.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karis.adalabs_pixabayapi.data.ImagesRepository
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: ImagesRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("Dog");
    val search : StateFlow<String> = _searchQuery

    fun setSearchQuery(query : String){
        _searchQuery.value = query
    }

    @ExperimentalPagingApi
    fun searchImages(searchQuery : String): Flow<PagingData<HitsItem>> {
        val newResult: Flow<PagingData<HitsItem>> = repository.getImages(searchQuery).cachedIn(viewModelScope)
        return newResult
    }

}