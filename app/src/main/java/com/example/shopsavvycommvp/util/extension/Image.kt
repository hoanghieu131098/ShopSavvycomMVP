package com.example.shopsavvycommvp.util.extension

import android.widget.ImageView
import com.example.shopsavvycommvp.R
import com.squareup.picasso.Picasso

internal fun ImageView.loadImg(url: String?) {
    Picasso.get().load(url).error(R.drawable.img_loading).into(this)
}