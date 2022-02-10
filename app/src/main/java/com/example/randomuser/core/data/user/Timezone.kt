package com.example.randomuser.core.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Timezone(
    val description: String? = null,
    val offset: String? = null
) : Parcelable