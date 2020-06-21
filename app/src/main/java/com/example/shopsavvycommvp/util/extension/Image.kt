package com.example.shopsavvycommvp.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.shopsavvycommvp.R
import com.squareup.picasso.Picasso

internal fun ImageView.loadImg(url: String?) {
    Glide.with(this.context).load(url).centerCrop().placeholder(R.drawable.ic_cart_profile).error(R.drawable.ic_cancel).dontAnimate().into(this)
}