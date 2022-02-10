package com.example.randomuser.core.network

import com.example.randomuser.core.data.user.Users
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RandomUserService @Inject constructor(retrofit: Retrofit) : RandomUserApi {
    private val api by lazy { retrofit.create(RandomUserApi::class.java) }

    override suspend fun getUsers(results: Int): Users =
        api.getUsers(results)

}


