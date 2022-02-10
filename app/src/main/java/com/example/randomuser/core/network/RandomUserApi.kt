package com.example.randomuser.core.network

import com.example.randomuser.core.data.user.Users
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Endpoints
 */
interface RandomUserApi {

    @GET("api/")
    suspend fun getUsers(
        @Query("results") results: Int = 10
    ): Users

}