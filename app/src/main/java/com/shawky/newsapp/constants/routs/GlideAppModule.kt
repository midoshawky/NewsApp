package com.shawky.newsapp.constants.routs

import android.content.Context
import android.media.Image
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.shawky.newsapp.R

@GlideModule
class GlideAppModule : AppGlideModule() {
    companion object {
        fun loadImage(imageView:ImageView,imageUrl:String,context:Context)  {
            GlideApp.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.news_placeholder)
                .into(imageView)
        }
    }
}