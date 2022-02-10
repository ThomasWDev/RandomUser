package com.example.randomuser.core.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Name(
    val first: String? = null,
    val last: String? = null,
    val title: String? = null
) : Parcelable