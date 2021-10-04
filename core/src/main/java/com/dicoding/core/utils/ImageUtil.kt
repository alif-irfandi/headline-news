package com.dicoding.core.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.core.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, urlString: String?) {
    urlString?.let {
        Glide.with(imageView)
            .load(urlString)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_baseline_cloud)
                    .error(R.drawable.ic_baseline_broken_image)
            )
            .into(imageView)
    }
}