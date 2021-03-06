package com.karis.adalabs_pixabayapi.commons

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.karis.adalabs_pixabayapi.R
import kotlin.math.ln
import kotlin.math.pow

//load a regular image using glide
fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(this)
    }
}

//load a circular image using glide
fun ImageView.loadCircularImage(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .circleCrop()
            .into(this)
    }
}

//get a shortened integer eg 1K, 1M,3G e.t.c
fun Int.shortenInt(): String {
    if (this < 1000) return this.toString()
    val exp = (ln(this.toDouble()) / ln(1000.0)).toInt()
    return String.format("%.1f%c", this / 1000.0.pow(exp.toDouble()), "KMGTPE"[exp - 1])
}

