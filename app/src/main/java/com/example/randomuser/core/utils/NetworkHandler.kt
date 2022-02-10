package com.example.randomuser.core.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * for network connectivity checking
 */
@Singleton
class NetworkHandler
@Inject constructor(@ApplicationContext val context: Context) {
    val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}