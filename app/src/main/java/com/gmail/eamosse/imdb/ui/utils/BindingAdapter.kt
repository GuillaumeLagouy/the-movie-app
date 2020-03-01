package com.gmail.eamosse.imdb.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value=["app:bindImage", "app:radius"], requireAll = false)
fun bindImage(imageView: ImageView, url: String?, radius:Int?) {
    if(url == null) return
    var requestOptions = RequestOptions()
    if(radius != null){
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(radius))
    }

    Glide.with(imageView).load("https://image.tmdb.org/t/p/w500/$url")
        .apply(requestOptions)
        .into(imageView)
}