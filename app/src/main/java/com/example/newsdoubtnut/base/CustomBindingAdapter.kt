package com.example.newsdoubtnut.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:image_url")
    fun loadImage(imageView: ImageView, url: String) {

        Glide.with(imageView.context)
            .load(url)
            .into(imageView)

//        Picasso.with(imageView.context).load(url).into(imageView)
    }
}