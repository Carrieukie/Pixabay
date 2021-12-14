package com.karis.adalabs_pixabayapi.ui.fragments.homefragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karis.adalabs_pixabayapi.commons.loadImage
import com.karis.adalabs_pixabayapi.data.network.responses.HitsItem
import com.karis.adalabs_pixabayapi.databinding.AdapterItemBinding

class ImagesAdapter(private val clicked: (HitsItem) -> Unit) :
    PagingDataAdapter<HitsItem, ImagesAdapter.ImagesViewHolder>(
        ImagesDiffCallback()
    ) {


    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {

        val data = getItem(position)
        holder.bind(data)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {

        return ImagesViewHolder(
            AdapterItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    inner class ImagesViewHolder(
        private val binding: AdapterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: HitsItem?) {

            binding.apply {

                imageViewImage.apply {

                    loadImage(
                        data?.largeImageURL
                    )

                    setOnClickListener {
                        clicked(data!!)
                    }

                }
                textViewImageUser.text = "By ${data?.user}"


            }

        }
    }

    private class ImagesDiffCallback : DiffUtil.ItemCallback<HitsItem>() {
        override fun areItemsTheSame(oldItem: HitsItem, newItem: HitsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HitsItem, newItem: HitsItem): Boolean {
            return oldItem == newItem
        }
    }

}