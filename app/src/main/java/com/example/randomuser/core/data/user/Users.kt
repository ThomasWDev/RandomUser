package com.example.randomuser.core.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Users(
    val info: Info? = null,
    val results: List<Result> = emptyList()
) : Parcelable