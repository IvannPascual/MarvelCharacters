package com.app.marvelapp.base.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.marvelapp.R
import com.bumptech.glide.Glide

@BindingAdapter("selected")
    fun bindFavorite(view: ImageView, isSelected: Boolean) {
        if(isSelected) view.setImageResource(R.drawable.ic_favorite_selected)
        else view.setImageResource(R.drawable.ic_favorite_unselected)
    }


@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view)
        .load(url)
        .into(view)
}