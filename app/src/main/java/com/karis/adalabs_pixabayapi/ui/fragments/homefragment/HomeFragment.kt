package com.karis.adalabs_pixabayapi.ui.fragments.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import com.karis.adalabs_pixabayapi.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalPagingApi
class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val adapter =
        HomeFragmentImagesAdapter { imageItem: HitsItem -> imageclicked(imageItem) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpAdapter()
        startSearchJob()
        initUi()
        return binding.root
    }

    private fun initUi() {
        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                adapter.refresh()
            }
            searcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                        if (query != null) {
                            viewModel.setSearchQuery(query)
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }
    }

    @ExperimentalPagingApi
    private fun startSearchJob() {

        lifecycleScope.launch {
            viewModel.search.collectLatest { searchQuery ->
                viewModel.searchImages(searchQuery).collectLatest {
                    adapter.submitData(it)
                }
            }

        }
    }

    private fun imageclicked(imageItem: HitsItem) {
        val toDetailAction = HomeFragmentDirections.homeFragmentToFragment(imageItem)
        findNavController().navigate(toDetailAction)
    }

    private fun setUpAdapter() {
        binding.apply {

            allProductRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            allProductRecyclerView.adapter = adapter.withLoadStateFooter(
                footer = HomeFragmentImagesLoadingAdapter { retry() }
            )

        }


        adapter.addLoadStateListener { loadState ->

            if (loadState.mediator?.refresh is LoadState.Loading) {

                if (adapter.snapshot().isEmpty()) {
                    binding.progress.isVisible = true
                }
                binding.errorTxt.isVisible = false

            } else {
                binding.progress.isVisible = false
                binding.swipeRefreshLayout.isRefreshing = false

                val error = when {

                    loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error

                    loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error

                    loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (adapter.snapshot().isEmpty()) {
                        binding.errorTxt.isVisible = true
                        binding.errorTxt.text = it.error.localizedMessage
                    }

                }

            }
        }

    }

    private fun retry() {
        adapter.retry()
    }

}