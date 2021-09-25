package me.fetsh.geekbrains.libraries.github.ui.images

import android.widget.ImageView
import com.bumptech.glide.Glide

interface ImageLoader<T> {

    fun loadTo(url: String, target: T)

}

class GlideImageLoader : ImageLoader<ImageView> {
    override fun loadTo(url: String, target: ImageView) {
        Glide.with(target.context)
            .load(url)
            .into(target)
    }
}