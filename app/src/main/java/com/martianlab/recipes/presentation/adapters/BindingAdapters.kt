package com.martianlab.recipes.presentation.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("bind:imgUrl")
    fun setImage(imageView: ImageView, imgUrl: String?) {
        if(imgUrl?.matches(""".*-\d{1}\.jpg""".toRegex()) == false && !imgUrl.matches(""".*-number\d{1}\.jpg""".toRegex()))
            Glide.with(imageView.context)
                .load(imgUrl)
                .into(imageView)
    }
}