package com.karis.adalabs_pixabayapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.karis.adalabs_pixabayapi.R
import com.karis.adalabs_pixabayapi.databinding.ActivityMainBinding
import com.karis.adalabs_pixabayapi.ui.fragments.homefragment.MainViewModel
import com.karis.adalabs_pixabayapi.ui.fragments.homefragment.ImagesAdapter
import com.karis.adalabs_pixabayapi.ui.fragments.homefragment.ImagesLoadingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


}