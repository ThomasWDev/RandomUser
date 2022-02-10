package com.example.randomuser.core.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val city: String? = null,
    val coordinates: Coordinates,
    val country: String? = null,
    val state: String? = null,
    val street: Street,
    val timezone: Timezone
) : Parcelable {

    companion object {
        fun getLocation(location: Location) : String {
            location.apply {
                return "${street.number.toString()} ${street.name} $city $state"
            }
        }
    }
}