package com.example.randomuser.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * for network connectivity checking
 */
val Context.networkInfo: NetworkInfo?
    get() =
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
