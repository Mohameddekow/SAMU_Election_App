package com.example.samuelectionapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.samuelectionapp.R
import com.squareup.picasso.Picasso

fun setImageWithPicasso(url: String, imageView: ImageView){
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.profile)
        .error(R.drawable.profile)
        .into(imageView)
}

fun ImageView.loadImageWithGlide(url: String){
    val option = RequestOptions().placeholder(R.drawable.profile)
        .error(R.drawable.profile)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)
}