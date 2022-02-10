package com.example.randomuser.core.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val cell: String? = null,
    val dob: Dob,
    val email: String? = null,
    val gender: String? = null,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String? = null,
    val phone: String? = null,
    val picture: Picture,
    val registered: Registered
) : Parcelable