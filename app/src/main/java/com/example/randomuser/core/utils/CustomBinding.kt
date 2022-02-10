package com.example.randomuser.core.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.circleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.randomuser.R

/** ---
* Utility class to contain functions for custom binding implementation
*/
@BindingAdapter("profileImage")
fun setProfile(imageView: ImageView, source: String?) {
    source?.let {
        if (it.isNotBlank()) {
            Glide.with(imageView.context)
                .load(it)
                .apply(RequestOptions.centerCropTransform())
                .placeholder(R.drawable.ic_no_image_available)
                .into(imageView)
        }
    }
}

@BindingAdapter("circularProfileImage")
fun setCircularProfile(imageView: ImageView, source: String?) {
    source?.let {
        if (it.isNotBlank()) {
            Glide.with(imageView.context)
                .load(it)
                .apply(RequestOptions().circleCrop())
                //.apply(RequestOptions.centerCropTransform())
                .placeholder(R.drawable.ic_no_image_available)
                .into(imageView)


        }
    }
}


