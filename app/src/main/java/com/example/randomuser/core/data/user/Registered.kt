package com.example.randomuser.core.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Registered(
    val age: Int? = 0,
    val date: String? = null
) : Parcelable