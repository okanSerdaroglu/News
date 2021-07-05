package com.example.news.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setUrl(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}

fun Context.openActivity(url: String) {
    if (!TextUtils.isEmpty(url)
        && (url.startsWith("http://")
                || (url.startsWith("https://")))
    ) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}

