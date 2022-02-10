package com.example.randomuser.core.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture(
    val large: String? = null,
    val medium: String? = null,
    val thumbnail: String? = null
) : Parcelable