package com.example.randomuser.core.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    val page: Int? = 0,
    val results: Int? = 0,
    val seed: String? = null,
    val version: String? = null
) : Parcelable