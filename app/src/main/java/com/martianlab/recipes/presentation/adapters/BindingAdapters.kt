package com.martianlab.recipes.presentation.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("bind:imgUrl")
    fun setImage(imageView: ImageView, imgUrl: String?) {
        Glide.with(imageView.getContext()).load(imgUrl)
            .into(
                imageView
            )
    }
}